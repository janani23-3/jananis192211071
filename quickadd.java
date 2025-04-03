package com.example.riseandshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.riseandshine.api.ApiInterface;
import com.example.riseandshine.api.RetrofitClient;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class quickadd extends AppCompatActivity {

    private ImageButton backButton;
    private Button doneButton;
    private EditText taskNameInput, taskTimeInput;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickadd);

        // Retrieve userId from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        userId = prefs.getString("userId", null);

        if (userId == null) {
            Toast.makeText(this, "User ID not found. Please log in again.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize Views
        backButton = findViewById(R.id.backButton);
        doneButton = findViewById(R.id.doneButton);
        taskNameInput = findViewById(R.id.task_name_input);
        taskTimeInput = findViewById(R.id.task_time_input);

        // Set Back Button Click Listener
        backButton.setOnClickListener(v -> finish());

        // Set Done Button Click Listener
        doneButton.setOnClickListener(v -> {
            // Retrieve input data
            String taskName = taskNameInput.getText().toString().trim();
            String taskTime = taskTimeInput.getText().toString().trim();

            // Validate input
            if (taskName.isEmpty() || taskTime.isEmpty()) {
                Toast.makeText(quickadd.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Call the API to add the task
            addTaskToServer(taskName, taskTime);
        });
    }

    private void addTaskToServer(String taskName, String taskTime) {
        TaskRequest taskRequest = new TaskRequest(taskName, taskTime);

        ApiInterface apiService = RetrofitClient.getApiService();
        Call<TaskResponse> call = apiService.addTask(userId, taskRequest);

        call.enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String message = response.body().getMessage();
                    Toast.makeText(quickadd.this, message, Toast.LENGTH_SHORT).show();

                    // Finish activity or navigate to another screen
                    Intent intent = new Intent(quickadd.this, morningroutine.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Handle HTTP errors (e.g., 400 or 500)
                    String errorMessage = "Failed to add task. Please try again.";
                    if (response.errorBody() != null) {
                        try {
                            errorMessage = response.errorBody().string();
                        } catch (Exception e) {
                            Log.e("quickadd", "Error reading error body: " + e.getMessage());
                        }
                    }
                    Toast.makeText(quickadd.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                Log.e("quickadd", "Error adding task: " + t.getMessage());
                Toast.makeText(quickadd.this, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

