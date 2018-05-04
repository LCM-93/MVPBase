package com.lcm.app.main;



import android.content.Context;

import com.lcm.android.mvp.BaseView;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午12:59
 * Desc:
 * *****************************************************************
 */

public interface MainView extends BaseView {

    void onLoadSuccess(String str);

    Context getContext();

}
