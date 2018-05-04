package com.lcm.app.data.http

import com.blankj.utilcode.util.LogUtils
import com.lcm.android.http.GlobeHttpHandler
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 16:10
 * Desc:
 * *****************************************************************
 */
class MyHttpHandler:GlobeHttpHandler{


    /**
     * 可以在请求前添加统一请求头
     */
    override fun onHttpRequestBefore(chain: Interceptor.Chain, request: Request): Request {
        return request.newBuilder()
                .addHeader("test","test-value")
                .build()
    }

    /**
     * 最先获取到返回数据
     * 进行处理
     */
    override fun onHttpResultResponse(httpResult: String, chain: Interceptor.Chain, response: Response): Response {
        LogUtils.eTag("返回",response)
        LogUtils.eTag("返回",chain)
        LogUtils.eTag("返回",httpResult)
        return response
    }

}