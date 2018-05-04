package com.lcm.android.base

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.lcm.android.BuildConfig
import com.lcm.android.dagger.module.AppModule
import com.lcm.android.dagger.module.ClientModule
import com.lcm.android.http.GlobeHttpHandler
import okhttp3.Interceptor
import java.util.*

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 17:45
 * Desc:
 * *****************************************************************
 */
abstract class BaseApplication: Application(){

    var mApplication:BaseApplication?=null

    var mActivityList:LinkedList<BaseActivity>?=null

    var mClientModule:ClientModule?=null
        private set

    var mAppModule:AppModule?=null
        private set

    init {
        mActivityList = LinkedList()
    }

    override fun onCreate() {
        super.onCreate()
        mApplication = this
        mClientModule = ClientModule.builder()
                .baseurl(BuildConfig.BaseUrl)
                .globeHttpHandler(getHttpHandler())
                .interceptors(getInterceptors())
                .build()

        mAppModule = AppModule(this)
        Utils.init(this)
    }

    /**
     * 这里可以提供一个全局处理http响应结果的处理类,
     * 这里可以比客户端提前一步拿到服务器返回的结果,可以做一些操作,比如token超时,重新获取
     * 默认不实现,如果有需求可以重写此方法
     *
     * @return
     */
    protected fun getHttpHandler():GlobeHttpHandler?{
        return null
    }

    /**
     * 用来提供interceptor,如果要提供额外的interceptor可以让子application实现此方法
     *
     * @return
     */
    protected fun getInterceptors():Array<Interceptor>?{
        return null
    }

}