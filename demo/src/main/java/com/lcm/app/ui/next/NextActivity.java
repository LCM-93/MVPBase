package com.lcm.app.ui.next;

import android.graphics.Color;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.lcm.android.base.BaseActivity;
import com.lcm.app.R;
import com.lcm.app.RouterConfig;

import butterknife.BindView;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/7/11 10:09
 * Desc:
 * *****************************************************************
 */
@Route(path = RouterConfig.NEXT)
public class NextActivity extends BaseActivity {

    @BindView(R.id.fakeView)
    View fakeView;

    @Override
    protected int rootView() {
        return R.layout.activity_next;
    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarColor(this,Color.TRANSPARENT);
        BarUtils.setStatusBarColor(fakeView, Color.TRANSPARENT, 15);
    }

    @Override
    protected void initData() {

    }


}
