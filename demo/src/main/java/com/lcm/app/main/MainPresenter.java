package com.lcm.app.main;

import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.api.DataManager;
import com.lcm.app.data.db.DBManager;
import com.lcm.app.data.entity.WelfareBean;
import com.lcm.app.weight.ProgressObserver;
import com.lcm.app.data.entity.HttpBaseResult;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;


/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午12:59
 * Desc:
 * *****************************************************************
 */

public class MainPresenter extends BaseMvpPresenter<MainView> {

    @Inject
    MainModel mainModel;

    @Inject
    DBManager dbManager;

    @Inject
    public MainPresenter() {

    }

    public void load() {
//        mainModel.load()
////                .subscribe(new Consumer<HttpBaseResult<List<String>>>() {
////                    @Override
////                    public void accept(HttpBaseResult<List<String>> listHttpBaseResult) throws Exception {
////                        LogUtils.eTag("LCM", listHttpBaseResult.getResults());
////                    }
////                }, new Consumer<Throwable>() {
////                    @Override
////                    public void accept(Throwable throwable) throws Exception {
////                        LogUtils.eTag("LCM", throwable.getMessage());
////                    }
////                }, new Action() {
////                    @Override
////                    public void run() throws Exception {
////                        LogUtils.eTag("LCM", "complete");
////                    }
////                });

        mainModel.load()
                .subscribe(new ProgressObserver<HttpBaseResult<List<String>>>(getMvpView().getActivityContext()) {
                    @Override
                    public void onNext(HttpBaseResult<List<String>> listHttpBaseResult) {
                        super.onNext(listHttpBaseResult);
                        getMvpView().onLoadSuccess(listHttpBaseResult.getResults().toString());
                    }
                });

    }


    public void getSplash() {
        mainModel.getSplah()
                .subscribe(new Consumer<HttpBaseResult<List<WelfareBean>>>() {
                    @Override
                    public void accept(HttpBaseResult<List<WelfareBean>> listHttpBaseResult) throws Exception {
                        List<WelfareBean> results = listHttpBaseResult.getResults();
                        getMvpView().onLoadSuccess("网络下载数据：："+results.toString());
                        for (WelfareBean w :
                                results) {
//                            dbManager.getWelfareBeanDao().insert(w);
                        }
                    }
                });
    }


    public void readDb() {
//        Query<WelfareBean> build = dbManager.getWelfareBeanDao().queryBuilder().build();
//        List<WelfareBean> list = build.list();
//        getMvpView().onLoadSuccess("从数据库中读取：："+list.toString());
    }
}
