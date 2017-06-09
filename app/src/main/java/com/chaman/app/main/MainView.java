package com.chaman.app.main;

import com.chaman.android.mvp.BaseView;
import com.chaman.app.data.entity.BannerBean;

/**
 * ****************************************************************
 * Author: Chaman
 * Date: 2017/5/23 下午12:59
 * Desc:
 * *****************************************************************
 */

public interface MainView extends BaseView {

    void OnloadSuccess(BannerBean bannerBean);
}
