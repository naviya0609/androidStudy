package com.ny.ex59_sensor;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class TTS02 extends AppCompatActivity {
    EditText editText1;
    Button button5,button6;
    TextToSpeech  tts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tts02);

        editText1 = findViewById(R.id.editText1);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);// 가능한지 검사
                startActivityForResult(intent,200);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                 tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        tts.setLanguage(Locale.KOREA);
                        tts.speak(editText1.getText().toString(), TextToSpeech.QUEUE_FLUSH,null,null);

                    }
                });
            }
        }
    }
}
