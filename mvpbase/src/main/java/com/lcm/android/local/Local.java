package com.lcm.android.local;

import android.content.Context;
import android.text.TextUtils;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/9/2 09:12
 * Desc:
 * *****************************************************************
 */
public class Local {

    static {
        System.loadLibrary("native-lib");
    }


    public static native String wechatParam(Context context);

    public static native String qqParam(Context context);

    public static native String pushSecret(Context context);


    public static String[] getWechatParam(Context context) {
        String wechatParam = wechatParam(context);
        if (TextUtils.isEmpty(wechatParam)) return null;
        return wechatParam.split("\\|");
    }

    public static String[] getQQParam(Context context) {
        String qqParam = qqParam(context);
        if (TextUtils.isEmpty(qqParam)) return null;
        return qqParam.split("\\|");
    }


}
