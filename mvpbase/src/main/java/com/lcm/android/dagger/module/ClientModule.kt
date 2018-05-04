package com.lcm.android.dagger.module

import android.app.Application
import com.blankj.utilcode.util.SPUtils
import com.lcm.android.BuildConfig
import com.lcm.android.http.GlobeHttpHandler
import com.lcm.android.http.RequestIntercept
import com.lcm.android.utils.SPHelper
import dagger.Module
import dagger.Provides
import io.rx_cache2.internal.RxCache
import io.victoralbertos.jolyglot.GsonSpeaker
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 18:52
 * Desc:
 * *****************************************************************
 */
@Module
class ClientModule {
    companion object {
        var TIME_OUT:Long = 10
        var HTTP_RESPONSE_DISK_CACHE_MAX_SIZE:Long = 10 * 1024 * 1024

        fun builder():Builder{
            return Builder()
        }
    }

    var mApiUrl: HttpUrl? = null
    var mHandler: GlobeHttpHandler? = null
    var mInterceptors: Array<Interceptor>? = null

    constructor(builder:Builder){
        this.mApiUrl = builder.apiUrl
        this.mHandler = builder.handler
        this.mInterceptors = builder.interceptors
    }

    @Singleton
    @Provides
    fun provideRetrofit(client:OkHttpClient,httpUrl:HttpUrl):Retrofit{
        var builder:Retrofit.Builder = Retrofit.Builder()
        return configureRetrofit(builder,client,httpUrl)
    }

    @Singleton
    @Provides
    fun provideClient(cache:Cache,interceptor: Interceptor):OkHttpClient{
        var okHttpClient:OkHttpClient.Builder = OkHttpClient.Builder()
        return configureClient(okHttpClient,cache,interceptor)
    }

    @Singleton
    @Provides
    fun provideBaseUrl():HttpUrl{
        return mApiUrl!!
    }

    @Singleton
    @Provides
    fun provideCache(cacheFile:File):Cache{
        return Cache(cacheFile, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE)
    }


    @Singleton
    @Provides
    fun provideIntercept():Interceptor{
        return RequestIntercept(mHandler)
    }

    @Singleton
    @Provides
    fun provideCacheFile(application: Application):File{
        return SPHelper.getCacheFile(application)
    }

    /**
     * 提供RxCache客户端
     */
    @Singleton
    @Provides
    fun provideRxCache(cacheDir:File):RxCache{
        return RxCache.Builder().persistence(cacheDir,GsonSpeaker())
    }


    private fun configureRetrofit(builder:Retrofit.Builder,client:OkHttpClient,httpUrl: HttpUrl):Retrofit{
        return builder.baseUrl(httpUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun configureClient(okHttpClient: OkHttpClient.Builder,cache: Cache,interceptor: Interceptor):OkHttpClient{
        var builder:OkHttpClient.Builder = okHttpClient.connectTimeout(TIME_OUT,TimeUnit.SECONDS)
                .readTimeout(TIME_OUT,TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(interceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(if(BuildConfig.DEBUG)HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))

        if (mInterceptors != null && mInterceptors?.size!!>0){
            for (interceptor in mInterceptors!!){
                builder.addInterceptor(interceptor)
            }
        }

        return builder.build()
    }


    class Builder {
        var apiUrl:HttpUrl?=null
            private set
        var handler:GlobeHttpHandler?=null
            private set
        var interceptors:Array<Interceptor>?=null
            private set

        constructor()

        fun baseurl(baseurl:String):Builder{
            this.apiUrl = HttpUrl.parse(baseurl)
            return this
        }

        fun globeHttpHandler(handler:GlobeHttpHandler?):Builder{
            this.handler = handler
            return this
        }

        fun interceptors(interceptors:Array<Interceptor>?):Builder{
            this.interceptors = interceptors
            return this
        }

        fun build():ClientModule{
            if (apiUrl == null){
                throw IllegalStateException("baseurl is required")
            }
            return ClientModule(this)
        }

    }
}