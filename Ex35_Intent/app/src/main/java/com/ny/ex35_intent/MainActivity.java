package com.ny.ex35_intent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText1, editText2;
    RadioGroup radioGroup1;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        radioGroup1 = findViewById(R.id.rg1);
        button1 = findViewById(R.id.button1);
        try {
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int su1 = Integer.parseInt(editText1.getText().toString());
                    int su2 = Integer.parseInt(editText2.getText().toString());
                    String op = null;
                    switch (radioGroup1.getCheckedRadioButtonId()) {
                        case R.id.radioButton1:
                            op = "+";
                            break;
                        case R.id.radioButton2:
                            op = "-";
                            break;
                        case R.id.radioButton3:
                            op = "*";
                            break;
                        case R.id.radioButton4:
                            op = "/";
                            break;
                        default:  Toast.makeText(MainActivity.this, "제대로 입력해주세요", Toast.LENGTH_SHORT).show(); return;
                    }

                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    intent.putExtra("su1", su1);
                    intent.putExtra("su2", su2);
                    intent.putExtra("op", op);

                    startActivityForResult(intent, 1000);
                }
            });
        }catch (Exception e){
            Toast.makeText(this, "제대로 입력해주세요", Toast.LENGTH_SHORT).show();
        }
    }
    // 처리결과를 받은 메소리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == RESULT_OK){
                int result = data.getIntExtra("result",0);
                Toast.makeText(this, "결과 : "+result, Toast.LENGTH_SHORT).show();
            }else if(resultCode == RESULT_CANCELED){
                String result = data.getStringExtra("result");
                Toast.makeText(this, "결과 : "+result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
