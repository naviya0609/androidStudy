package com.ny.ex41_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.zip.Inflater;

public class ListFragment extends Fragment {
    public ImageSelectionCallback callback;

    public  static interface ImageSelectionCallback{
        public void onImageSelected(int position);
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ImageSelectionCallback){
            callback = (ImageSelectionCallback) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_list,container,false);

        Button button1 = rootview.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback !=null){
                    callback.onImageSelected(0);
                }
            }
        });
        Button button2 = rootview.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback !=null){
                    callback.onImageSelected(1);
                }
            }
        });
        Button button3 = rootview.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback !=null){
                    callback.onImageSelected(2);
                }
            }
        });
        return rootview;
    }
}
