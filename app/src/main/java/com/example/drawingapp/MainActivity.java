package com.example.drawingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        DrawingView mDrawingView = findViewById(R.id.drawing_view);

        ActionBar actionBar = new ActionBar(MainActivity.this);
        actionBar.onRedoActionClick(mDrawingView);
        actionBar.onUndoActionClick(mDrawingView);
        actionBar.onClearCanvasClick(mDrawingView);

        PaintDialog paintDialog = new PaintDialog(MainActivity.this, mDrawingView);
        paintDialog.onColorFillChange();
        paintDialog.onColorStrokeChange();
        paintDialog.onStrokeWidthChange();

        ToolBar toolBar = new ToolBar(MainActivity.this, paintDialog);
        toolBar.onToolSelected(mDrawingView);
        toolBar.onTableBtnClick();
        toolBar.onPaintDialogBtnClick();

        mDrawingView.table = new Table(MainActivity.this, mDrawingView);
    }
}