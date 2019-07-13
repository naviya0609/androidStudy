package com.ny.ex35_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);

        Intent rintent = getIntent();

        int su1 = rintent.getIntExtra("su1", 0);
        int su2 = rintent.getIntExtra("su2", 0);
        String op  = rintent.getStringExtra("op");
        int res = 0;
        String msg =  null;


        switch (op){
            case "+" : res = su1 + su2; break;
            case "-" : res = su1 - su2; break;
            case "*" : res = su1 * su2; break;
            case "/" :
                if(su2 !=0){
                    res = su1 / su2; break;
                } else {
                    msg = "0으로는 나눌 수 가 없습니다.";
                }
        }
        Intent m_intent = new Intent();
        if (su2 ==0 && op.equals("/")) {
            m_intent.putExtra("result", msg);
            setResult(RESULT_CANCELED,m_intent);
        }else {
            m_intent.putExtra("result", res);
            setResult(RESULT_OK,m_intent);
        }

        finish();
    }
}
