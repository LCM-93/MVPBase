package com.lcm.android.mvp

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午12:05
 * Desc:
 * *****************************************************************
 */


open class BaseModel<D>(protected var mDataManager: D?) {

    open fun onDestroy() {
        this.mDataManager = null
    }

}

