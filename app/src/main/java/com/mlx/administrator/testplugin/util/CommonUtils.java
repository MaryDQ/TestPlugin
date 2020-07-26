package com.mlx.administrator.testplugin.util;

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
}
