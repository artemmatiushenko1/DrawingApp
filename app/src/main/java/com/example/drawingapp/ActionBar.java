package com.example.drawingapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

public class ActionBar {
    public Activity activity;
    private final View undoBtn;
    private final View redoBtn;
    private final View clearCanvasBtn;

    public ActionBar(Activity activity) {
        this.activity = activity;
        undoBtn = activity.findViewById(R.id.undo_action);
        redoBtn = activity.findViewById(R.id.redo_action);
        clearCanvasBtn = activity.findViewById(R.id.erase_all);
    }

    public void onUndoActionClick(DrawingView drawingView) {
        undoBtn.setOnClickListener(event -> {
            drawingView.editor.undoAction(drawingView);
        });
    }

    public void onRedoActionClick(DrawingView drawingView) {
        redoBtn.setOnClickListener(event -> {
            drawingView.editor.redoAction(drawingView);
        });
    }

    public void onClearCanvasClick(DrawingView drawingView) {
        clearCanvasBtn.setOnClickListener(event -> {
            drawingView.editor.clearCanvas(drawingView);
            Toast toast = Toast.makeText(activity.getApplicationContext(), "Canvas was cleared", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 200);
            toast.show();
        });
    }
}
