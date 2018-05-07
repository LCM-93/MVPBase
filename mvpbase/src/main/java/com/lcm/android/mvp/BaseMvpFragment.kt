package com.lcm.android.mvp

import android.os.Bundle

import com.lcm.android.base.BaseFragment

import javax.inject.Inject

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午1:43
 * Desc:
 * *****************************************************************
 */

abstract class BaseMvpFragment<P : BaseMvpPresenter<*>> : BaseFragment() {

    @Inject
    lateinit var mPresenter: P


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        ComponentInject()
        super.onActivityCreated(savedInstanceState)
    }

    /**
     * 依赖注入的入口
     */
    protected abstract fun ComponentInject()


    override fun onDestroy() {
        mPresenter.onDetachView()//释放资源
        super.onDestroy()
    }
}
