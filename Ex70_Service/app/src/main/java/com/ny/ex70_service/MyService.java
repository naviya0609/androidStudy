package com.ny.ex70_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    // 없으면 생성하고 시작
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("my","onCreate()");
    }
    // 있으면 바로 시작
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("my","onStartCommand()");
        process(intent);
        return super.onStartCommand(intent, flags, startId);
    }
    private void process(Intent intent){
        if(intent != null){
            String msg = intent.getStringExtra("msg");
            sendToActivity(msg);
        }
    }
    //MainActivity 안에 있는 Broadcast Receiver 에게 전달.
    private void sendToActivity(String msg){
        Intent returnIntent = new Intent();
        // 브로드캐스트 리시버 표시
        returnIntent.setAction("com.ict.service02");
        returnIntent.putExtra("msg",msg);
        sendBroadcast(returnIntent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("my","onDestroy()");
    }
}
