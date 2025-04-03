package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Question1 extends AppCompatActivity {

    private Button btn7HoursOrLess, btn7To9Hours, btn9To12Hours, btn12HoursOrMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        // Initialize buttons
        btn7HoursOrLess = findViewById(R.id.option1);
        btn7To9Hours = findViewById(R.id.option2);
        btn9To12Hours = findViewById(R.id.option3);
        btn12HoursOrMore = findViewById(R.id.option4);

        // Set click listeners for sleep duration buttons
        btn7HoursOrLess.setOnClickListener(v -> proceedToNext(btn7HoursOrLess, "7 hours or less"));
        btn7To9Hours.setOnClickListener(v -> proceedToNext(btn7To9Hours, "7-9 hours"));
        btn9To12Hours.setOnClickListener(v -> proceedToNext(btn9To12Hours, "9-12 hours"));
        btn12HoursOrMore.setOnClickListener(v -> proceedToNext(btn12HoursOrMore, "12 hours or more"));
    }

    private void proceedToNext(Button selectedButton, String selectedOption) {
        // Reset button states to default
        resetButtonStyles();

        // Highlight the selected button
        selectedButton.setAlpha(1.0f); // Fully opaque
        selectedButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color)); // Replace with your color resource

        // Show a toast with the selected option
        Toast.makeText(this, "Selected: " + selectedOption, Toast.LENGTH_SHORT).show();

        // Add a delay before navigating to the next activity
        new Handler().postDelayed(() -> {
            // Navigate to the next activity after the delay
            Intent intent = new Intent(Question1.this, Question2.class);
            startActivity(intent);
        }, 1000); // 2000 milliseconds (2 seconds) delay
    }

    private void resetButtonStyles() {
        // Reset button styles to default for all buttons
        btn7HoursOrLess.setAlpha(0.5f);
        btn7HoursOrLess.setBackgroundColor(getResources().getColor(androidx.cardview.R.color.cardview_shadow_end_color));

        btn7To9Hours.setAlpha(0.5f);
        btn7To9Hours.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));

        btn9To12Hours.setAlpha(0.5f);
        btn9To12Hours.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));

        btn12HoursOrMore.setAlpha(0.5f);
        btn12HoursOrMore.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));
    }
}
