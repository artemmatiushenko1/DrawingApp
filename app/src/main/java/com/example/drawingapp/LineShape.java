package com.example.drawingapp;

import android.graphics.Canvas;
import android.graphics.Paint;

public class LineShape extends Shape {
    @Override
    public void Show(Canvas canvas, Paint paint, boolean isTemp) {
        if (!isTemp) {
            this.setPaintColor(paint, colorStroke);
        }
        canvas.drawLine(xs1, ys1, xs2, ys2, paint);
    }
}
