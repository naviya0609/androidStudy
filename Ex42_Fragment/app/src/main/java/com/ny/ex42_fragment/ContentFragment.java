package com.ny.ex42_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {
    TextView name, addr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_content,container,false);
        name = rootview.findViewById(R.id.name);
        addr = rootview.findViewById(R.id.addr);

        return rootview;
    }

    public void change(String n, String a){
        name.setText(n);
        addr.setText(a);
    }
}
