package com.lcm.app.main;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.lcm.android.mvp.BaseModel;
import com.lcm.app.data.api.ApiManager;
import com.lcm.app.data.api.CacheManager;
import com.lcm.app.data.entity.HttpBaseResult;

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

public class MainModel extends BaseModel<ApiManager, CacheManager> {

    @Inject
    public MainModel(ApiManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }

    public Observable<HttpBaseResult<List<String>>> load() {
        return mCacheManager.getCommonCache().getHistoryDateList(mServiceManager.getCommonApi().getHistoryDateList(),new EvictProvider(NetworkUtils.isConnected()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(httpBaseResultReply -> {
                    LogUtils.e(httpBaseResultReply.getSource());
                    return httpBaseResultReply.getData();
                });
    }
}
