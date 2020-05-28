package com.example.todo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class MyAdapter extends ArrayAdapter {
    private ArrayList<String> data;
    Activity context;
    public MyAdapter(@NonNull Activity context, ArrayList<String> data) {
        super(context,R.layout.viewlayout,data);
        this.data=data;
        this.context=context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View v=layoutInflater.inflate(R.layout.viewlayout,null,true);
        TextView textView=v.findViewById(R.id.textview);
        CheckBox checkBox=(CheckBox) v.findViewById(R.id.checkbox);
        textView.setText(data.get(position));
        checkBox.setText(data.get(position));
        return v;
    }
}
