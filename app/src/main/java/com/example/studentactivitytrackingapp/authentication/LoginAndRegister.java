package com.example.studentactivitytrackingapp.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import com.example.studentactivitytrackingapp.MainActivity;
import com.example.studentactivitytrackingapp.R;



public class LoginAndRegister extends AppCompatActivity {

  Button btn_register,btn_login,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_and__regis);

        btn_login =  findViewById(R.id.login);
        btn_register =  findViewById(R.id.register);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAndRegister.this, LoginActivity.class));
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAndRegister.this, RegisterActivity.class));
            }
        });


    }
}
