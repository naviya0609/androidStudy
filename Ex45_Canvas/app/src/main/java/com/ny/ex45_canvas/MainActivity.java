package com.ny.ex45_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static  final int LINE = 1, CIRCLE= 2, RECTANGLE = 3;
    static int curShape = LINE ;
    static int curColor = Color.BLACK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(new MyCanvas(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"선그리기");
        menu.add(0,2,0,"원그리기");
        menu.add(0,3,0,"사각형그리기");
        SubMenu subMenu = menu.addSubMenu("색상변경 >>");
        subMenu.add(0,4,0,"빨강");
        subMenu.add(0,5,0,"파랑");
        subMenu.add(0,6,0,"초록");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1 : curShape = LINE; return  true;
            case 2 : curShape = CIRCLE; return  true;
            case 3 : curShape = RECTANGLE; return  true;
            case 4 : curColor = Color.RED; return  true;
            case 5 : curColor = Color.BLUE; return  true;
            case 6 : curColor = Color.GREEN; return  true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class MyCanvas extends View {
        Paint paint = new Paint();
        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyCanvas(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(MainActivity.curColor);

            switch (MainActivity.curShape) {
                case MainActivity.LINE :
                    canvas.drawLine(startX,startY,stopX,stopY,paint); break;
                case MainActivity.CIRCLE :
                    int radius = (int)Math.sqrt(Math.pow(stopX-startX,2)+Math.pow(stopY-startY,2));
                    canvas.drawCircle(startX,startY,radius,paint); break;
                case MainActivity.RECTANGLE :
                    canvas.drawRect(startX,startY,stopX,stopY,paint); break;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN :
                    startX = (int) event.getX();
                    startY = (int) event.getY();

                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    break;

                case MotionEvent.ACTION_MOVE :
                case MotionEvent.ACTION_UP :
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    break;

            }
            invalidate();
            return true;
        }
    }
}
