package com.lcm.android.mvp

import android.os.Bundle

import com.lcm.android.base.BaseActivity

import javax.inject.Inject

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午1:40
 * Desc:
 * *****************************************************************
 */

abstract class BaseMvpActivity<P : BaseMvpPresenter<*>> : BaseActivity() {

    @Inject
    lateinit var mPresenter: P


    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentInject()//依赖注入
        super.onCreate(savedInstanceState)
    }

    /**
     * 依赖注入的入口
     */
    protected abstract fun ComponentInject()

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetachView()//释放资源
    }


}
