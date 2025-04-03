package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Question2 extends AppCompatActivity {

    private Button always, usually, sometimes, rarely;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        // Initialize buttons
        always = findViewById(R.id.option1);
        usually = findViewById(R.id.option2);
        sometimes = findViewById(R.id.option3);
        rarely = findViewById(R.id.option4);

        // Set click listeners for each option
        always.setOnClickListener(v -> proceedToNext(always, "always"));
        usually.setOnClickListener(v -> proceedToNext(usually, "usually"));
        sometimes.setOnClickListener(v -> proceedToNext(sometimes, "sometimes"));
        rarely.setOnClickListener(v -> proceedToNext(rarely, "rarely"));
    }

    private void proceedToNext(Button selectedButton, String selectedOption) {
        // Reset button styles
        resetButtonStyles();

        // Highlight the selected button
        selectedButton.setAlpha(1.0f); // Fully opaque
        selectedButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color)); // Replace with your color resource

        // Show a toast with the selected option
        Toast.makeText(this, "Selected: " + selectedOption, Toast.LENGTH_SHORT).show();

        // Add a delay before navigating to the next activity
        new Handler().postDelayed(() -> {
            // Navigate to the next activity after the delay
            Intent intent = new Intent(Question2.this, question3.class);
            startActivity(intent);
        }, 1000); // 2000 milliseconds (2 seconds) delay
    }

    private void resetButtonStyles() {
        // Reset styles for all buttons
        always.setAlpha(0.5f);
        always.setBackgroundColor(getResources().getColor(androidx.cardview.R.color.cardview_shadow_end_color));

        usually.setAlpha(0.5f);
        usually.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));

        sometimes.setAlpha(0.5f);
        sometimes.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));

        rarely.setAlpha(0.5f);
        rarely.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));
    }
}
