package com.example.worklist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worklist.Model.WorkListModel;
import com.example.worklist.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private final List<WorkListModel> task_list;
    Context context;

    public CustomAdapter(Context context, List<WorkListModel> task_list){
        this.task_list = task_list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(view);
    }

    private boolean isChecked(int value) {
        return value == 1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorkListModel workListModel = task_list.get(position);
        holder.checkBox.setText(workListModel.getTask());
        holder.checkBox.setChecked(isChecked(workListModel.getStatus()));
    }

    @Override
    public int getItemCount() {
        return task_list != null ? task_list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBoxId);
        }
    }
}
