package com.lcm.android.mvp;



/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午12:10
 * Desc:
 * *****************************************************************
 */

public class BaseMvpPresenter<V extends BaseView> implements BasePresenter<V>{
    private V mMvpView;

    @Override
    public void onAttachView(V view) {
        mMvpView = view;
    }

    @Override
    public void onDetachView() {
        mMvpView = null;
    }

    public V getMvpView() {
        return mMvpView;
    }


}
