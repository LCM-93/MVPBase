package com.lcm.android.mvp;

import com.lcm.android.http.BaseDataManager;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午12:05
 * Desc:
 * *****************************************************************
 */


public class BaseModel<D extends BaseDataManager> {
    protected D mDataManager;

    public BaseModel(D mDataManager){
        this.mDataManager = mDataManager;
    }

    public void onDestory(){
        this.mDataManager = null;
    }

}

//public class BaseModel<S extends BaseServiceManager,C extends BaseCacheManager> {
//    protected S mServiceManager;
//    protected C mCacheManager;
//
//    public BaseModel(S serviceManager,C cacheManager){
//        this.mCacheManager = cacheManager;
//        this.mServiceManager = serviceManager;
//    }
//
//    public BaseModel(S serviceManger){
//        this.mServiceManager = serviceManger;
//    }
//
//
//    public void onDestory(){
//        this.mServiceManager = null;
//        this.mCacheManager = null;
//    }
//
//}
