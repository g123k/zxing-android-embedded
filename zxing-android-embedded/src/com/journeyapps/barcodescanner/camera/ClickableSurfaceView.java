package com.journeyapps.barcodescanner.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class ClickableSurfaceView extends SurfaceView implements ClickableViewListener {

    private final GestureDetector gestureDetector;
    private OnSurfaceTouchListener onTouchListener;

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public ClickableSurfaceView(@NonNull Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public ClickableSurfaceView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public ClickableSurfaceView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gestureDetector = new GestureDetector(context, new GestureListener(this));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClickableSurfaceView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
    public void onSingleTap(double x, double y) {
        if (onTouchListener != null) {
            onTouchListener.onSingleTap(getWidth(), getHeight(), x, y);
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
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (onTouchListener != null) {
                onTouchListener.onSingleTap(e.getX(), e.getY());
            }

            return true;
        }



    }
}
