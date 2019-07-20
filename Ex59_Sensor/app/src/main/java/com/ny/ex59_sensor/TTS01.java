package com.ny.ex59_sensor;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class TTS01 extends AppCompatActivity {
    TextView textView1;
    Button button4;
    TextToSpeech tts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tts01);

        textView1 = findViewById(R.id.textView1);
        button4 = findViewById(R.id.button4);

        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);// 가능한지 검사
        startActivityForResult(intent,100);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) { //tts 성공 상수
                tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        tts.setLanguage(Locale.KOREA);
                        tts.speak(textView1.getText().toString(),TextToSpeech.QUEUE_FLUSH, null,null);
                    }
                });
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.stop();
    }
}
