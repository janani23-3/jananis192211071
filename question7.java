package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class question7 extends AppCompatActivity {

    private List<String> selectedoption = new ArrayList<>();
    private Button[] buttons; // To store the buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question7);

        // Initialize buttons
        Button btnOption1 = findViewById(R.id.option1);
        Button btnOption2 = findViewById(R.id.option2);
        Button btnOption3 = findViewById(R.id.option3);
        Button btnOption4 = findViewById(R.id.option4);
        Button btnOption5 = findViewById(R.id.option5);
        Button btnOption6 = findViewById(R.id.option6);
        Button btnNext = findViewById(R.id.next_button);

        buttons = new Button[]{btnOption1, btnOption2, btnOption3, btnOption4, btnOption5, btnOption6};

        // Set toggle listeners for each button
        setToggleListener(btnOption1, "To finally wake up before my alarm yells at me");
        setToggleListener(btnOption2, "To trick my brain into thinking I’m a morning person");
        setToggleListener(btnOption3, "To become so productive that even coffee is jealous");
        setToggleListener(btnOption4, "To stop snoozing 10 times before getting up");
        setToggleListener(btnOption5, "To trick my life together... at least until noon");
        setToggleListener(btnOption6, "To actually follow a routine, instead of just thinking about it");

        // Next button click listener
        btnNext.setOnClickListener(v -> {
            if (selectedoption.isEmpty()) {
                Toast.makeText(question7.this, "Please select at least one habit", Toast.LENGTH_SHORT).show();
            } else {
                // Save the selected habits or pass to the next screen
                Toast.makeText(question7.this, "Selected Habits: " + selectedoption, Toast.LENGTH_SHORT).show();

                // Example of moving to the next screen with selected habits
                Intent intent = new Intent(question7.this, inspirepage.class);
                startActivity(intent);
            }
        });
    }

    private void setToggleListener(Button button, final String habit) {
        button.setOnClickListener(v -> {
            if (v.isSelected()) {
                // Deselect button and remove the habit
                selectedoption.remove(habit);
                v.setSelected(false);
                v.setBackgroundColor(getResources().getColor(androidx.cardview.R.color.cardview_shadow_end_color)); // Reset to default color
            } else {
                // Select button and add the habit
                selectedoption.add(habit);
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
        });
    }
}
