package com.example.riseandshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riseandshine.api.ApiInterface;
import com.example.riseandshine.api.RetrofitClient;
import com.example.riseandshine.model.GetAllUserResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class morningroutine extends AppCompatActivity {

    private Button quickAddButton;
    private ImageView planIcon, searchIcon, profileIcon;
    private RoutineAdapter adapter;
    private ArrayList<GetAllUserResponse.User.Task> listOfTask;
    private RecyclerView recyclerView;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morningroutine);

        // Initialize Views
        quickAddButton = findViewById(R.id.quickadd);
        planIcon = findViewById(R.id.clockk);
        searchIcon = findViewById(R.id.search);
        profileIcon = findViewById(R.id.profileIcon);
        recyclerView = findViewById(R.id.recyclerView);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        userId = prefs.getString("userId", null);

        listOfTask = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RoutineAdapter(listOfTask, this);
        recyclerView.setAdapter(adapter);

        // Load routines
        loadRoutines();

        // Set up intents for buttons and icons
        quickAddButton.setOnClickListener(v -> {
            Intent quickAddIntent = new Intent(morningroutine.this, quickadd.class);
            startActivity(quickAddIntent);
        });

        planIcon.setOnClickListener(v -> {
            Intent planIntent = new Intent(morningroutine.this, morningroutine.class);
            startActivity(planIntent);
        });

        searchIcon.setOnClickListener(v -> {
            Intent searchIntent = new Intent(morningroutine.this, explorepage.class);
            startActivity(searchIntent);
        });

        profileIcon.setOnClickListener(v -> {
            Intent profileIntent = new Intent(morningroutine.this, profilepage.class);
            startActivity(profileIntent);
        });
    }

    private void loadRoutines() {
        Call<GetAllUserResponse> call = RetrofitClient.getClient().create(ApiInterface.class).getAllUser();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<GetAllUserResponse> call, Response<GetAllUserResponse> response) {
                if (response.isSuccessful()) {
                    List<GetAllUserResponse.User> userList = response.body().getUsers();

                    for (GetAllUserResponse.User user : userList) {
                        if (user.get_id().equals(userId)) {
                            for (GetAllUserResponse.User.Task task : user.getTasks()) {
                                listOfTask.add(task);
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    try {
                        Log.d("TAG", "onResponse: " + response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllUserResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }
}
