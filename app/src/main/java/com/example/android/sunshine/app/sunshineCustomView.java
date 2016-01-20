package com.example.android.sunshine.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

/**
 * Created by GAURAV on 17-01-2016.
 */
public class SunshineCustomView extends View{
    private Paint paint;

    public SunshineCustomView(Context context){
        super(context);
    }

    public SunshineCustomView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setTextSize(25);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
    }

    public SunshineCustomView(Context context, AttributeSet attributeSet, int DefaultStyle){
        super(context,attributeSet,DefaultStyle);
    }

    /*private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setTextSize(25);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
    }*/


    @Override
    protected void onDraw(Canvas canvas){
        int xPoint = 200;//getMeasuredWidth() / 2;
        int yPoint = 200;//getMeasuredHeight() / 2;

        float radius = (float) (Math.max(xPoint, yPoint) * 0.6);
        canvas.drawCircle(xPoint, yPoint, radius, paint);
    }
}
