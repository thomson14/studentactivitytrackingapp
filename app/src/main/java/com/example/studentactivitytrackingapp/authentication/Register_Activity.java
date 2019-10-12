package com.example.studentactivitytrackingapp.authentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.studentactivitytrackingapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;

import java.util.HashMap;

public class Register_Activity extends AppCompatActivity {

    MaterialAutoCompleteTextView username, email, phone_num, gender, password;
    Button btn_Register;
    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressBar registerProgress;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        registerProgress = findViewById(R.id.registerProgress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone_num = findViewById(R.id.mobile_number);
        gender = findViewById(R.id.gender);
        password = findViewById(R.id.password);

        btn_Register = findViewById(R.id.btn_register);

        auth = FirebaseAuth.getInstance();

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerProgress.setVisibility(View.VISIBLE);
                String txt_username = username.getText().toString();
                String txt_email = email.getText().toString();
                String txt_phone = phone_num.getText().toString();
                String txt_gender = gender.getText().toString();
                String txt_password = password.getText().toString();


                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_phone) || TextUtils.isEmpty(txt_gender) || TextUtils.isEmpty(txt_password)) {

                    Toast.makeText(Register_Activity.this, "All Fields are Required", Toast.LENGTH_SHORT).show();

                } else if (txt_password.length() < 6) {

                    Toast.makeText(Register_Activity.this, "Password must be at 6 character", Toast.LENGTH_SHORT).show();

                } else {
                    register(txt_username, txt_email, txt_phone, txt_gender, txt_password);
                }


            }
        });


    }

    private void register(final String username, final String email, final String phone_num, final String gender, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            registerProgress.setVisibility(View.INVISIBLE);
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String UserID = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(UserID);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", UserID);
                            hashMap.put("username", username);
                            hashMap.put("Mobile", phone_num);
                            hashMap.put("gender", gender);
                            hashMap.put("imageURL", "default");


                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();


                                    }
                                }
                            });


                        } else {
                            Toast.makeText(Register_Activity.this, "You Can't register with this email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
