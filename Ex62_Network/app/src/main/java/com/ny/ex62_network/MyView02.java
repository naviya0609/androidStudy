package com.ny.ex62_network;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyView02 extends AppCompatActivity {
    Button button5 ;
    TextView textView2;
    String html;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myview02);

        button5 = findViewById(R.id.button5);
        textView2 = findViewById(R.id.textView2);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 스레드 실행 시킴
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // html = download("http://google.com");
                        html = download("http://172.16.14.1:8090/server01/network01.jsp");
                        handler.sendEmptyMessage(0);
                    }
                }).start();
            }
        });
    }
    private String download(String addr){
        StringBuffer sb = new StringBuffer();
        try{
            URL url = new URL(addr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            // 연결 성공
            if(conn != null){
                // 타임 아웃 설정
                conn.setConnectTimeout(10000);
                // 캐쉬 사용 안함
                conn.setUseCaches(false);
                if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                    // 정보 읽기
                    BufferedReader br =
                            new BufferedReader(
                                    new InputStreamReader(
                                            conn.getInputStream(),"utf-8"));

                     while(true){
                         // 한 줄씩 읽어오기
                         String line = br.readLine();
                         if(line == null) break; // 더이상 읽을 수 없으면 탈출
                         sb.append(line+"\n");
                     }
                     br.close();
                }
                conn.disconnect();
            }
        }catch (Exception e){
        }
        return sb.toString();
    }

    // 스레드 처리 시 View에 정보를 넣은 방법
   Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            handler.sendEmptyMessage(0);
            textView2.setText(html);
        }
    };

}
