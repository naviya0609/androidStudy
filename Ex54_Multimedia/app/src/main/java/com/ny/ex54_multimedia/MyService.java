package com.ny.ex54_multimedia;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.util.ServiceConfigurationError;

// 배경에서 계속 실행하고자 할 때
// 서비스를 상속하면 반드시 manifests에 등록하기
public class MyService extends Service {

    //음악재생
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this,R.raw.back);
        player.stop();
        return START_STICKY;
    }

    //종료시
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();     // 음악재생 멈춤
        player.release();  // 자원해제
    }
}
