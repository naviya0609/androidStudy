package com.ny.ex46_canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(new MyCanvas(this));

    }

    private class MyCanvas extends View{
        Bitmap bitmap;
        int x = 0, y=0 , nx = 20, ny = 20;

        public MyCanvas(Context context) {
            super(context);

            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dream01);
            handler.sendEmptyMessageDelayed(0,200);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (x < 0 || (canvas.getWidth() -bitmap.getWidth()< x)) {
                nx = -nx;
            }
            if (y < 0 || (canvas.getHeight() -bitmap.getHeight()< y)) {
                ny = -ny;
            }
            x= x+nx;
            y= y+ny;
            canvas.drawBitmap(bitmap, x, y, null);

        }

        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                invalidate();
                handler.sendEmptyMessageDelayed(0,200);
            }
        };
    }
}
