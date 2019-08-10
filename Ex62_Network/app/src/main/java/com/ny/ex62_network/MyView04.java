package com.ny.ex62_network;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyView04 extends AppCompatActivity {
    Button button7;
    TextView textView3 ;
    FileInputStream fis;
    URL url;
    String result;
    String boundary = "*****";
    String lineEnd = "\r\n";
    String twoHyphens = "--";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myview04);
        button7 = findViewById(R.id.button7);
        textView3 = findViewById(R.id.textView3);

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String file = Environment.getDataDirectory()+"/data/"+getPackageName()
                        +"/files/test.txt";
                fileUpload(file);
            }
        });
    }
    private void fileUpload(final String file){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 서버주소와 첨부내용
                httpFileUpload("http://172.16.14.1:8090/server01/upload.jsp",file);
            }
        }).start();
    }
    private void httpFileUpload(String urlString, String file){
        try {
            fis = new FileInputStream(file);
            url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            // MultipartRequest는 무조건 POST 방식을 사용해야됨
            conn.setRequestMethod("POST");
            // 헤더 설정
            conn.setRequestProperty("Connection","Keep-Alive");
            conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);

            // 데이터 쓰기
            DataOutputStream dos =
                    new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens+boundary+lineEnd);
            dos.writeBytes("Content-Disposition: form-data;name=\"uploadedfile\";" +
                    "filename=\""+file+"\""+lineEnd);
            dos.writeBytes(lineEnd);

            // 실제 데이터 읽기
            int bytsAvailable = fis.available();
            int maxBufferSize = 1024;
            int buffereSize = Math.min(bytsAvailable,maxBufferSize);
            byte[] buffer = new byte[buffereSize];
            int byteRead = fis.read(buffer,0,buffereSize);

            while(byteRead > 0){
                dos.write(buffer,0,buffereSize);
                bytsAvailable = fis.available();
                buffereSize = Math.min(bytsAvailable,maxBufferSize);
                byteRead = fis.read(buffer,0,buffereSize);
            }
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens+boundary+lineEnd);
            dos.flush();
            fis.close();

            // 서버정보 읽기
            int ch;
            InputStream is = conn.getInputStream();
            StringBuffer sb = new StringBuffer();
            while((ch=is.read())!= -1){
                sb.append((char)ch);
            }
            result =sb.toString().trim();
            handler.sendEmptyMessage(0);
            dos.close();
        }catch (Exception e){
        }
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            handler.sendEmptyMessage(0);
            textView3.setText(result);
        }
    };
}
