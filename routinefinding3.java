package com.example.riseandshine;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class routinefinding3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routinefinding3);

        Thread timer=new Thread()
        {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally
                {
                    Intent i=new Intent(routinefinding3.this,morningroutine.class);
                    finish();
                    startActivity(i);
                }
            }
        };
        timer.start();

    }
}