package com.lcm.app.data.api;

import com.lcm.android.http.BaseDataManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/4/26 18:57
 * Desc:
 * *****************************************************************
 */
@Singleton
public class DataManager  extends BaseDataManager{

    private CommonApi commonApi;
    private CommonCache commonCache;

    @Inject
    public DataManager(CommonApi commonApi, CommonCache commonCache) {
        this.commonApi = commonApi;
        this.commonCache = commonCache;
    }


    public CommonApi getCommonApi() {
        return commonApi;
    }

    public CommonCache getCommonCache() {
        return commonCache;
    }
}
