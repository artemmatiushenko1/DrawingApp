package com.example.drawingapp;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.divyanshu.colorseekbar.ColorSeekBar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.slider.Slider;
import com.google.android.material.tabs.TabLayout;

public class ToolBar {
    public Activity activity;
    private final TabLayout toolsTab;
    private final BottomSheetBehavior bottomSheet;
    private final View tableBtn;
    private final ImageButton paintDialogBtn;
    private final PaintDialog paintDialog;

    public ToolBar(Activity context, PaintDialog paintDialog) {
        this.activity = context;
        this.paintDialog = paintDialog;
        bottomSheet = BottomSheetBehavior.from(activity.findViewById(R.id.sheet));
        toolsTab = activity.findViewById(R.id.tab_menu);
        tableBtn = activity.findViewById(R.id.table_open_btn);
        paintDialogBtn = activity.findViewById(R.id.color_wheel_btn);
    }

    public void onToolSelected(DrawingView drawingView) {
        toolsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                drawingView.currentShapeType = tab.getContentDescription().toString();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void onTableBtnClick() {
        tableBtn.setOnClickListener(v -> bottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED));
    }

    public void onPaintDialogBtnClick() {
        paintDialogBtn.setOnClickListener(v -> paintDialog.show());
    }
}
