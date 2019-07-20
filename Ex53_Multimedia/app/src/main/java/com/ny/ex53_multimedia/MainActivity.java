package com.ny.ex53_multimedia;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //내부저장소
    // static  final String AUDIO_URL = Environment.getExternalStorageDirectory().getAbsolutePath()+"/back.mp3"; //잘씀
    // static  final String AUDIO_URL1 = Environment.getDataDirectory()+"data/com.ny.ex53_multimedia/back.mp3";  //data >> data >> package

    //외부저장소 퍼미션필요
    static final String AUDIO_URL2 = "/sdcard/Music/back.mp3";

    Button button1,button2,button3;

    //재생 오디오 처리
    private MediaPlayer mediaPlayer;
    private int playBackPosition = 0; //재실행시 위치 기억 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //복제문제로 안씀 Environment.getExternalStorageDirectory().getAbsoluteFile();

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   if(mediaPlayer != null ){
                       mediaPlayer.release();
                   }

                mediaPlayer  = new MediaPlayer();
                mediaPlayer.setDataSource(AUDIO_URL2);
                mediaPlayer.prepare();//스타트 하기전에 먼저 준비 메소드 실행
                mediaPlayer.start();
               }catch (Exception e ){

               }
            }
        });

        //중지
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer !=null){
                    playBackPosition = mediaPlayer.getCurrentPosition();// 현재 재생위치 변수에저장
                    mediaPlayer.pause();
                    Toast.makeText(MainActivity.this, "일시정지/"+playBackPosition, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //재시작
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //플레이어가 있으면서 플레이중이 아닐때
                if(mediaPlayer!=null && !mediaPlayer.isPlaying() ){
                    mediaPlayer.start();;
                    //일시정지된 시점부터 재시작
                    mediaPlayer.seekTo(playBackPosition);
                    Toast.makeText(MainActivity.this, "재실행", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.release(); //종료시 미디어플레이어도 종료
        }
    }
}
