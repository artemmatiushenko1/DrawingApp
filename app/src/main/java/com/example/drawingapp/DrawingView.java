package com.example.drawingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;


public class DrawingView extends View {
    public Editor editor = Editor.getInstance();
    public Table table;
    public String currentShapeType = "Rect";

    public DrawingView(Context context) {
        super(context);
        init(null);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet set) {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        editor.onPaint(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                switch (currentShapeType) {
                    case "Ellipse":
                        editor.Start(new EllipseShape());
                        break;
                    case "Rect":
                        editor.Start(new RectShape());
                        break;
                    case "Point":
                        editor.Start(new PointShape());
                        break;
                    case "Line":
                        editor.Start(new LineShape());
                        break;
                    case "LineOO":
                        editor.Start(new LineOOShape());
                        break;
                    case "Cube":
                        editor.Start(new CubeShape());
                        break;
                }
                editor.onTouchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                editor.onTouchMove(event);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                table.add(new TableItem(currentShapeType, editor.xStart, editor.yStart, editor.xEnd, editor.yEnd));
                editor.onTouchUp();
                invalidate();
                break;
        }
        return true;
    }
}
