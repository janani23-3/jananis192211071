package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.riseandshine.api.ApiInterface;
import com.example.riseandshine.api.RetrofitClient;
import com.example.riseandshine.GenderRequest;
import com.example.riseandshine.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Identitypage extends AppCompatActivity {

    private Button buttonMale, buttonFemale, buttonOthers, nextButton;
    private String selectedGender;
    private String userID; // Retrieved from SharedPreferences or Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identitypage); // Reference your XML layout

        // Initialize views
        buttonMale = findViewById(R.id.button_male);
        buttonFemale = findViewById(R.id.button_female);
        buttonOthers = findViewById(R.id.button_others);
        nextButton = findViewById(R.id.next_button);

        // Retrieve userID from SharedPreferences or Intent
        userID = getSharedPreferences("AppPrefs", MODE_PRIVATE).getString("userId", null);

        if (userID == null) {
            Toast.makeText(this, "User ID is missing. Please log in again.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Set button click listeners for gender selection
        buttonMale.setOnClickListener(v -> selectGender("Male"));
        buttonFemale.setOnClickListener(v -> selectGender("Female"));
        buttonOthers.setOnClickListener(v -> selectGender("Others"));

        // Set Next button click listener
        nextButton.setOnClickListener(v -> {
            if (selectedGender != null) {
                sendGenderToServer(userID, selectedGender);
            } else {
                Toast.makeText(this, "Please select a gender.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectGender(String gender) {
        selectedGender = gender;
        buttonMale.setAlpha(gender.equals("Male") ? 1.0f : 0.5f);
        buttonFemale.setAlpha(gender.equals("Female") ? 1.0f : 0.5f);
        buttonOthers.setAlpha(gender.equals("Others") ? 1.0f : 0.5f);
    }


    private void sendGenderToServer(String userID, String gender) {
        ApiInterface apiService = RetrofitClient.getClient().create(ApiInterface.class);

        // ✅ Create a proper GenderRequest object
        GenderRequest genderRequest = new GenderRequest(userID, gender);

        // ✅ Use @Path parameter for userID
        apiService.saveGender(userID, genderRequest).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserResponse genderResponse = response.body();
                    if (genderResponse.isSuccess()) {
                        Toast.makeText(Identitypage.this, "Gender selection saved successfully!", Toast.LENGTH_SHORT).show();

                        // Navigate to the next activity
                        Intent intent = new Intent(Identitypage.this, age.class);
                        intent.putExtra("selectedGender", gender);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Identitypage.this, "Failed: " , Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("API_ERROR", "Response Code: " + response.code());
                    Toast.makeText(Identitypage.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("API_ERROR", "onFailure: " + t.getMessage(), t);
                Toast.makeText(Identitypage.this, "API call failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
