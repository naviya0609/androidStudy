package com.ny.ex64_network;

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
    static final String IP = "172.16.14.1";
    static final int PORT = 7880;
    Socket socket;
    Handler handler = new Handler();
    BufferedWriter writer;
    BufferedReader reader;
    String textReceiver;
    EditText edtMsg;
    Button send;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMsg = findViewById(R.id.edtMsg);
        send = findViewById(R.id.send);
        result = findViewById(R.id.result);

        new Thread(){
            @Override
            public void run() {
               connet();
            }
        }.start();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            sendMessage(edtMsg.getText().toString());
                        }
                    }).start();
                    edtMsg.setText("");
                }catch (Exception e){
                }
            }
        });
    }

    private void connet(){
        try {
            socket = new Socket(IP,PORT);
            if(socket != null){
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while(socket.isConnected()){
                    try {
                        textReceiver = reader.readLine();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                result.setText(result.getText().toString()+"\n"+textReceiver);
                            }
                        });
                    }catch (Exception e){
                    }
                }
            }
        }catch (Exception e){
        }
    }
    private void sendMessage(String msg){
        try{
            writer.write(msg+System.getProperty("line.separator"));
            writer.flush();
        }catch (Exception e){
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try{
            socket.close();
        }catch (Exception e){
        }
    }
}
