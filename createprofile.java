package com.example.riseandshine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.riseandshine.api.ApiInterface;
import com.example.riseandshine.api.RetrofitClient;
import com.example.riseandshine.model.SignUpRequest;
import com.example.riseandshine.model.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createprofile extends AppCompatActivity {

    private Button btnContinue, btnLogin;
    private EditText etName, etMobileNo, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);

        // Initialize views
        etName = findViewById(R.id.etName);
        etMobileNo = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        btnContinue = findViewById(R.id.btnSave);
        btnLogin = findViewById(R.id.tvLoginTitle);

        // Set button click listener
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProfile();
            }
        });

        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, loginpage.class));
        });
    }

    private void createProfile() {
        // Retrieve input values
        String name = etName.getText().toString().trim();
        String mobileNo = etMobileNo.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate input
        if (name.isEmpty() || mobileNo.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate mobile number format (exactly 10 digits)
        if (!mobileNo.matches("\\d{10}")) {
            Toast.makeText(this, "Please enter a valid 10-digit mobile number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create SignUpRequest object
        SignUpRequest signUpRequest = new SignUpRequest(name, mobileNo, password);

        try {
            ApiInterface apiService = RetrofitClient.getClient().create(ApiInterface.class);
            apiService.create(signUpRequest).enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        SignUpResponse signUpResponse = response.body();
                        if (signUpResponse.isSuccess()) {
                            Toast.makeText(createprofile.this, "Signup Successful!", Toast.LENGTH_SHORT).show();

                            Intent secondIntent = new Intent(createprofile.this, Identitypage.class);
                            String userId = response.body().getUser().getId(); // Assuming `getUser().getId()` gets the userId
                            getSharedPreferences("AppPrefs", MODE_PRIVATE)
                                    .edit()
                                    .putString("userId", userId)
                                    .apply();

                            Log.i("id...", response.body().getUser().getId());

                            startActivity(secondIntent);
                            finish();
                        } else {
                            Toast.makeText(createprofile.this, "Signup Failed: " + signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("API_ERROR", "Response Code: " + response.code());
                        Toast.makeText(createprofile.this, "Invalid Response: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    Log.e("API_ERROR", "onFailure: " + t.getMessage(), t);
                    Toast.makeText(createprofile.this, "API Call Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.e("EXCEPTION", "Error: " + e.getMessage(), e);
            Toast.makeText(this, "Unexpected Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
