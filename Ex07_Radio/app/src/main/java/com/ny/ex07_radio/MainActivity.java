package com.ny.ex07_radio;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view01);

        rg = findViewById(R.id.rg);
        textView1 = findViewById(R.id.textView1);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                // int i => 선택된 라디오 버튼을 뜻한다.
                switch (i){
                    case R.id.radioButton1: textView1.setTextColor(Color.RED); break;
                    case R.id.radioButton2: textView1.setTextColor(Color.GREEN); break;
                    case R.id.radioButton3: textView1.setTextColor(Color.BLUE); break;
                }
            }
        });

    }
}
