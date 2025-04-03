package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class question4 extends AppCompatActivity {

    private String selectedOption = ""; // Variable to hold the selected answer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);

        // Initialize buttons
        Button low = findViewById(R.id.option1);
        Button medium = findViewById(R.id.option2);
        Button high = findViewById(R.id.option3);
        Button cantdescribe = findViewById(R.id.option4);

        // Set click listeners for each option
        low.setOnClickListener(v -> proceedToNext(low, "low"));
        medium.setOnClickListener(v -> proceedToNext(medium, "medium"));
        high.setOnClickListener(v -> proceedToNext(high, "high"));
        cantdescribe.setOnClickListener(v -> proceedToNext(cantdescribe, "can't describe"));
    }

    private void proceedToNext(Button selectedButton, String option) {
        // Reset button styles
        resetButtonStyles();

        // Highlight the selected button
        selectedButton.setAlpha(1.0f); // Fully opaque
        selectedButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color)); // Replace with your color resource

        // Store the selected option
        selectedOption = option;

        // Show a toast with the selected option
        Toast.makeText(this, "Selected: " + selectedOption, Toast.LENGTH_SHORT).show();

        // Add a delay before navigating to the next activity
        new Handler().postDelayed(() -> {
            // Navigate to the next activity after the delay
            Intent intent = new Intent(question4.this, question5.class);
            startActivity(intent);
        }, 1000); // 1000 milliseconds (1 second) delay
    }

    private void resetButtonStyles() {
        // Reset styles for all buttons
        Button low = findViewById(R.id.option1);
        Button medium = findViewById(R.id.option2);
        Button high = findViewById(R.id.option3);
        Button cantdescribe = findViewById(R.id.option4);

        low.setAlpha(0.5f);
        low.setBackgroundColor(getResources().getColor(androidx.cardview.R.color.cardview_shadow_end_color));

        medium.setAlpha(0.5f);
        medium.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));

        high.setAlpha(0.5f);
        high.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));

        cantdescribe.setAlpha(0.5f);
        cantdescribe.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));
    }
}
