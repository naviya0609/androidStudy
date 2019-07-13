package com.ny.ex21_datepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
DatePicker datePicker1;
TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker1 = findViewById(R.id.datePicker1);
        textView1= findViewById(R.id.textView1);

        //이벤트처리
        datePicker1.init(datePicker1.getYear(), datePicker1.getMonth(), datePicker1.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textView1.setText(String.format("%d년 %d월 %d일", year , monthOfYear+1,dayOfMonth));
            }
        });
    }
}
