package com.lcm.app.dagger.component

import android.app.Application

import com.lcm.android.dagger.module.AppModule
import com.lcm.android.dagger.module.ClientModule
import com.lcm.app.dagger.module.DataModule
import com.lcm.app.data.api.DataManager
import com.lcm.app.data.db.DBManager

import javax.inject.Singleton

import dagger.Component

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午1:53
 * Desc:
 * *****************************************************************
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, ClientModule::class, DataModule::class))
interface AppComponent {


    fun Application(): Application

    //服务管理器,retrofitApi
    //    ApiManager serviceManager();
    //
    //    CacheManager cacheManager();

    fun dataManager(): DataManager

    fun dbManager(): DBManager


}
