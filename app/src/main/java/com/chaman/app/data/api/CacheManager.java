package com.chaman.app.data.api;

import com.chaman.android.http.BaseCacheManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * ****************************************************************
 * Author: Chaman
 * Date: 2017/5/26 下午1:48
 * Desc:
 * *****************************************************************
 */
@Singleton
public class CacheManager extends BaseCacheManager {

    private CommonCache commonCache;

    @Inject
    public CacheManager(CommonCache commonCache) {
        this.commonCache = commonCache;
    }

    public CommonCache getCacheAp(){
        return commonCache;
    }
}
