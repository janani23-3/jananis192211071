package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.riseandshine.api.RetrofitClient;
import com.example.riseandshine.model.LoginRequest;
import com.example.riseandshine.model.LoginResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginpage extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        // Initialize UI components
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.login_button);

        // Set OnClickListener for the Login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Check if the fields are not empty
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(loginpage.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(username, password);
                }
            }
        });
    }

    private void loginUser(String username, String password) {
        LoginRequest request = new LoginRequest();
        request.setMobileNo(username);
        request.setPassword(password);

        Call<LoginResponse> call = RetrofitClient.getApiService().login(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().isSuccess()) {
                        Toast.makeText(loginpage.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        // Save user ID to SharedPreferences
//                        String userId = response.body().getUser().getId(); // Assuming `getUser().getId()` gets the userId
//                        getSharedPreferences("AppPrefs", MODE_PRIVATE)
//                                .edit()
//                                .putString("userId", userId)
//                                .apply();

                        // Navigate to morningroutine
                        startActivity(new Intent(loginpage.this, morningroutine.class));
                        finish();
                    }
                } else {
                    // Handle error response
                    try {
                        String errorJson = response.errorBody().string();
                        LoginResponse errorResponse = new Gson().fromJson(errorJson, LoginResponse.class);
                        Toast.makeText(loginpage.this, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.e("TAG", "Error parsing error response: " + e.getMessage());
                        Toast.makeText(loginpage.this, "An error occurred.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(loginpage.this, "Failed to connect to the server.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
