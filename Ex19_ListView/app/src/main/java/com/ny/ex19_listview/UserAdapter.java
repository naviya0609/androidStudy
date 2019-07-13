package com.ny.ex19_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//사용자 정의 어댑터
//어댑터를 만들기 위해서는 반드시 BaseAdapter 상속
//getView, getCount,getItem,getItemId 4가지는 기본으로 오버라이딩 구현 필수 alt+insert
public class UserAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<VO> list;
    LayoutInflater inflater;

    public UserAdapter() {
    }

    //생성자에서 만든것을 다른곳에서 쓸려면 전역변수로 생성
    public UserAdapter(Context context, int layout, ArrayList<VO> list) {
        this.context = context;
        this.layout = layout;
        this.list= list;

        //user_item.xml 정보를 메모리에 적재 시키고, 자바에서 해당 메모리에 적재된 정보를 사용할 수 있도록 인플레이트한다. (LayoutInflater : layout setter, getter 하는 과정)
        inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    //이벤트처리, 리스트 삽입 등등 전부 getView가처리
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null){
            //inflater 하는 방법
            view = inflater.inflate(layout,viewGroup,false);
        }
        //사용자 어댑터
        TextView textView1 = view.findViewById(R.id.textView1);
        ImageView imageView1 = view.findViewById(R.id.imageView1);

        //해당 위치에서 해당 데이터 설정하기
        final VO vo =list.get(position);
        textView1.setText(vo.getImageName());
        imageView1.setImageResource(vo.getResId());

        //이벤트 처리
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, vo.getImageName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    //리스트 개수 리턴
    @Override
    public int getCount() {  return list.size(); }

    //원하는 위치에 객체 리턴( position = 위치)
    @Override
    public Object getItem(int position) { return list.get(position); }

    @Override
    public long getItemId(int position) {    return position;  }
}
