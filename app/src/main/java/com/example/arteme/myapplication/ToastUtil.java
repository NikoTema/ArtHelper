package com.example.arteme.myapplication;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

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

    public static void hideKeyboard(Activity activity)
    {
        try
        {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        catch (Exception e)
        {
            Log.e("KeyBoardUtil", e.toString(), e);
        }
    }
}
