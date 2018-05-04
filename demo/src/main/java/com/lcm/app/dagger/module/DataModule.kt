package com.lcm.app.dagger.module

import android.app.Application

import com.lcm.app.data.api.CommonApi
import com.lcm.app.data.api.CommonCache
import com.lcm.app.data.db.DBManager

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import io.rx_cache2.internal.RxCache
import retrofit2.Retrofit

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/4/26 19:00
 * Desc:
 * *****************************************************************
 */
@Module
class DataModule {

    @Singleton
    @Provides
    internal fun provideCommonApi(retrofit: Retrofit): CommonApi {
        return retrofit.create(CommonApi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideCommonCache(rxCache: RxCache): CommonCache {
        return rxCache.using(CommonCache::class.java)
    }


    @Singleton
    @Provides
    internal fun provideDBManager(application: Application): DBManager {
        return DBManager(application)
    }
}
