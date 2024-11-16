package com.example.retrofitrestapi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitrestapi.Model.PhotoModel;
import com.example.retrofitrestapi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    List<PhotoModel> photoModelList;

    public CustomAdapter(Context context, List<PhotoModel> photoModelList) {
        this.context = context;
        this.photoModelList=photoModelList;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        PhotoModel photoModel=photoModelList.get(position);

        holder.title.setText(photoModel.getTitle());
        Picasso.get().load(photoModel.getThumbnailUrl()).into(holder.thumbnailImage);
    }

    @Override
    public int getItemCount() {
        return photoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImage;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailImage=itemView.findViewById(R.id.imageViewId);
            title=itemView.findViewById(R.id.titleViewId);
        }
    }
}
