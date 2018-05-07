package com.lcm.app

import com.alibaba.android.arouter.launcher.ARouter
import com.lcm.android.base.BaseApplication
import com.lcm.app.dagger.component.AppComponent
import com.lcm.app.dagger.component.DaggerAppComponent
import com.lcm.app.dagger.module.DataModule
import com.squareup.leakcanary.LeakCanary
import io.realm.Realm
import io.realm.RealmConfiguration

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
                .appModule(mAppModule)
                .clientModule(mClientModule)
                .dataModule(DataModule())
                .build()


        ARouter.openDebug()
        ARouter.openLog()
        ARouter.init(this)

        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().name(BuildConfig.DB_Name).build())

        LeakCanary.install(this)
    }



}