package com.ny.ex23_dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button button1, button2;
    TextView textView1;
    int mYear, mMonth, mDay, mHour, mMinitue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        textView1 = findViewById(R.id.textView1);

        //다이얼로그 초기값지정
        Calendar now= Calendar.getInstance();
        mYear       = now.get(Calendar.YEAR);
        mMonth      = now.get(Calendar.MONTH); //0~11까지 mMonth+1 필요
        mDay        = now.get(Calendar.DATE);
        mHour       = now.get(Calendar.HOUR);
        mMinitue   = now.get(Calendar.MINUTE);

        button1.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mYear = year;
                        mMonth = month;
                        mDay =dayOfMonth;
                       textView1.setText(String.format("%d, %d, %d. %d : %d", mYear, mMonth+1, mDay, mHour, mMinitue));
                    }
                }, mYear, mMonth, mDay).show(); //다이얼로그 .show() 필요
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                        mMinitue = minute;
                        textView1.setText(String.format("%d, %d, %d. %d : %d", mYear, mMonth+1, mDay, mHour, mMinitue));
                    }
                }, mHour, mMinitue, true).show(); //다이얼로그 .show() 필요
            }
        });
    }
}
