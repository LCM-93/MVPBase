package com.lcm.android.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 18:44
 * Desc:
 * *****************************************************************
 */
@Module
class AppModule (private val application:Application){

    @Provides
    @Singleton
    fun provideApplication():Application{
        return application
    }
}