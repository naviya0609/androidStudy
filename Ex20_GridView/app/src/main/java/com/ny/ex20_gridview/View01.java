package com.ny.ex20_gridview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class View01 extends AppCompatActivity {
    GridView gridView;

//내부클래스로 만든 어댑터;
    MyGridAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view01);
        gridView = findViewById(R.id.gridView1);
        adapter = new MyGridAdapter(this);
        gridView.setAdapter(adapter);

    }

    public  class MyGridAdapter extends BaseAdapter {
        Context context;
        Integer[] imgId = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9};
        String[] imgName = {"그림1","그림2","그림3","그림4","그림5","그림6","그림7","그림8","그림9"};

        public MyGridAdapter() { }

        public MyGridAdapter( Context context) {

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            //직접 이미지뷰 만들기
            ImageView imageView = new ImageView(context);

            //이미지 크기 설정
            imageView.setLayoutParams(new GridView.LayoutParams(200,250));

            //중앙정렬
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            //내부여백
            imageView.setPadding(3,3,3,3);

            //배열을 이용한 이미지 뷰에 그림배치
            imageView.setImageResource(imgId[position]);

            //이벤트처리
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, imgName[position], Toast.LENGTH_SHORT).show();
                }
            });


            return imageView;
        }

        @Override
        public int getCount() {    return imgId.length;  }

        @Override
        public Object getItem(int position) {    return imgId[position]; }

        @Override
        public long getItemId(int position) {    return position;   }
    }


}
