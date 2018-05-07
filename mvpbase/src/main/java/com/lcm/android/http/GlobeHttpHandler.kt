package com.lcm.android.http

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/7 09:18
 * Desc:
 * *****************************************************************
 */
interface GlobeHttpHandler {

    fun onHttpRequestBefore(chain:Interceptor.Chain,request: Request):Request


    fun onHttpResultResponse(httpResult:String,chain: Interceptor.Chain,response: Response):Response

}