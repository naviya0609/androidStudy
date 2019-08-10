package com.ny.ex63_network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText editText1, editText2, editText3;
    Button button1;
    TextView textView1;

    Handler handler = new Handler();
    String ip, port, msg, result, str;
    Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        textView1 = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ip = editText1.getText().toString();
               port = editText2.getText().toString();
               msg = editText3.getText().toString();

               // 네트워크 : 스레드처리
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 서버에 가기 위한 메소드
                        str = callServer(ip, port,msg);
                        result = str + "\n"+ result;

                        // View 정보 넣기
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textView1.setText(result);
                                editText3.setText("");
                            }
                        });
                    }
                }).start();
            }
        });
    }
    private String callServer(String ip, String port, String msg){
        String res = "";
        // 정보 입출력
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            socket = new Socket(ip, Integer.parseInt(port));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.write(msg+System.getProperty("line.separator"));
            writer.flush();

            res = reader.readLine();
        }catch (Exception e){
        }
        return res;
    }
}
