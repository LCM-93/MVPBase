package com.lcm.android.mvp


/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午12:10
 * Desc:
 * *****************************************************************
 */

open class BaseMvpPresenter<V : BaseView> : BasePresenter<V> {
    var mvpView: V? = null
        private set

    override fun onAttachView(view: V) {
        mvpView = view
    }

    override fun onDetachView() {
        mvpView = null
    }


}
