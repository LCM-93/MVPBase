package com.chaman.app.dagger.component;

import android.app.Application;

import com.chaman.android.dagger.module.AppModule;
import com.chaman.android.dagger.module.ClientModule;
import com.chaman.app.dagger.module.CacheModule;
import com.chaman.app.dagger.module.ServiceModule;
import com.chaman.app.data.api.ApiManager;
import com.chaman.app.data.api.CacheManager;
import com.chaman.app.data.api.CommonApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ****************************************************************
 * Author: Chaman
 * Date: 2017/5/23 下午1:53
 * Desc:
 * *****************************************************************
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class, ServiceModule.class, CacheModule.class})
public interface AppComponent {


    Application Application();

    //服务管理器,retrofitApi
    ApiManager serviceManager();

    CacheManager cacheManager();


    CommonApi commonApi();

}
