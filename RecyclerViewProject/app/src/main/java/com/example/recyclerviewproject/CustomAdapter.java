package com.example.recyclerviewproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    String[] moviesName;
    int[] movies;

    public CustomAdapter(Context context, String[] moviesName, int[] movies) {
        this.context = context;
        this.moviesName = moviesName;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.movies.setImageResource(movies[position]);

        holder.moviesName.setText(moviesName[position]);

    }

    @Override
    public int getItemCount() {
        return movies.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movies;
        TextView moviesName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movies = itemView.findViewById(R.id.posterViewId);
            moviesName = itemView.findViewById(R.id.titleViewId);
        }

    }

}
