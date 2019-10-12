package com.example.studentactivitytrackingapp.todoList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.studentactivitytrackingapp.R;

import java.util.ArrayList;

public class ToDoAdapter extends ArrayAdapter<ToDoItem> {

    private Activity context;
    private int id;
    private ArrayList<ToDoItem> array;


    public ToDoAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<ToDoItem> objects) {
        super(context, resource, objects);

                this.context=context;
                this.id = resource;
                this.array = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView==null)
        {
            LayoutInflater inflater  = context.getLayoutInflater();
            convertView= inflater.inflate(id,null);
        }

        final ToDoItem item = array.get(position);
        TextView txtWork = (TextView) convertView.findViewById(R.id.txt_work);
        TextView txtTime = convertView.findViewById(R.id.txt_time);
        CheckBox checkBox = convertView.findViewById(R.id.cbx_work);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                item.setChecked(isChecked);
            }
        });


        txtWork.setText(item.getWork());
        txtTime.setText(item.getTime());
        checkBox.setChecked(item.isChecked());


        return convertView;
    }
}
