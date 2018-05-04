package com.lcm.app.main


import android.content.Context

import com.lcm.android.mvp.BaseView

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午12:59
 * Desc:
 * *****************************************************************
 */

interface MainView : BaseView {

    fun getContext(): Context

    fun onLoadSuccess(str: String)

}
