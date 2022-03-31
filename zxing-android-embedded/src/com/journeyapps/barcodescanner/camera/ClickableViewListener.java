package com.journeyapps.barcodescanner.camera;

public interface ClickableViewListener {
    void onSingleTap(float x, float y);
    void onDoubleTap(float x, float y);
    void onLongTap(float x, float y);
}
