package com.example.studentactivitytrackingapp.studyTimer;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentactivitytrackingapp.R;

import java.util.Locale;

public class StudyTimerActivity extends AppCompatActivity {

    EditText time_title,time_discription,edit_time_picker;
    TextView text_count_down;
    Button btn_time_picker;
    private int  mHour, mMinute;

    //dfhegfyegfyue


    TextView btn_reset,btn_start_pause,getText_count_down;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long startTimeInMillis;
    private long getTimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_timer);
        time_title = findViewById(R.id.edit_title_time);
        time_discription = findViewById(R.id.edit_Description_time);
        edit_time_picker = findViewById(R.id.edit_Time_picker);
        btn_time_picker = findViewById(R.id.btn_time_picker);
        text_count_down = findViewById(R.id.text_count_down);

        btn_start_pause = findViewById(R.id.button_start_pause);
        btn_reset = findViewById(R.id.button_reset);
        getText_count_down = findViewById(R.id.text_count_down);


        btn_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = edit_time_picker.getText().toString();
                if (input.length()==0){

                    Toast.makeText(StudyTimerActivity.this, "Field cant be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                long millisInput = Long.parseLong(input) * 60000;

                if(millisInput == 0 ){

                    Toast.makeText(StudyTimerActivity.this, "please enter a postive no.", Toast.LENGTH_SHORT).show();
                    return;
                }

                setTime(millisInput);

                edit_time_picker.setText("");
            }
        });


        btn_start_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(timerRunning){

                    pauseTimer();

                }else {
                    startTimer();
                }
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTimer();
            }
        });
        updateCountDownText();
    }


    private void  setTime(long milliseconds){

        startTimeInMillis = milliseconds;
        resetTimer();
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(getTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                getTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {

                timerRunning = false;
                btn_start_pause.setText("Start");
                btn_start_pause.setVisibility(View.INVISIBLE);
                btn_reset.setVisibility(View.VISIBLE);

            }
        }.start();

        timerRunning = true;
        btn_start_pause.setText("Pause");
        btn_reset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer(){

        countDownTimer.cancel();
        timerRunning = false;
        btn_start_pause.setText("start");
        btn_reset.setVisibility(View.VISIBLE);
    }

    private void  resetTimer(){

        getTimeLeftInMillis = startTimeInMillis;
        updateCountDownText();
        btn_reset.setVisibility(View.INVISIBLE);
        btn_start_pause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText(){
        int hours = (int) (getTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((getTimeLeftInMillis / 1000) % 3600) / 60;
        int secconds = (int) (getTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted;

        if (hours > 0){

            timeLeftFormatted = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secconds);
        }
        else {

            timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,secconds);
        }


        text_count_down.setText(timeLeftFormatted);

    }
}
