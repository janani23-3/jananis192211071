package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class editname extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editname); // Replace with your XML layout file name.

        // Find the Done button by ID
        Button doneButton = findViewById(R.id.doneButton);

        // Set an onClickListener for the Done button
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to another activity
                Intent intent = new Intent(editname.this, profilepage.class);
                startActivity(intent);
            }
        });
    }
}
