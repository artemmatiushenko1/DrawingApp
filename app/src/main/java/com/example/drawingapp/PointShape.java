package com.example.drawingapp;

import android.graphics.Canvas;
import android.graphics.Paint;

public class PointShape extends Shape {
    @Override
    public void Show(Canvas canvas, Paint paint, boolean isTemp) {
        if (!isTemp) {
            this.setPaintColor(paint, colorStroke);
        }
        canvas.drawPoint(xs1, ys1, paint);
    }
}
