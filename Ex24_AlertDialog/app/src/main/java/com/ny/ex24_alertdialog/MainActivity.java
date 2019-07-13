package com.ny.ex24_alertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button1,button2,button3,button4,button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 =findViewById(R.id.button1);
        button2 =findViewById(R.id.button2);
        button3 =findViewById(R.id.button3);
        button4 =findViewById(R.id.button4);
        button5 =findViewById(R.id.button5);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new AlertDialog.Builder(MainActivity.this)
                         .setTitle("대화상자1")
                         .setMessage("대화상자 내용")
                         .setIcon(R.mipmap.ic_launcher)
                         .show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("대화상자2")
                        .setMessage("대화상자 내용")
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "확인 클릭", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "취소 클릭", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] foods = {"치킨","삼겹살","파전","제육볶음"};

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("술안주")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setItems(foods, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String msg= foods[which];
                                Toast.makeText(MainActivity.this, msg + " 선택", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("닫기", null)
                        .show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] foods = {"치킨","삼겹살","파전","제육볶음"};

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("술안주")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setSingleChoiceItems(foods, 3, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                String msg= foods[i];
                                Toast.makeText(MainActivity.this, msg + " 선택", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("닫기", null)
                        .show();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] foods = {"치킨","삼겹살","파전","제육볶음"};
                final boolean[] isfoods = {false,false,false,false};
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("술안주")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setMultiChoiceItems(foods, isfoods, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i, boolean b) {
                                if(b) {
                                    Toast.makeText(MainActivity.this, foods[i]+" 선택", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(MainActivity.this, foods[i]+" 해제", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("닫기", null)
                        .show();
            }
        });
    }
}
