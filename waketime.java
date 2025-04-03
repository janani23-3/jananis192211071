package com.example.riseandshine;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.riseandshine.api.ApiInterface;
import com.example.riseandshine.api.RetrofitClient;
import com.example.riseandshine.WakeUpTimeRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class waketime extends AppCompatActivity {

    private ApiInterface apiInterface;
    private String selectedTime = null; // Holds the selected time
    private String userId; // User ID passed from previous activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waketime);

        // Initialize Retrofit interface
        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);

        // Get User ID from Intent
        userId = getSharedPreferences("AppPrefs", MODE_PRIVATE).getString("userId", null);

        Log.i("userIDDDD...", userId);
        if (userId == null) {
            Toast.makeText(this, "User ID not found. Please log in again.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Fetch TextView and Button
        TextView selectedTimeView = findViewById(R.id.selected_time);
        Button nextButton = findViewById(R.id.next_button);

        // Show TimePickerDialog on TextView click
        selectedTimeView.setOnClickListener(v -> showTimePickerDialog(selectedTimeView));

        // Set click listener for the Next button
        nextButton.setOnClickListener(v -> {
            if (selectedTime != null) {
                saveWakeUpTime(userId, selectedTime);
            } else {
                Toast.makeText(this, "Please select a wake-up time", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showTimePickerDialog(TextView timeView) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    // Format the selected time
                    String amPm = (hourOfDay < 12) ? "AM" : "PM";
                    int hour = (hourOfDay == 0 || hourOfDay == 12) ? 12 : hourOfDay % 12;
                    String formattedTime = String.format("%02d:%02d %s", hour, minute, amPm);

                    // Set the selected time in the TextView and store it
                    timeView.setText(formattedTime);
                    selectedTime = formattedTime;
                },
                6, // Default hour (6 AM)
                0, // Default minute
                false // 12-hour format
        );
        timePickerDialog.show();
    }


    private void saveWakeUpTime(String userId, String wakeUpTime) {
        // Create the request body
        WakeUpTimeRequest request = new WakeUpTimeRequest(wakeUpTime);

        // Make the API call
        Call<UserResponse> call = apiInterface.saveWakeUpTime(userId, request);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(waketime.this, "Wake-Up Time Updated Successfully!", Toast.LENGTH_SHORT).show();

                    // Navigate to the next activity
                    Intent intent = new Intent(waketime.this, Question1.class);
                    //intent.putExtra("userId", userId); // Pass userId to the next activity
                    startActivity(intent);
                    finish();
                } else {
                    handleError(response);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                handleFailure(t);
            }
        });
    }


    private void handleError(Response<?> response) {
        Log.e("API_ERROR", "Response code: " + response.code() + ", message: " + response.message());
        if (response.errorBody() != null) {
            try {
                Log.e("API_ERROR_BODY", response.errorBody().string());
            } catch (IOException e) {
                Log.e("API_ERROR_BODY", "Error reading error body", e);
            }
        }
        Toast.makeText(this, "Failed to update wake-up time. Please try again.", Toast.LENGTH_SHORT).show();
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
