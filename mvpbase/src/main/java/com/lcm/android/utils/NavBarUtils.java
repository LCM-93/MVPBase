package com.lcm.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/6/13 09:39
 * Desc: 导航栏高度
 * *****************************************************************
 */
public class NavBarUtils {

    private static final String VIVO_NAVIGATION_GESTURE = "navigation_gesture_on"; //vivo手机设置中导航还是手势的值
    private static final String SMARTISAN_NAVIGATION_MODE = "navigationbar_trigger_mode"; //锤子手机手势还是导航
    /**
     * 一般情况下判读虚拟导航栏是否可见
     * @param activity
     * @return
     */
    public static boolean isNavigationBarShow_Normal(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = activity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y!=size.y;
        }else {
            boolean menu = ViewConfiguration.get(activity).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if(menu || back) {
                return false;
            }else {
                return true;
            }
        }
    }

    /**
     * 判读虚拟导航栏是否可见
     * @param activity
     * @return
     */
    public static boolean isNavigationBarShow(Activity activity){
        return isNavigationBarShow_Normal(activity) && !vivoNavigationGestureEnabled(activity) && !smartisanNavigationMode(activity);
    }


    /**
     * 获取vivo手机设置中的"navigation_gesture_on"值，判断当前系统是使用导航键还是手势导航操作
     * @return false 表示使用的是虚拟导航键(NavigationBar)， true 表示使用的是手势， 默认是false
     */
    public static boolean vivoNavigationGestureEnabled(Context context) {
        int val = Settings.Secure.getInt(context.getContentResolver(), VIVO_NAVIGATION_GESTURE, 0);
        return val != 0;
    }

    /***
     * 获取锤子手机导航栏模式  0导航栏  1手势操作
     * @return true 手势操作   false 默认导航栏
     */
    public static boolean smartisanNavigationMode(Context context){
        int val = Settings.Global.getInt(context.getContentResolver(), SMARTISAN_NAVIGATION_MODE, 0);
        return val != 0;
    }

    /**
     * 获取虚拟导航栏的高度
     * @param activity
     * @return
     */
    public static int getNavigationBarHeight(Activity activity) {
        if (!isNavigationBarShow(activity)){
            return 0;
        }
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

}
