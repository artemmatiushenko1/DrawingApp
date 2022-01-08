package com.example.drawingapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import java.util.ArrayList;

public class Editor {
    private static Editor editorInstance;
    private final static ArrayList<Shape> shapes = new ArrayList<>();
    private final static ArrayList<Shape> deletedShapes = new ArrayList<>();
    private Shape currentShape = null;
    public float xStart, yStart, xEnd, yEnd;
    public int colorFill = Color.WHITE;
    public int colorStroke = Color.BLACK;
    public int strokeWidth = 7;

    public static Editor getInstance() {
        if (editorInstance == null) {
            editorInstance = new Editor();
        }
        return editorInstance;
    }

    public void Start(Shape shape) {
        currentShape = shape;
        currentShape.setColorFill(colorFill);
        currentShape.setColorStroke(colorStroke);
        currentShape.setStrokeWidth(strokeWidth);
    }

    public void onTouchDown(MotionEvent event) {
        xStart = event.getX();
        yStart = event.getY();
        currentShape.Set(xStart, yStart, xStart, yStart);
    }

    public void onTouchMove(MotionEvent event) {
        xEnd = event.getX();
        yEnd = event.getY();
        currentShape.Set(xStart, yStart, xEnd, yEnd);
    }

    public void onTouchUp() {
        shapes.add(currentShape);
        xStart = 0;
        yStart = 0;
        xEnd = 0;
        yEnd = 0;
        currentShape = null;
    }

    public void onPaint(Canvas canvas) {
        for (Shape shape : shapes) {
            shape.Show(canvas, shape.paint, false);
        }

        if (currentShape != null) {
            currentShape.Show(canvas, currentShape.tracePaint, true);
        }
    }

    public void undoAction(DrawingView drawingView) {
        if (shapes.size() != 0) {
            deletedShapes.add(shapes.remove(shapes.size() - 1));
            drawingView.table.removeItem();
            drawingView.invalidate();
        }
    }

    public void redoAction(DrawingView drawingView) {
        if (deletedShapes.size() != 0) {
            shapes.add(deletedShapes.remove(deletedShapes.size() - 1));
            drawingView.table.returnItem();
            drawingView.invalidate();
        }
    }

    public void deleteShapeByIndex(int shapeIndex, DrawingView drawingView){
        shapes.remove(shapeIndex);
        drawingView.invalidate();
    }

    public void clearCanvas(DrawingView drawingView) {
        if (shapes.size() != 0) {
            shapes.clear();
            deletedShapes.clear();
            drawingView.table.clear();
            drawingView.invalidate();
        }
    }
}
