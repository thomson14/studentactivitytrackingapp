package com.example.studentactivitytrackingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Log_and_Regis extends AppCompatActivity {

    Button btn_register,btn_login,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_and__regis);

        btn_login = (Button) findViewById(R.id.login);
        btn_register = (Button) findViewById(R.id.register);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Log_and_Regis.this,Login_Activity.class));
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Log_and_Regis.this,Register_Activity.class));
            }
        });


    }
}
