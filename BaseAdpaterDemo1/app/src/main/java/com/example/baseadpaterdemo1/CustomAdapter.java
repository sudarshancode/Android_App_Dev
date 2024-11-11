package com.example.baseadpaterdemo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    String[] countryNames;
    int[] flags;
    Context context;
    LayoutInflater inflater;
    public CustomAdapter(Context context, String[] countryNames,int[] flags){
        this.countryNames=countryNames;
        this.context=context;
        this.flags=flags;
    }

    @Override
    public int getCount() {
        return countryNames.length;
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
            inflater= ( LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.sample_view,parent,false);
        }
        ImageView imageView= (ImageView) convertView.findViewById(R.id.imageviewId);
        TextView textView=(TextView) convertView.findViewById(R.id.countryNameId);

        imageView.setImageResource(flags[position]);
        textView.setText(countryNames[position]);
        return convertView;
    }
}
