package com.ny.ex65_network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3;
    ListView list;
    String result;
    String msg;
    final static String IP = "172.16.14.1";
    final static int PORT = 7888;
    Socket socket ;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        list = findViewById(R.id.list);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        result = callServer(IP,PORT, "test");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).start();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        result = callServer(IP,PORT, "db");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String[] str = null;
                                try {
                                    str = result.split(",");
                                    ArrayAdapter<String> adapter =
                                            new ArrayAdapter<>(MainActivity.this,
                                                    android.R.layout.simple_list_item_1,str);
                                    list.setAdapter(adapter);
                                }catch (Exception e){
                                }
                            }
                        });

                    }
                }).start();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private String callServer(String ip, int port, String msg){
        String str = "";
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            socket = new Socket(ip, port);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            msg = msg + System.getProperty("line.separator");
            writer.write(msg);
            writer.flush();

            str = reader.readLine();
        }catch (Exception e){
        }
        return str;
    }
}
