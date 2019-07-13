package com.ny.ex34_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {
    Button button2;
    int su1, su2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);
        button2 = findViewById(R.id.button2);

        Intent rintent = getIntent();
        su1 = rintent.getIntExtra("su1",0);
        su2 = rintent.getIntExtra("su2",0);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인으로 되돌아가는 Intent 만들기
                Intent mintent = new Intent();
                if(su2!=0){
                    int res = su1/ su2;
                    mintent.putExtra("res",res);
                    setResult(RESULT_OK, mintent);
                }else if (su2 == 0){
                    mintent.putExtra("res" , "0으로는 나눌수가 없습니다.");
                    setResult(RESULT_CANCELED, mintent);
                }
                finish();
            }
        });
    }
}
