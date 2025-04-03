package com.example.riseandshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.riseandshine.api.ApiInterface;
import com.example.riseandshine.api.RetrofitClient;
import com.example.riseandshine.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profilepage extends AppCompatActivity {

    private static final int EDIT_PROFILE_REQUEST = 1;

    TextView profileName, genderValue, ageValue;
    Button editButton, logoutButton;
    ImageView planIcon, ideaIcon, profileIcon;

    private String userId;
    private ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);

        // Initialize Views
        profileName = findViewById(R.id.profileName);
        genderValue = findViewById(R.id.genderValue);
        ageValue = findViewById(R.id.ageValue);
        editButton = findViewById(R.id.editButton);
        logoutButton= findViewById(R.id.logout);

        planIcon = findViewById(R.id.clockk);
        ideaIcon = findViewById(R.id.search);
        profileIcon = findViewById(R.id.profileIcon);

        // Initialize Retrofit API
        apiService = RetrofitClient.getClient().create(ApiInterface.class);

        // Retrieve userId from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        userId = prefs.getString("userId", null);

        if (userId == null) {
            Toast.makeText(this, "User ID is missing. Please log in again.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Fetch User Profile Data
        fetchUserProfile();

        // Set up Click Listeners
        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(profilepage.this, editname.class);
            intent.putExtra("name", profileName.getText().toString());
            intent.putExtra("gender", genderValue.getText().toString());
            intent.putExtra("age", ageValue.getText().toString());
            startActivityForResult(intent, EDIT_PROFILE_REQUEST);
        });

        planIcon.setOnClickListener(v -> startActivity(new Intent(profilepage.this, morningroutine.class)));
        ideaIcon.setOnClickListener(v -> startActivity(new Intent(profilepage.this, explorepage.class)));
        profileIcon.setOnClickListener(v -> startActivity(new Intent(profilepage.this, profilepage.class)));
        logoutButton.setOnClickListener((v -> startActivity(new Intent(profilepage.this,createprofile.class))));// Refresh page
    }

    private void fetchUserProfile() {
        Call<UserResponse> call = apiService.getUserProfile(userId);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    UserResponse.User user = response.body().getUser();
                    profileName.setText(user.getName());
                    genderValue.setText(user.getGender());
                    ageValue.setText(String.valueOf(user.getAge()));
                } else {
                    Log.e("API_ERROR", "Failed response. Code: " + response.code());
                    Toast.makeText(profilepage.this, "Failed to load user profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("API_FAILURE", "Error: " + t.getMessage());
                Toast.makeText(profilepage.this, "Network error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_PROFILE_REQUEST && resultCode == RESULT_OK && data != null) {
            profileName.setText(data.getStringExtra("name"));
            genderValue.setText(data.getStringExtra("gender"));
            ageValue.setText(data.getStringExtra("age"));
        }
    }
}
