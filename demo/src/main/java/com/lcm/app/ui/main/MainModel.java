package com.lcm.app.ui.main;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lcm.android.mvp.BaseModel;
import com.lcm.app.data.api.DataManager;
import com.lcm.app.data.entity.HttpBaseResult;
import com.lcm.app.data.entity.WelfareBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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
                    ToastUtils.showShort("数据来源：："+httpBaseResultReply.getSource());
                    LogUtils.e("数据来源：："+httpBaseResultReply.getSource());
                    return httpBaseResultReply.getData();
                });
    }

    public Observable<HttpBaseResult<List<WelfareBean>>>  getSplash(){
        return mDataManager.getCommonApi().getSplash()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
