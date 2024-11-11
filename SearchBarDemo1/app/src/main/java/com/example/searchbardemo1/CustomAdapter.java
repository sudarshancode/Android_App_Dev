package com.example.searchbardemo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    String[] movieName;
    int[] poster;
    Context context;
    LayoutInflater inflater;
    public CustomAdapter(Context context,String[] movieName,int[] poster){
        this.context=context;
        this.movieName=movieName;
        this.poster=poster;
    }
    @Override
    public int getCount() {
        return movieName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView=inflater.inflate(R.layout.simple_view,parent,false);

        }

        TextView textView= (TextView) convertView.findViewById(R.id.postNameId);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.posterId);


        textView.setText(movieName[position]);
        imageView.setImageResource(poster[position]);

        return convertView;
    }

}
