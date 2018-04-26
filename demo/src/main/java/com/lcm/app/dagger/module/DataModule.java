package com.lcm.app.dagger.module;

import com.lcm.app.data.api.CommonApi;
import com.lcm.app.data.api.CommonCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import retrofit2.Retrofit;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/4/26 19:00
 * Desc:
 * *****************************************************************
 */
@Module
public class DataModule {

    @Singleton
    @Provides
    CommonApi provideCommonApi(Retrofit retrofit) {
        return retrofit.create(CommonApi.class);
    }

    @Singleton
    @Provides
    CommonCache provideCommonCache(RxCache rxCache) {
        return rxCache.using(CommonCache.class);
    }
}
