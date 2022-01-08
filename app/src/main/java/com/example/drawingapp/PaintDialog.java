package com.example.drawingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.divyanshu.colorseekbar.ColorSeekBar;
import com.google.android.material.slider.Slider;

public class PaintDialog {
    public Activity activity;
    private View paintDialogView;
    private final AlertDialog paintDialog;
    private final ColorSeekBar fillColorSeekBar;
    private final ColorSeekBar strokeColorSeekBar;
    private final Slider strokeWidthSeekBar;
    private final DrawingView drawingView;

    public PaintDialog(Activity context, DrawingView drawingView) {
        this.activity = context;
        this.drawingView = drawingView;
        paintDialog = createPaintDialog();
        fillColorSeekBar = paintDialogView.findViewById(R.id.color_fill_picker);
        strokeColorSeekBar = paintDialogView.findViewById(R.id.color_stroke_picker);
        strokeWidthSeekBar = paintDialogView.findViewById(R.id.stroke_width_picker);
    }

    public AlertDialog createPaintDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        paintDialogView = layoutInflater.inflate(R.layout.fragment_brush_options_panel, null);
        alert.setView(paintDialogView);

        return alert.create();
    }

    public void show() {
        paintDialog.show();
    }

    public void onColorFillChange() {
        fillColorSeekBar.setOnColorChangeListener(color -> {
            drawingView.editor.colorFill = color;
        });
    }

    public void onColorStrokeChange() {
        strokeColorSeekBar.setOnColorChangeListener(color -> {
            drawingView.editor.colorStroke = color;
        });
    }

    public void onStrokeWidthChange() {
        strokeWidthSeekBar.addOnChangeListener((slider, value, fromUser) -> drawingView.editor.strokeWidth = Math.round(value));
    }
}
