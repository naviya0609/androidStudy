package com.ny.ex69_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //서비스 없으면 만들고 시작.
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("my", "onCreate 호출");
    }

    //있으면 바로 시작한다.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("my","onStartCommand 호출");
        if(intent == null){
            // 비정상적으로 종료되면  자동으로 재시작
            return Service.START_STICKY;
        }else{
            process(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void process(Intent intent){
        String command = intent.getStringExtra("command");
        String msg= intent.getStringExtra("msg");

        for(int i=0; i<5; i++){
            try{
             Thread.sleep(1000);
            } catch (Exception e){

            }
            Log.d("my","  Waiting   "+i+ "  seconds.");
        }

        //다시 메인 activitity로 intent보냄
        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
        // 서비스는 화면이 없기 때문에 서비스에서 화면이 있는 액티비티를 띄우려면 새로운
        // 태스크를  만들어야 하고, 현재 사용중인 MainActivity를 재사용하기 위해서 필요하다.
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                |Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        showIntent.putExtra("msg", msg + " from service");
        startActivity(showIntent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("my", "onDestroy 호출");
    }

}
