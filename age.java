package com.example.riseandshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.riseandshine.api.ApiInterface;
import com.example.riseandshine.api.RetrofitClient;
import com.example.riseandshine.AgeRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class age extends AppCompatActivity {

    private EditText editTextAge;
    private Button buttonNext;
    private String userId;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

        // Initialize Retrofit interface
        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);

        // Retrieve userId from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        userId = prefs.getString("userId", null);

        if (userId == null) {
            Log.e("USER_ID", "User ID is null! Please check SharedPreferences.");
            Toast.makeText(this, "User ID is missing, please log in again.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize UI components
        editTextAge = findViewById(R.id.editText_age);
        buttonNext = findViewById(R.id.button_next);

        buttonNext.setOnClickListener(v -> {
            String age = editTextAge.getText().toString().trim();

            if (!age.matches("\\d+") || Integer.parseInt(age) <= 0 || Integer.parseInt(age) > 120) {
                Toast.makeText(this, "Please enter a valid age (1-120)", Toast.LENGTH_SHORT).show();
                return;
            }


            sendAgeToServer(userId, age);
        });
    }

    private void sendAgeToServer(String userId, String age) {
        AgeRequest ageRequest = new AgeRequest(age);

        Call<Void> call = apiInterface.saveAge(userId, ageRequest);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(age.this, "Age submitted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(age.this, waketime.class);
                    startActivity(intent);
                    finish();
                } else {
                    handleError(response);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                handleFailure(t);
            }
        });
    }

    private void handleError(Response<?> response) {
        Log.e("API_ERROR", "Response code: " + response.code() + ", message: " + response.message());
        if (response.errorBody() != null) {
            try {
                String errorBody = response.errorBody().string();
                Log.e("API_ERROR_BODY", errorBody);
            } catch (IOException e) {
                Log.e("API_ERROR_BODY", "Error reading error body", e);
            }
        }

        Toast.makeText(this, "Failed to submit age. Please try again.", Toast.LENGTH_SHORT).show();
    }

    private void handleFailure(Throwable t) {
        if (t instanceof IOException) {
            Log.e("API_FAILURE", "Network failure: " + t.getMessage());
            Toast.makeText(this, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("API_FAILURE", "Unexpected error: " + t.getMessage());
            Toast.makeText(this, "Unexpected error occurred. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
