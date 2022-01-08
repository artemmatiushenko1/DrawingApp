package com.example.drawingapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CubeShape extends Shape {
    private final Shape Rect = new RectShape();
    private final Shape Line = new LineShape();

    @Override
    public void Show(Canvas canvas, Paint paint, boolean isTemp) {
        Rect.colorFill = Color.TRANSPARENT;
        Rect.colorStroke = colorStroke;
        Line.colorStroke = colorStroke;

        adjustSquareCoords();
        float shift = (ys2 - ys1) / 3;

        Rect.Set(xs1, ys1, xs2, ys2);
        Rect.Show(canvas, paint, isTemp);
        Rect.Set(xs1 + shift, ys1 - shift, xs2 + shift, ys2 - shift);
        Rect.Show(canvas, paint, isTemp);
        Line.Set(xs1, ys1, xs1 + shift, ys1 - shift);
        Line.Show(canvas, paint, isTemp);
        Line.Set(xs2, ys2, xs2 + shift, ys2 - shift);
        Line.Show(canvas, paint, isTemp);
        Line.Set(xs1, ys2, xs1 + shift, ys2 - shift);
        Line.Show(canvas, paint, isTemp);
        Line.Set(xs2, ys1, xs2 + shift, ys1 - shift);
        Line.Show(canvas, paint, isTemp);
    }

    private void adjustSquareCoords() {
        float delta = Math.abs(xs1 - xs2);
        if ((xs1 > xs2 || xs1 < xs2) && ys1 < ys2) {
            ys2 = ys1 + delta;
        } else {
            ys2 = ys1 - delta;
        }
    }
}
