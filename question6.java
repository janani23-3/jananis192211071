package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class question6 extends AppCompatActivity {

    private String selectedoption = ""; // Variable to hold the selected answer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question6);

        // Initialize buttons
        Button easilydistracted = findViewById(R.id.option1);
        Button occasionallydistracted = findViewById(R.id.option2);
        Button rarelydistracted = findViewById(R.id.option3);
        Button notdistracted = findViewById(R.id.option4);

        // Set click listeners for each option
        easilydistracted.setOnClickListener(v -> proceedToNext(easilydistracted, "Easily distracted"));
        occasionallydistracted.setOnClickListener(v -> proceedToNext(occasionallydistracted, "Occasionally distracted"));
        rarelydistracted.setOnClickListener(v -> proceedToNext(rarelydistracted, "Rarely distracted"));
        notdistracted.setOnClickListener(v -> proceedToNext(notdistracted, "Not distracted at all"));
    }

    private void proceedToNext(Button selectedButton, String option) {
        // Reset button styles
        resetButtonStyles();

        // Highlight the selected button
        selectedButton.setAlpha(1.0f); // Fully opaque
        selectedButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color)); // Replace with your color resource

        // Store the selected option
        selectedoption = option;

        // Show a toast with the selected option
        Toast.makeText(this, "Selected: " + selectedoption, Toast.LENGTH_SHORT).show();

        // Add a delay before navigating to the next activity
        new Handler().postDelayed(() -> {
            // Navigate to the next activity after the delay
            Intent intent = new Intent(question6.this, question7.class);
            startActivity(intent);
        }, 1000); // 1000 milliseconds (1 second) delay
    }

    private void resetButtonStyles() {
        // Reset styles for all buttons
        Button easilydistracted = findViewById(R.id.option1);
        Button occasionallydistracted = findViewById(R.id.option2);
        Button rarelydistracted = findViewById(R.id.option3);
        Button notdistracted = findViewById(R.id.option4);

        easilydistracted.setAlpha(0.5f);
        easilydistracted.setBackgroundColor(getResources().getColor(androidx.cardview.R.color.cardview_shadow_end_color));

        occasionallydistracted.setAlpha(0.5f);
        occasionallydistracted.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));

        rarelydistracted.setAlpha(0.5f);
        rarelydistracted.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));

        notdistracted.setAlpha(0.5f);
        notdistracted.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));
    }
}
