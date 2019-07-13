package com.ny.ex16_spinner;

import android.media.ImageReader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
/*<!--dropdown == spinner 콤보박스-->*/
    //1. 반드시 어댑터를 사용해야 한다. (어댑터뷰)
    //2. 들어갈 내용을 먼저 배열로 만들어놓고 어댑터에 삽입하여 사용한다.
    Spinner spinner1, spinner2,spinner3;
    ImageView imageView1;
    String[] arr = {"과일종류" , "mango", "durian", "apple","dragon fruit", "lemon"};
    String[] arr3 = {"그림을 선택하세요" , "boy", "coffee", "dog","donald"};
    //int[] images= {R.drawable.boy , R.drawable.coffe , R.drawable.dog, R.drawable.donald};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view01);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        imageView1= findViewById(R.id.imageView1);

        //배열은 크기지정시 변경안됨 리스트는 중간에 변경, 추가 가능
        //어댑터 생성
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arr); // 간격 좁음
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,        //화면에 보여줄 레이아웃
                                                                                android.R.layout.simple_spinner_dropdown_item, arr);

        spinner1.setAdapter(adapter); //adapter 스피너에 장착

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String msg = (String) spinner1.getAdapter().getItem(position);
                Toast.makeText(MainActivity.this,  position+" : "+ msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {  }
        });

        /****************************************************************************************************************************************************/

/*        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,        //화면에 보여줄 레이아웃
                android.R.layout.simple_spinner_dropdown_item, arr3);

        spinner3.setAdapter(adapter3); //adapter 스피너에 장착*/

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              switch (position){
                  case 1: imageView1.setImageResource(R.drawable.boy);           break;
                  case 2: imageView1.setImageResource(R.drawable.coffe);         break;
                  case 3: imageView1.setImageResource(R.drawable.dog);           break;
                  case 4: imageView1.setImageResource(R.drawable.donald);        break;
                  default:
                      Toast.makeText(MainActivity.this, "그림을 선택하세요", Toast.LENGTH_SHORT).show(); break;
              }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {  }
        });
    }
}
