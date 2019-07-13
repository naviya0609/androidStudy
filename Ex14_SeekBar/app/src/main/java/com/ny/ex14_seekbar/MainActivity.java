package com.ny.ex14_seekbar;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Seekbar는 progress 와 같은방법으로 이벤트 처리 (thread)

    SeekBar seekBar1;
    TextView textView1;
    Button button1;
    Handler handler = new Handler();
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        seekBar1 = findViewById(R.id.seekBar1);
        button1 = findViewById(R.id.button1);
        textView1 = findViewById(R.id.textView1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for(i=seekBar1.getProgress(); i<=seekBar1.getMax(); i=i+(int)(Math.random()*5)){
                            //위치값 얻어내서 시작값 변경
                            //textView1.setText("진행률 : " +seekBar1.getProgress()+"%");
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    seekBar1.setProgress(i);
                                    textView1.setText("진행률 : " +seekBar1.getProgress()+"%");
                                }
                            });
                            SystemClock.sleep(500);
                        }
                    }
                }).start();
            }
        });
    }
}
