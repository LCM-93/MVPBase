package com.lcm.app.main;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.lcm.android.mvp.BaseModel;
import com.lcm.app.data.api.ApiManager;
import com.lcm.app.data.api.CacheManager;
import com.lcm.app.data.api.DataManager;
import com.lcm.app.data.db.DBManager;
import com.lcm.app.data.entity.HttpBaseResult;
import com.lcm.app.data.entity.WelfareBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.EvictProvider;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/25 下午4:16
 * Desc:
 * *****************************************************************
 */

public class MainModel extends BaseModel<DataManager> {

    @Inject
    public MainModel(DataManager dataManager) {
        super(dataManager);
    }

    public Observable<HttpBaseResult<List<String>>> load() {
        return  mDataManager.getCommonCache().getHistoryDateList(mDataManager.getCommonApi().getHistoryDateList(),new EvictProvider(NetworkUtils.isConnected()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(httpBaseResultReply -> {
                    LogUtils.e(httpBaseResultReply.getSource());
                    return httpBaseResultReply.getData();
                });
    }

    public Observable<HttpBaseResult<List<WelfareBean>>>  getSplah(){
        return mDataManager.getCommonApi().getSplash()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}