package com.example.drawingapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

public abstract class Shape {
    protected float xs1, ys1, xs2, ys2;
    protected Paint paint = new Paint(Paint.DITHER_FLAG);
    protected Paint tracePaint = new Paint(Paint.DITHER_FLAG);
    protected int colorFill, colorStroke, strokeWidth;

    public Shape() {
        //Default paint setup
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);

        //Default trace paint setup
        tracePaint.setAntiAlias(true);
        tracePaint.setDither(true);
        tracePaint.setColor(Color.BLACK);
        tracePaint.setStyle(Paint.Style.STROKE);
        tracePaint.setStrokeJoin(Paint.Join.ROUND);
        tracePaint.setStrokeCap(Paint.Cap.ROUND);
        tracePaint.setStrokeWidth(5);
        tracePaint.setPathEffect(new DashPathEffect(new float[]{10f, 10f}, 0f));
    }

    public void setPaintStyle(Paint currentPaint, String style) {
        if (style.equals("FILL")) {
            currentPaint.setStyle(Paint.Style.FILL);
        } else {
            currentPaint.setStyle(Paint.Style.STROKE);
        }
    }

    public void setPaintColor(Paint currentPaint, int color) {
        currentPaint.setColor(color);
    }

    public void setColorFill(int colorFill) {
        this.colorFill = colorFill;
    }

    public void setColorStroke(int colorStroke) {
        this.colorStroke = colorStroke;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        paint.setStrokeWidth(strokeWidth);
    }

    public void Set(float x1, float y1, float x2, float y2) {
        xs1 = x1;
        ys1 = y1;
        xs2 = x2;
        ys2 = y2;
    }

    public abstract void Show(Canvas canvas, Paint paint, boolean isTemp);
}


