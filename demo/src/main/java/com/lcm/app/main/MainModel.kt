package com.lcm.app.main

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import com.lcm.android.mvp.BaseModel
import com.lcm.app.data.api.DataManager
import com.lcm.app.data.entity.HttpBaseResult
import com.lcm.app.data.entity.WelfareBean

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.rx_cache2.EvictProvider

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/25 下午4:16
 * Desc:
 * *****************************************************************
 */

class MainModel @Inject
constructor(dataManager: DataManager) : BaseModel<DataManager>(dataManager) {

    fun splah(): Observable<HttpBaseResult<List<WelfareBean>>>{
        return mDataManager.commonApi.splash
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun load(): Observable<HttpBaseResult<List<String>>> {
        return mDataManager.commonCache.getHistoryDateList(mDataManager.commonApi.historyDateList, EvictProvider(NetworkUtils.isConnected()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { httpBaseResultReply ->
                    LogUtils.e(httpBaseResultReply.source)
                    httpBaseResultReply.data
                }
    }
}
