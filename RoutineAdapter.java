package com.example.riseandshine;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riseandshine.api.RetrofitClient;
import com.example.riseandshine.model.GetAllUserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder> {

    private ArrayList<GetAllUserResponse.User.Task> tasks;
    private Context context;

    public RoutineAdapter(ArrayList<GetAllUserResponse.User.Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public RoutineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routine_list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineAdapter.ViewHolder holder, int position) {
        GetAllUserResponse.User.Task task = tasks.get(position);

        holder.taskName.setText(task.getTaskName());
        holder.taskTime.setText(task.getTaskTime());

        holder.deleteTask.setOnClickListener(v -> {
            deleteTask(task.getId(), position);

            Log.i("task id", task.getId());
        });
    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }

    private void deleteTask(String taskId, int position) {

        String userId = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE).getString("userId", null);

        if (taskId == null) {
            Toast.makeText(context, "Task ID is null. Cannot delete task.", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (userId == null) {
            Toast.makeText(context, "User ID not found. Please log in again.", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            Call<Void> call = RetrofitClient.getApiService().deleteTask(userId, taskId);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Task deleted successfully", Toast.LENGTH_SHORT).show();
                        tasks.remove(position);
                        notifyItemRemoved(position);

                    } else {
                        Toast.makeText(context, "Failed to delete task. Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("RoutineAdapter", "Error deleting task: " + t.getMessage());
                    Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView taskName, taskTime, deleteTask;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            taskName = itemView.findViewById(R.id.taskName);
            taskTime = itemView.findViewById(R.id.taskTime);
            deleteTask = itemView.findViewById(R.id.delete);
        }
    }
}
