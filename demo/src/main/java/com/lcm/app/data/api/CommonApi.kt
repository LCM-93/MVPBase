package com.lcm.app.data.api

import com.lcm.app.data.entity.HttpBaseResult
import com.lcm.app.data.entity.WelfareBean
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.ArrayList

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 16:44
 * Desc:
 * *****************************************************************
 */
interface CommonApi {

    @GET("day/history")
    fun getHistoryDateList(): Observable<HttpBaseResult<ArrayList<String>>>

    @GET("random/data/福利/2")
    fun getSplash(): Observable<HttpBaseResult<ArrayList<WelfareBean>>>
}