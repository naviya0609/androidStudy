package com.ny.ex13_progressbar;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class View02 extends AppCompatActivity {
    ProgressBar progressBar3, progressBar4;
    Button btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view2);

        progressBar3 =findViewById(R.id.progressBar3);
        progressBar4 =findViewById(R.id.progressBar4);
        btn2 =findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<101; i++){
                            progressBar3.setProgress(i);
                            SystemClock.sleep((int)(Math.random()*300));
                        }

                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<101; i++){
                            progressBar4.setProgress(i);
                            SystemClock.sleep((int)(Math.random()*300));
                        }
                    }
                }).start();
            }
        });

    }
}
