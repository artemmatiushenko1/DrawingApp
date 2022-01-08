package com.example.drawingapp;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.OvershootInRightAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class Table {
    public Activity activity;
    public DrawingView drawingView;
    public RecyclerView tableRCView;
    private static File file;
    static ArrayList<TableItem> tableItems = new ArrayList<>();
    static ArrayList<TableItem> removedTableItems = new ArrayList<>();
    static TableItemsAdapter tableItemsAdapter;

    public Table(Activity activity, DrawingView drawingView) {
        this.activity = activity;
        this.drawingView = drawingView;
        tableRCView = activity.findViewById(R.id.table_values_rcv);
        setTableProps(tableRCView, activity);
        SlideInLeftAnimator slideInLeftAnimator = new SlideInLeftAnimator();
        slideInLeftAnimator.setAddDuration(200);
        tableRCView.setItemAnimator(slideInLeftAnimator);
    }

    public void setTableProps(RecyclerView recyclerView, Context context) {
        tableItemsAdapter = new TableItemsAdapter(tableItems, new TableItemsAdapter.TableItemClickListener() {
            @Override
            public void onTableItemClick(TableItem tableItem, int itemPosition) {
                deleteItemByIndex(itemPosition);
            }
        });
        recyclerView.setAdapter(tableItemsAdapter);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager1);
    }

    public void add(TableItem tableItem) {
        tableItems.add(tableItem);
        saveToFile();
        int addedItemIndex = tableItems.size() - 1;
        tableRCView.scheduleLayoutAnimation();
        tableItemsAdapter.notifyItemInserted(addedItemIndex);
    }

    public void removeItem() {
        int removeItemIndex = tableItems.size() - 1;
        removedTableItems.add(tableItems.get(removeItemIndex));
        tableItems.remove(removeItemIndex);
        saveToFile();
        tableItemsAdapter.notifyItemRemoved(removeItemIndex);
    }

    public void returnItem() {
        tableItems.add(removedTableItems.remove(removedTableItems.size() - 1));
        int returnedItemIndex = tableItems.size() - 1;
        saveToFile();
        tableItemsAdapter.notifyItemRemoved(returnedItemIndex);
    }

    public void deleteItemByIndex(int index){
        tableItems.remove(index);
        saveToFile();
        tableItemsAdapter.notifyItemRemoved(index);
        tableItemsAdapter.notifyItemRangeChanged(index, tableItems.size());
        drawingView.editor.deleteShapeByIndex(index, drawingView);
    }

    public void clear() {
        tableItems.clear();
        removedTableItems.clear();
        saveToFile();
        tableItemsAdapter.notifyDataSetChanged();
    }

    public void createFile(Context context) {
        String path = context.getExternalCacheDir() + "";
        File externalAppDir = new File(path);
        if (!externalAppDir.exists()) {
            externalAppDir.mkdir();
        }

        file = new File(externalAppDir , "shapes.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        createFile(activity.getApplicationContext());
        String fileContent = "";
        for (TableItem tableItem : tableItems) {
            String shapeName = tableItem.getShapeName();
            String xStart = tableItem.getxStart();
            String yStart = tableItem.getyStart();
            String xEnd = tableItem.getxEnd();
            String yEnd = tableItem.getyEnd();
            fileContent += shapeName + "\t\t" + xStart + "\t\t" + yStart + "\t\t" + xEnd + "\t\t" + yEnd + "\n";
        }
        try {
            FileOutputStream stream = new FileOutputStream(file);
            try {
                stream.write(fileContent.getBytes());
            } finally {
                stream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
