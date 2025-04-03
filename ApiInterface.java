package com.example.riseandshine.api;

import com.example.riseandshine.AgeRequest;
import com.example.riseandshine.GenderRequest;
import com.example.riseandshine.TaskRequest;
import com.example.riseandshine.TaskResponse;
import com.example.riseandshine.UserResponse;
import com.example.riseandshine.WakeUpTimeRequest;
import com.example.riseandshine.model.GetAllUserResponse;
import com.example.riseandshine.model.LoginRequest;
import com.example.riseandshine.model.LoginResponse;
import com.example.riseandshine.model.SignUpRequest;
import com.example.riseandshine.model.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    // Create a new user
    @POST("user/create")
    Call<SignUpResponse> create(@Body SignUpRequest signUpRequest);

    // Save or update gender for a specific user
    @POST("user/saveGender/{id}")
    Call<UserResponse> saveGender(@Path("id") String userID, @Body GenderRequest genderRequest);

    // Save or update wake-up time for a specific user
    @POST("user/saveWakeUpTime/{userId}")
    Call<UserResponse> saveWakeUpTime(@Path("userId") String userId, @Body WakeUpTimeRequest wakeUpTimeRequest);

    // Save or update age for a specific user
    @POST("user/saveAge/{userId}")
    Call<Void> saveAge(@Path("userId") String userId, @Body AgeRequest ageRequest);

    // Get user profile
    @GET("user/profile/{userId}")
    Call<UserResponse> getUserProfile(@Path("userId") String userId);

    // Delete task for a specific user
    @DELETE("user/deleteTask/{userId}/{taskId}")
    Call<Void> deleteTask(@Path("userId") String userId, @Path("taskId") String taskId);

    // Add a task for the user
    @POST("user/addTask/{userId}")
    Call<TaskResponse> addTask(@Path("userId") String userId, @Body TaskRequest taskRequest);

    @GET("user/getAllUsers")
    Call<GetAllUserResponse> getAllUser();

    @POST("user/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
