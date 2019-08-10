package com.ny.ex62_network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyView01 extends AppCompatActivity {
    TextView textView1;
    String result = "";
    ConnectivityManager manager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myview01);
        textView1 = findViewById(R.id.textView1);

        manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        manager.registerNetworkCallback(builder.build(),
                new ConnectivityManager.NetworkCallback(){
                  // 네트워크 연결되어서 사용 가능
                    @Override
                    public void onAvailable(@NonNull Network network) {
                        super.onAvailable(network);
                        Toast.makeText(MyView01.this, "연결됨", Toast.LENGTH_SHORT).show();
                        NetworkInfo activityNetwork = manager.getActiveNetworkInfo();
                        if(activityNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                            Toast.makeText(MyView01.this, activityNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                        }else if(activityNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                            Toast.makeText(MyView01.this, activityNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                        }
                        result += activityNetwork.toString()+"\n";
                        textView1.setText(result);
                    }

                    // 연결도중 끊어 졌을때
                    @Override
                    public void onLost(@NonNull Network network) {
                        super.onLost(network);
                        Toast.makeText(MyView01.this, "연결이 끊어짐", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
