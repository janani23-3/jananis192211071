package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class question5 extends AppCompatActivity {

    private List<String> selectedHabits = new ArrayList<>();
    private Button[] buttons; // To store the buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);

        // Initialize buttons
        Button btnProcrastination = findViewById(R.id.option1);
        Button btnPhoneUsage = findViewById(R.id.option2);
        Button btnRushed = findViewById(R.id.option3);
        Button btnSleepSchedule = findViewById(R.id.option4);
        Button btnLackOfExercise = findViewById(R.id.option5);
        Button btnDifficultyFocusing = findViewById(R.id.option6);
        Button btnNotPlanning = findViewById(R.id.option7);
        Button btnNext = findViewById(R.id.next_button);

        buttons = new Button[]{btnProcrastination, btnPhoneUsage, btnRushed, btnSleepSchedule, btnLackOfExercise, btnDifficultyFocusing, btnNotPlanning};

        // Set toggle listeners for each button
        setToggleListener(btnProcrastination, "Procrastinating in the morning");
        setToggleListener(btnPhoneUsage, "Spending too much time on your phone");
        setToggleListener(btnRushed, "Starting the day feeling rushed");
        setToggleListener(btnSleepSchedule, "Inconsistent sleep schedule");
        setToggleListener(btnLackOfExercise, "Lack of exercise in the morning");
        setToggleListener(btnDifficultyFocusing, "Difficulty focusing after waking up");
        setToggleListener(btnNotPlanning, "Not planning or organizing the day ahead");

        // Next button click listener
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedHabits.isEmpty()) {
                    Toast.makeText(question5.this, "Please select at least one habit", Toast.LENGTH_SHORT).show();
                } else {
                    // Save the selected habits or pass to the next screen
                    Toast.makeText(question5.this, "Selected Habits: " + selectedHabits, Toast.LENGTH_SHORT).show();

                    // Example of moving to the next screen with selected habits
                    Intent intent = new Intent(question5.this, question6.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setToggleListener(Button button, final String habit) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    // Deselect button and remove the habit
                    selectedHabits.remove(habit);
                    v.setSelected(false);
                    v.setBackgroundColor(getResources().getColor(androidx.cardview.R.color.cardview_shadow_end_color)); // Reset to default color
                } else {
                    // Select button and add the habit
                    selectedHabits.add(habit);
                    v.setSelected(true);
                    v.setBackgroundColor(getResources().getColor(androidx.cardview.R.color.cardview_shadow_start_color)); // Highlight the button color
                }

                // Dim all other buttons
                for (Button otherButton : buttons) {
                    if (otherButton != v) {
                        otherButton.setAlpha(0.6f); // Dim non-selected buttons
                    } else {
                        otherButton.setAlpha(1.0f); // Keep the selected button at full opacity
                    }
                }
            }
        });
    }
}
