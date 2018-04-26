package com.lcm.app.data.api;

import com.lcm.app.data.entity.HttpBaseResult;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/26 下午1:49
 * Desc: RxCache简介：https://www.jianshu.com/p/b58ef6b0624b
 * *****************************************************************
 */

public interface CommonCache {

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<HttpBaseResult<List<String>>>> getHistoryDateList(Observable<HttpBaseResult<List<String>>> oRepos, EvictDynamicKey evictDynamicKey);

}
