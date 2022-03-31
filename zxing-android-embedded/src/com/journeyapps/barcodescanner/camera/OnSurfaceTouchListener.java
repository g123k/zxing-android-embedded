package com.journeyapps.barcodescanner.camera;

public interface OnSurfaceTouchListener {
    void onSingleTap(int surfaceWidth, int surfaceHeight, float x, float y);
    void onDoubleTap(int surfaceWidth, int surfaceHeight, float x, float y);
    void onLongTap(int surfaceWidth, int surfaceHeight, float x, float y);
}