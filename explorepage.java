package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class explorepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorepage); // Ensure this matches your XML filename

        // Initialize Bottom Navigation Icons
        ImageView planIcon = findViewById(R.id.clockk); // Matches XML id 'clockk'
        ImageView ideaIcon = findViewById(R.id.search); // Matches XML id 'search'
        ImageView profileIcon = findViewById(R.id.profileIcon); // Matches XML id 'profileIcon'

        // Plan Icon Click Listener
        planIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(explorepage.this, morningroutine.class);
                startActivity(intent);
            }
        });

        // Idea Icon Click Listener
        ideaIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(explorepage.this, explorepage.class);
                startActivity(intent);
            }
        });

        // Profile Icon Click Listener
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(explorepage.this, profilepage.class);
                startActivity(intent);
            }
        });
    }
}
