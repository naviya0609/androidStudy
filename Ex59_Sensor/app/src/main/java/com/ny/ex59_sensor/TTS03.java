package com.ny.ex59_sensor;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TTS03 extends AppCompatActivity {
    TextView textView2;
    ListView list;
    String[] arr = {"안드로보이","아이폰보이","아이언 맨", "핫 맨"};
    Button button7 ;
    TextToSpeech tts;
    String result;  // 정답, 오답 저장
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tts03);

        textView2 = findViewById(R.id.textView2);
        list = findViewById(R.id.list);
        button7 = findViewById(R.id.button7);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,arr);
        list.setAdapter(adapter);

        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent,300);

        // 답을 선택했을 때 처리
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent2 = new Intent();
                intent2.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                startActivityForResult(intent2,400);
                if(i == 0){
                    result = arr[i].toString()+" 정답입니다.";
                }else {
                    result = arr[i].toString()+" 오답입니다.";
                }
            }
        });



        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 문제 읽기
        if(requestCode == 300){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        tts.setLanguage(Locale.KOREA);
                        tts.speak(textView2.getText().toString(),
                                TextToSpeech.QUEUE_FLUSH,null, null);
                    }
                });
            }
        }
        // 정답, 오답 처리
        if(requestCode == 400){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        tts.setLanguage(Locale.KOREA);
                        tts.speak(result,
                                TextToSpeech.QUEUE_FLUSH,null, null);
                    }
                });
            }
        }
    }
}
