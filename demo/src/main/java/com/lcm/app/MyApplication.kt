package com.lcm.app

import com.lcm.android.base.BaseApplication
import com.lcm.app.dagger.component.AppComponent
import com.lcm.app.dagger.component.DaggerAppComponent
import com.lcm.app.dagger.module.DataModule

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 11:49
 * Desc:
 * *****************************************************************
 */
class MyApplication:BaseApplication(){

    var appComponent: AppComponent?=null
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .clientModule(clientModule)
                .dataModule(DataModule())
                .build()
    }


}