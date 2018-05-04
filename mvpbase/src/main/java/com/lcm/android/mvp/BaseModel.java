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

