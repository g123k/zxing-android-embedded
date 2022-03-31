package com.journeyapps.barcodescanner;

import android.content.Context;
import android.graphics.Rect;
import android.os.Looper;
import android.util.DisplayMetrics;

/**
 *
 */
public class Util {
    public static void validateMainThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("Must be called from the main thread.");
        }
    }

    public static int clamp(int x, int min, int max) {
        if (x > max) {
            return max;
        } else if (x < min) {
            return min;
        } else {
            return x;
        }
    }

    public static int convertPixelsToDp(float px, Context context){
        return (int) (px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public static Rect invert(Rect rect) {
        return new Rect(rect.top, rect.left, rect.bottom, rect.right);
    }
}
