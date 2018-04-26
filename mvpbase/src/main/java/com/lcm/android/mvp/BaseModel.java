package com.lcm.android.mvp;

import com.lcm.android.http.BaseCacheManager;
import com.lcm.android.http.BaseServiceManager;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午12:05
 * Desc:
 * *****************************************************************
 */

public class BaseModel<S extends BaseServiceManager,C extends BaseCacheManager> {
    protected S mServiceManager;
    protected C mCacheManager;

    public BaseModel(S serviceManager,C cacheManager){
        this.mCacheManager = cacheManager;
        this.mServiceManager = serviceManager;
    }

    public BaseModel(S serviceManger){
        this.mServiceManager = serviceManger;
    }


    public void onDestory(){
        this.mServiceManager = null;
        this.mCacheManager = null;
    }

}
