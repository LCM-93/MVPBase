package com.lcm.app.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcm.android.base.BaseActivity;
import com.lcm.app.R;
import com.lcm.app.RouterConfig;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/7 17:05
 * Desc:
 * *****************************************************************
 */
@Route(path = RouterConfig.SPLASH)
public class SplashActivity extends BaseActivity {

    @Override
    protected int rootView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
