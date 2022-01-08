package com.example.drawingapp;

import android.graphics.Canvas;
import android.graphics.Paint;

public class LineOOShape extends Shape {
    private final Shape Line = new LineShape();
    private final Shape Ellipse = new EllipseShape();

    @Override
    public void Show(Canvas canvas, Paint paint, boolean isTemp) {
        Line.colorStroke = colorStroke;
        Ellipse.colorFill = colorFill;
        Ellipse.colorStroke = colorStroke;

        float d = strokeWidth + 5;
        Line.Set(xs1, ys1, xs2, ys2);
        Line.Show(canvas, paint, isTemp);
        Ellipse.Set(xs1 - d, ys1 - d, xs1 + d, ys1 + d);
        Ellipse.Show(canvas, paint, isTemp);
        Ellipse.Set(xs2 - d, ys2 - d, xs2 + d, ys2 + d);
        Ellipse.Show(canvas, paint, isTemp);
    }
}
