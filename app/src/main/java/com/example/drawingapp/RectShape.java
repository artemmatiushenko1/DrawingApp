package com.example.drawingapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class RectShape extends Shape {
    @Override
    public void Show(Canvas canvas, Paint paint, boolean isTemp) {
        RectF rect = createRectangle();
        if (!isTemp) {
            this.setPaintStyle(paint, "FILL");
            this.setPaintColor(paint, this.colorFill);
            canvas.drawRect(rect, paint);

            this.setPaintStyle(paint, "STROKE");
            this.setPaintColor(paint, this.colorStroke);
            canvas.drawRect(rect, paint);
        }
        canvas.drawRect(rect, paint);
    }

    private RectF createRectangle() {
        float right = Math.max(xs1, xs2);
        float left = Math.min(xs1, xs2);
        float bottom = Math.max(ys1, ys2);
        float top = Math.min(ys1, ys2);
        return new RectF(left, top, right, bottom);
    }
}
