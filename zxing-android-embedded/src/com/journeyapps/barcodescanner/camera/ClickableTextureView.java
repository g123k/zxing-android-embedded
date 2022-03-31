package com.journeyapps.barcodescanner.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.TextureView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class ClickableTextureView extends TextureView implements ClickableViewListener {

    private final GestureDetector gestureDetector;
    private OnSurfaceTouchListener onTouchListener;

    public ClickableTextureView(@NonNull Context context) {
        this(context, null);
    }

    public ClickableTextureView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClickableTextureView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gestureDetector = new GestureDetector(context, new GestureListener(this));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClickableTextureView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        gestureDetector = new GestureDetector(context, new GestureListener(this));
    }

    public OnSurfaceTouchListener getOnSurfaceTouchListener() {
        return onTouchListener;
    }

    public void setOnSurfaceTouchListener(OnSurfaceTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public void onSingleTap(float x, float y) {
        if (onTouchListener != null) {
            onTouchListener.onSingleTap(getWidth(), getHeight(), x, y);
        }
    }

    @Override
    public void onDoubleTap(float x, float y) {
        if (onTouchListener != null) {
            onTouchListener.onDoubleTap(getWidth(), getHeight(), x, y);
        }
    }

    @Override
    public void onLongTap(float x, float y) {
        if (onTouchListener != null) {
            onTouchListener.onLongTap(getWidth(), getHeight(), x, y);
        }
    }

    private static class GestureListener extends
            GestureDetector.SimpleOnGestureListener {

        private final ClickableViewListener onTouchListener;

        public GestureListener(ClickableViewListener onTouchListener) {
            this.onTouchListener = onTouchListener;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (onTouchListener != null) {
                onTouchListener.onDoubleTap(e.getX(), e.getY());
            }

            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            if (onTouchListener != null) {
                onTouchListener.onLongTap(e.getX(), e.getY());
            }
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (onTouchListener != null) {
                onTouchListener.onSingleTap(e.getX(), e.getY());
            }

            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }
    }
}
