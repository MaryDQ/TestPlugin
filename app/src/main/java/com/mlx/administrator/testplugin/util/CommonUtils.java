package com.mlx.administrator.testplugin.util;

import android.util.Log;

public class CommonUtils {
    // 两次点击间隔不能少于500ms
    private static long lastClickTime;

    public static boolean isFastClick(int id) {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime < lastClickTime
                || (currentClickTime - lastClickTime) >= 700) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime < lastClickTime
                || (currentClickTime - lastClickTime) >= 700) {
            Log.d("mlxcs","正常点击");
            flag = false;
        }
        if (flag) {
            Log.d("mlxcs","快速点击");
        }
        lastClickTime = currentClickTime;
        return flag;
    }
}
