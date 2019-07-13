package com.ny.ex44_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // setContentView(R.layout.activity_main);
        setContentView(new MyCanvas(this));
    }


    private class MyCanvas extends View{
        String str = "액션의 종류";
        int x,y;

        // 선그리기
        Path path = new Path();
        Paint paint = new Paint();

        public MyCanvas(Context context) {
            super(context);
            setBackgroundColor(Color.YELLOW);

            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);

            // 선연결
            paint.setStrokeJoin(Paint.Join.ROUND);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // Paint paint = new Paint();
            // paint.setColor(Color.RED);
            // paint.setTextSize(50);
            //  canvas.drawText(str,100,100,paint);
            //  canvas.drawRect(new Rect(x,y,x+50,y+50),paint);

            // 연결선 그리기
            canvas.drawPath(path,paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            x = (int) event.getX();
            y = (int) event.getY();

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN : path.moveTo(x,y); return true;
                case MotionEvent.ACTION_UP   : break;
                case MotionEvent.ACTION_MOVE : path.lineTo(x,y); break;
            }
            invalidate();
            return true;
        }
    }

}
