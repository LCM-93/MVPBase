package com.lcm.app.data.api

import com.lcm.app.data.entity.HttpBaseResult

import java.util.ArrayList
import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.rx_cache2.EvictProvider
import io.rx_cache2.LifeCache
import io.rx_cache2.Reply

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 16:53
 * Desc: RxCache简介：https://www.jianshu.com/p/b58ef6b0624b
 * *****************************************************************
 */

interface CommonCache {

    @LifeCache(duration = 20, timeUnit = TimeUnit.SECONDS)
    fun getHistoryDateList(oRepos: Observable<HttpBaseResult<ArrayList<String>>>): Observable<Reply<HttpBaseResult<ArrayList<String>>>>

}
