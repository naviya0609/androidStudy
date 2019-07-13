package com.ny.ex11_imageview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class View03 extends AppCompatActivity {
    CheckBox checkBox1;
    TextView tv1;
    RadioGroup rg1;
    RadioButton rb1,rb2,rb3,rb4;
    Button btn6;
    ImageView imageView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view3);

        checkBox1 = findViewById(R.id.checkBox1);
        tv1 = findViewById(R.id.tv1);
        rg1 = findViewById(R.id.rg1);
        btn6= findViewById(R.id.btn6);
        imageView3= findViewById(R.id.imageView3);


        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tv1.setVisibility(ImageView.VISIBLE);
                    rg1.setVisibility(ImageView.VISIBLE);
                    btn6.setVisibility(ImageView.VISIBLE);
                    imageView3.setVisibility(ImageView.VISIBLE);
                }else{
                    tv1.setVisibility(ImageView.INVISIBLE);
                    rg1.setVisibility(ImageView.INVISIBLE);
                    btn6.setVisibility(ImageView.INVISIBLE);
                    imageView3.setVisibility(ImageView.INVISIBLE);
                    //초기화 방법
                    imageView3.setImageResource(0);
                    rg1.clearCheck();
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rg1.getCheckedRadioButtonId()){
                    case R.id.rb1 : imageView3.setImageResource(R.drawable.boy); break;
                    case R.id.rb2 : imageView3.setImageResource(R.drawable.coffe); break;
                    case R.id.rb3 : imageView3.setImageResource(R.drawable.dog); break;
                    case R.id.rb4 : imageView3.setImageResource(R.drawable.donald); break;
                    default : Toast.makeText(View03.this, "선택 후 클릭해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
