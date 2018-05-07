package com.lcm.app.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.lcm.android.base.BaseActivity
import com.lcm.app.R
import com.lcm.app.RouterConfig

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/7 11:49
 * Desc:
 * *****************************************************************
 */
@Route(path = RouterConfig.SPLASH)
class SplashActivity: BaseActivity() {

    override fun rootView(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {

    }

    override fun initData() {
    }
}