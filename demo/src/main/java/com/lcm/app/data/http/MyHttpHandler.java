package com.lcm.app.data.http;

import com.blankj.utilcode.util.LogUtils;
import com.lcm.android.http.GlobeHttpHandler;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/4/26 17:22
 * Desc:
 * *****************************************************************
 */
public class MyHttpHandler implements GlobeHttpHandler {
    @Override
    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
        LogUtils.eTag("请求",request);
        LogUtils.eTag("请求",chain);

//        可以给所有请求增加请求头
        Request new_request = request.newBuilder()
                .addHeader("test","test-value")
                .build();

        return new_request;
    }

    @Override
    public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
        LogUtils.eTag("返回",response);
        LogUtils.eTag("返回",chain);
        LogUtils.eTag("返回",httpResult);
        return response;
    }


}
