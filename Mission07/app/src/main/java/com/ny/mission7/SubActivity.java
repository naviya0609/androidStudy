package com.ny.mission7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {
    Button button2, button3, button4, button5 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        Intent rintent = getIntent();
        String id = rintent.getStringExtra("id");
        String pw = rintent.getStringExtra("pw");

        Toast.makeText(SubActivity.this, "ID : " + id + " PW : "+ pw, Toast.LENGTH_SHORT).show();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SubActivity.this, Third_1.class);
                intent1.putExtra("msg" , button2.getText().toString());
                startActivityForResult(intent1,200);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SubActivity.this, Third_2.class);
                intent2.putExtra("msg" , button2.getText().toString());
                startActivityForResult(intent2,300);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(SubActivity.this, Third_3.class);
                intent3.putExtra("msg" , button3.getText().toString());
                startActivityForResult(intent3,400);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.putExtra("menu","SubActivity");
                setResult(1, mIntent);
                finish();
            }
        });

   /*     button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.putExtra("menu",button2.getText());
                setResult(1, mIntent);
                finish();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.putExtra("menu",button3.getText());
                setResult(1, mIntent);
                finish();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.putExtra("menu",button4.getText());
                setResult(1, mIntent);
                finish();
            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== 200){
            if(resultCode == RESULT_OK){
                String str = data.getStringExtra("str");
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            }else if (resultCode == RESULT_CANCELED) {
                finish();
            }
        }else if (requestCode== 300){
            if(resultCode == RESULT_OK){
                String str = data.getStringExtra("str");
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            }else if (resultCode == RESULT_CANCELED) {
                finish();
            }
        }else if (requestCode== 400){
            if(resultCode == RESULT_OK){
                String str = data.getStringExtra("str");
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            }else if (resultCode == RESULT_CANCELED) {
                finish();
            }
        }
    }
}
