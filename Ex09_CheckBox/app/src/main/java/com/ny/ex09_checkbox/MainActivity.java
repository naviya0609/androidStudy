package com.ny.ex09_checkbox;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox1, checkBox2;
    TextView textView1,textView2,textView3;
    ToggleButton toggleButton1;
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        checkBox1= findViewById(R.id.checkBox1);
        checkBox2= findViewById(R.id.checkBox2);
        textView1= findViewById(R.id.textView1);
        textView2= findViewById(R.id.textView2);
        textView3= findViewById(R.id.textView3);
        toggleButton1= findViewById(R.id.toggleButton);
        switch1= findViewById(R.id.switch1);

        //checkbox, togglebutton, switch 이벤트리스너 동일 : compoundButton

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                //선택유무는 boolean을 통해서 알수있음 선택됨 true, 해제시 false.
                if(b) {
                    textView1.setTextSize(50);
                }else {
                    textView1.setTextSize(20);
                }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                //선택유무는 boolean을 통해서 알수있음 선택됨 true, 해제시 false.
                if(b) {
                    //textView1.setTextColor(Color.MAGENTA);
                    textView1.setTextColor(Color.rgb(255,153,102));
                }else {
                    textView1.setTextColor(Color.BLUE);
                }
            }
        });


        toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    textView2.setTextSize(50);
                    textView2.setTextColor(Color.RED);
                }else{
                    textView2.setTextSize(20);
                    textView2.setTextColor(Color.GREEN);
                }

            }
        });


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    textView3.setTextSize(40);
                    textView3.setText("Magenta");
                    textView3.setTextColor(Color.MAGENTA);
                    textView3.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                }else{
                    textView3.setTextSize(20);
                    textView3.setText("Green");
                    textView3.setTextColor(Color.GREEN);
                    textView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                }

            }
        });




    }
}
