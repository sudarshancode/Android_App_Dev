package com.example.myspinnerdemo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    String[] countryName;
    int[] flags;
    Context context;
    LayoutInflater layoutInflater;

    CustomAdapter(Context context,String[] countryName,int[] flags){
        this.context=context;
        this.countryName=countryName;
        this.flags=flags;
    }
    @Override
    public int getCount() {
        return countryName.length;
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
        if(convertView ==null){
            layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.sample_view,parent,false);
        }

        ImageView imageView=(ImageView) convertView.findViewById(R.id.flagId);
        TextView textView=(TextView) convertView.findViewById(R.id.countryNameId);

        imageView.setImageResource(flags[position]);
        textView.setText(countryName[position]);

        return convertView;
    }
}
