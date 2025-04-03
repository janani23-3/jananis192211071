package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class question8 extends AppCompatActivity {

    private String selectedoption = ""; // Variable to hold the selected answer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question8);

        // Initialize buttons
        Button Notbusy = findViewById(R.id.option1);
        Button Enough = findViewById(R.id.option2);
        Button Busy = findViewById(R.id.option3);
        Button Verybusy = findViewById(R.id.option4);

        // Set click listeners for each option
        Notbusy.setOnClickListener(v -> proceedToNext(Notbusy, "Not busy"));
        Enough.setOnClickListener(v -> proceedToNext(Enough, "Enough"));
        Busy.setOnClickListener(v -> proceedToNext(Busy, "Busy"));
        Verybusy.setOnClickListener(v -> proceedToNext(Verybusy, "Very busy"));
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
            Intent intent = new Intent(question8.this, routinefinding1.class);
            startActivity(intent);
        }, 1000); // 1000 milliseconds (1 second) delay
    }

    private void resetButtonStyles() {
        // Reset styles for all buttons
        Button Notbusy = findViewById(R.id.option1);
        Button Enough = findViewById(R.id.option2);
        Button Busy = findViewById(R.id.option3);
        Button Verybusy = findViewById(R.id.option4);

        Notbusy.setAlpha(0.5f);
        Notbusy.setBackgroundColor(getResources().getColor(androidx.cardview.R.color.cardview_shadow_end_color));

        Enough.setAlpha(0.5f);
        Enough.setBackgroundColor(getResources().getColor(androidx.cardview.R.color.cardview_shadow_end_color));

        Busy.setAlpha(0.5f);
        Busy.setBackgroundColor(getResources().getColor(androidx.cardview.R.color.cardview_shadow_end_color));

        Verybusy.setAlpha(0.5f);
        Verybusy.setBackgroundColor(getResources().getColor(androidx.cardview.R.color.cardview_shadow_end_color));
    }
}
