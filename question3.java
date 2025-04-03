package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class question3 extends AppCompatActivity {

    private Button Checkyourphone, teaorcoffee, morningwalk, backtosleep;
    private String selectedOption = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        // Initialize buttons
        Checkyourphone = findViewById(R.id.option1);
        teaorcoffee = findViewById(R.id.option2);
        morningwalk = findViewById(R.id.option3);
        backtosleep = findViewById(R.id.option4);

        // Set click listeners for each option
        Checkyourphone.setOnClickListener(v -> proceedToNext(Checkyourphone, "Check your phone"));
        teaorcoffee.setOnClickListener(v -> proceedToNext(teaorcoffee, "Drink water or tea or coffee"));
        morningwalk.setOnClickListener(v -> proceedToNext(morningwalk, "Morning walk or exercise"));
        backtosleep.setOnClickListener(v -> proceedToNext(backtosleep, "Hit the snooze button and go back to sleep"));
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
            Intent intent = new Intent(question3.this, question4.class);
            startActivity(intent);
        }, 1000); // 2000 milliseconds (2 seconds) delay
    }

    private void resetButtonStyles() {
        // Reset styles for all buttons
        Checkyourphone.setAlpha(0.5f);
        Checkyourphone.setBackgroundColor(getResources().getColor(androidx.cardview.R.color.cardview_shadow_end_color));

        teaorcoffee.setAlpha(0.5f);
        teaorcoffee.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));

        morningwalk.setAlpha(0.5f);
        morningwalk.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));

        backtosleep.setAlpha(0.5f);
        backtosleep.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));
    }
}
