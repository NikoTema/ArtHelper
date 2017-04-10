package com.example.arteme.myapplication;


import android.app.Activity;
import android.graphics.Color;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

public class ToastUtil {
    public static void showErrorToast(Activity activity, String message) {
        SuperActivityToast.create(activity, new Style(), Style.TYPE_STANDARD)
                .setProgressBarColor(Color.BLUE)
                .setIconResource(R.drawable.ic_sync_error)
                .setText(message)
                .setDuration(Style.DURATION_VERY_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_POP).show();
    }

    public static void showSuccessToast(Activity activity, String message) {
        SuperActivityToast.create(activity, new Style(), Style.TYPE_STANDARD)
                .setProgressBarColor(Color.BLUE)
                .setIconResource(R.drawable.ic_succ)
                .setText(message)
                .setDuration(Style.DURATION_VERY_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_GREEN))
                .setAnimations(Style.ANIMATIONS_POP).show();
    }
}
