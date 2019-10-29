package com.example.studentactivitytrackingapp.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import com.example.studentactivitytrackingapp.MainActivity;
import com.example.studentactivitytrackingapp.R;

import life.sabujak.roundedbutton.RoundedButton;

public class LoginAndRegister extends AppCompatActivity {

    RoundedButton btn_register,btn_login,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_and__regis);

        btn_login = (RoundedButton) findViewById(R.id.login);
        btn_register = (RoundedButton) findViewById(R.id.register);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAndRegister.this, MainActivity.class));
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
