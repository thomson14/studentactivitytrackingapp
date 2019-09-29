package com.example.studentactivitytrackingapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoList extends AppCompatActivity {

    EditText edt_work,edt_hour,edt_Minute;
    Button btn_add;
    ListView lv_List;
    private ArrayList<ToDoItem> array;
    private ToDoAdapter adapter;


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("To DO List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        btn_add = findViewById(R.id.btn_add);
        edt_work = findViewById(R.id.edt_work);
        edt_hour = findViewById(R.id.Edit_hour);
        edt_Minute = findViewById(R.id.edit_minut);
        lv_List = findViewById(R.id.list_view);

        array = new ArrayList<ToDoItem>();
        adapter = new ToDoAdapter(this, R.layout.to_do_custom_list_view,array);
        lv_List.setAdapter(adapter);
        Log.d("data","adapter"+adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddWork();
            }
        });


    }

    public void AddWork() {

        if (edt_work.getText().toString().equals("") || edt_hour.getText().toString().equals("") || edt_Minute.getText().toString().equals("")) {

            AlertDialog.Builder builder = new AlertDialog.Builder(ToDoList.this);
            builder.setTitle("Info Missing");
            builder.setMessage("please add Info...");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create().show();
        } else {
            final String work = edt_work.getText().toString();
            final String time = edt_hour.getText().toString() + "h :" + edt_Minute.getText().toString() + "m";
            ToDoItem item = new ToDoItem(work, time);
            array.add(0, item);
            adapter.notifyDataSetChanged();
            edt_work.setText("");
            edt_hour.setText("");
            edt_Minute.setText("");
            edt_work.requestFocus();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.to_do_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.btn_delete)
        {
            if (array.size() > 0)
            {
                for (int i = 0; i < array.size(); i++)
                {
                    if (array.size() < i) {
                        break;
                    }
                    if (array.get(i).isChecked()) {
                        array.remove(i);
                        adapter.notifyDataSetChanged();
                        continue;
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
