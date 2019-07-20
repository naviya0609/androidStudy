package com.ny.ex54_multimedia;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    NotificationManager manager; //알림설정 관리자

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //실행
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //음악재생
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent); //서비스 클래스의 startCommand 실행

                //알림설정 Notification.Builder 빌더이용
                Notification.Builder  builder = new Notification.Builder(MainActivity.this);

                builder.setSmallIcon(R.mipmap.ic_launcher_round);
                builder.setContentTitle("음악서비스");
                builder.setContentText("백지영 - 총맞은 것처럼...");

                Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                Intent[] intents = new Intent[1];
                intents[0] = intent1;

                //다른 어플리케이션에게 인텐트를 위임하기 위해서는
                //PendingIntent가 필요하다
                PendingIntent pendingIntent =
                        PendingIntent.getActivities(MainActivity.this, 100, intents, 0);
                builder.setContentIntent(pendingIntent);

                //노티피게이션 생성
                Notification notification = builder.build();

                //manager에게 넘김 >> 알림아이디
                manager.notify(1,notification);
            }
        });

        //해제
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
                manager.cancel(1);// 알림아이디
            }
        });
    }
}
