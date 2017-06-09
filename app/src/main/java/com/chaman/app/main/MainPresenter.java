package com.chaman.app.main;

import com.blankj.utilcode.utils.LogUtils;
import com.chaman.android.mvp.BaseMvpPresenter;
import com.chaman.app.weight.ProgressObserver;
import com.chaman.app.data.entity.BannerBean;
import com.chaman.app.data.entity.HttpBaseResult;

import java.util.List;

import javax.inject.Inject;


/**
 * ****************************************************************
 * Author: Chaman
 * Date: 2017/5/23 下午12:59
 * Desc:
 * *****************************************************************
 */

public class MainPresenter extends BaseMvpPresenter<MainView> {

    @Inject
    MainModel mainModel;

    @Inject
    public MainPresenter() {

    }

    public void load(){
      mainModel.load()
                .subscribe(new ProgressObserver<HttpBaseResult<List<BannerBean>>>(getmMvpView().getActivityContext()){
                    @Override
                    public void onNext(HttpBaseResult<List<BannerBean>> listHttpBaseResult) {
                        super.onNext(listHttpBaseResult);
                        LogUtils.e("Chaman",listHttpBaseResult.getData().get(0).toString());
                        getmMvpView().OnloadSuccess(listHttpBaseResult.getData().get(0));
                    }
                });

    }
}
