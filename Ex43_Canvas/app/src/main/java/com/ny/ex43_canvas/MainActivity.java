package com.ny.ex43_canvas;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  setContentView(R.layout.activity_main);
        setContentView(new CanvasView(this));
    }
    private class CanvasView extends View {

        public CanvasView(Context context) {
            super(context);
            setBackgroundColor(Color.rgb(34,153,153));

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(10f);
            paint.setStrokeCap(Paint.Cap.ROUND);

            canvas.drawLine(100,100,100,600, paint);
            canvas.drawLine(100,100,600,100, paint);
            canvas.drawLine(600,100,600,600, paint);
            canvas.drawLine(100,600,600,600, paint);


            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.YELLOW);
            canvas.drawRect(500,1200,800,1500, paint);

            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(700,700,300,paint);

            paint.setColor(Color.BLACK);
            paint.setTextSize(100);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawText("Android",400,100,paint);

            BitmapDrawable bit = (BitmapDrawable) getResources().getDrawable(R.drawable.dream01, null);
            Bitmap bitmap = bit.getBitmap();
            Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.dream02);

            canvas.drawBitmap(bitmap,100,1800,paint);
            canvas.drawBitmap(bitmap1,null,new RectF(700,900,1000,1500),paint);

        }
    }


}
