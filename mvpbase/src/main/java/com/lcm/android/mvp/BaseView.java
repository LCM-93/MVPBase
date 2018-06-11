package com.lcm.android.mvp;

import android.content.Context;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午12:01
 * Desc:
 * *****************************************************************
 */

public interface BaseView {
    Context getActivityContext();

    void finishView();
}
