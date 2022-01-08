package com.example.drawingapp;

public class TableItem {
    private final String shapeName;
    private final String xStart;
    private final String yStart;
    private final String xEnd;
    private final String yEnd;

    public TableItem(String shapeName, float xStart, float yStart, float xEnd, float yEnd) {
        this.shapeName = shapeName;
        this.xStart = String.valueOf(Math.round(xStart));
        this.yStart = String.valueOf(Math.round(yStart));
        this.xEnd = String.valueOf(Math.round(xEnd));
        this.yEnd = String.valueOf(Math.round(yEnd));
    }

    public String getShapeName() {
        return shapeName;
    }

    public String getxStart() {
        return xStart;
    }

    public String getyStart() {
        return yStart;
    }

    public String getxEnd() {
        return xEnd;
    }

    public String getyEnd() {
        return yEnd;
    }
}
