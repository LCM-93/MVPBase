package com.lcm.android.http

import com.lcm.android.utils.ZipHelper
import okhttp3.*
import okio.Buffer
import okio.BufferedSource
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/7 09:26
 * Desc:
 * *****************************************************************
 */
class RequestIntercept(private val mHandler: GlobeHttpHandler?) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        var requestBuffer: Buffer = Buffer()
        if (request.body() != null) {
            request.body().writeTo(requestBuffer)
        }

        //在请求服务器之前可以拿到request，做一些操作 比如给request添加header，如果不做操作则返回参数中的request
        if (mHandler != null)
            request = mHandler.onHttpRequestBefore(chain, request)

        var originalResponse: Response = chain.proceed(request)

        //读取服务器返回的结果
        var responseBody: ResponseBody = originalResponse.body()
        var source: BufferedSource = responseBody.source()
        source.request(Long.MAX_VALUE)
        var buffer: Buffer = source.buffer()

        //获取content的压缩类型
        var encoding: String? = originalResponse.headers().get("Content-Encoding")

        var clone: Buffer = buffer.clone()

        var bodyString: String

        //解析response content
        if (encoding != null && encoding.equals("gzip", true)) { //使用gzip压缩
            var outputStream: ByteArrayOutputStream = ByteArrayOutputStream()
            clone.writeTo(outputStream)
            bodyString = ZipHelper.decompressForGzip(outputStream.toByteArray())
            outputStream.close()
        } else if (encoding != null && encoding.equals("zlib", true)) { //使用zlib压缩
            var outputStream: ByteArrayOutputStream = ByteArrayOutputStream()
            clone.writeTo(outputStream)
            bodyString = ZipHelper.decompressToStringForZlib(outputStream.toByteArray())
            outputStream.close()
        } else {  //content没有被压缩
            var charset: Charset = Charset.forName("UTF-8")
            var contentType: MediaType?= responseBody.contentType()

            if(contentType != null) charset = contentType.charset(charset)

            bodyString = clone.readString(charset)
        }


        if (mHandler != null)
            return mHandler.onHttpResultResponse(bodyString, chain, originalResponse)

        return originalResponse
    }
}