package com.ny.ex13_progressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class View03 extends AppCompatActivity {
    TextView tv1,tv2;
    ProgressBar progressBar5, progressBar6;
    Button btn3;
    Handler handler = new Handler();
    int i, j;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view3);

        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        progressBar5=findViewById(R.id.progressBar5);
        progressBar6=findViewById(R.id.progressBar6);
        btn3=findViewById(R.id.btn3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (i=0; i<101; i++){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar5.setProgress(i);
                                    // 스레드 안에서는 view에게 직접 값을 전달 할 수 없다.
                                    // handler를 이용해서 값을 전달 해야 된다.
                                    tv1.setText(i+"%");
                                }
                            });
                            SystemClock.sleep((int)(Math.random()*300));
                        }
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (j=0; j<101; j++){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar6.setProgress(j);
                                    // 스레드 안에서는 view에게 직접 값을 전달 할 수 없다.
                                    // handler를 이용해서 값을 전달 해야 된다.
                                    tv2.setText(j+"%");
                                }
                            });
                            SystemClock.sleep((int)(Math.random()*300));
                        }
                    }
                }).start();
            }
        });
    }
}
