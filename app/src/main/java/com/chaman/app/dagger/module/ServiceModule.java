package com.chaman.app.dagger.module;



import com.chaman.app.data.api.CommonApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * ****************************************************************
 * Author: Chaman
 * Date: 2017/5/23 下午2:50
 * Desc:
 * *****************************************************************
 */
@Module
public class ServiceModule {

    @Singleton
    @Provides
    CommonApi provideCommonService(Retrofit retrofit) {
        return retrofit.create(CommonApi.class);
    }

}
