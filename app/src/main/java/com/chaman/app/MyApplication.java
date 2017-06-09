package com.chaman.app;

import com.chaman.android.base.BaseApplication;
import com.chaman.app.dagger.component.AppComponent;
import com.chaman.app.dagger.component.DaggerAppComponent;
import com.chaman.app.dagger.module.CacheModule;
import com.chaman.app.dagger.module.ServiceModule;

/**
 * ****************************************************************
 * Author: Chaman
 * Date: 2017/5/23 下午12:58
 * Desc:
 * *****************************************************************
 */

public class MyApplication extends BaseApplication{
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(getAppModule())
                .clientModule(getClientModule())
                .serviceModule(new ServiceModule())
                .cacheModule(new CacheModule())
                .build();
    }

    @Override
    protected String getBaseUrl() {
        return "http://api.link-pay.cn/";
    }
    /**
     * 将AppComponent返回出去,供其它地方使用, AppComponent接口中声明的方法返回的实例, 在getAppComponent()拿到对象后都可以直接使用
     *
     * @return
     */
    public  AppComponent getAppComponent() {
        return mAppComponent;
    }


}
