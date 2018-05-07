package com.lcm.android.mvp

import android.support.annotation.UiThread

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午12:03
 * Desc:
 * *****************************************************************
 */

interface BasePresenter<V : BaseView> {

    @UiThread
    fun onAttachView(view: V)

    @UiThread
    fun onDetachView()

}
