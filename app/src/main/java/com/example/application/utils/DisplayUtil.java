package com.example.application.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by zsl on 15/10/13.
 * 里面的方法用于db和px的转换
 */
public class DisplayUtil {

    /**
     * dp转换为px
     *
     * @param context
     * @param dp
     * @return
     */
    public static float dpToPx(Context context, float dp) {
        float px = getAbsValue(context, dp, TypedValue.COMPLEX_UNIT_DIP);
        return px;
    }

    private static float getAbsValue(Context context, float value, int unit) {
        return TypedValue.applyDimension(unit, value, context.getResources().getDisplayMetrics());
    }
}
