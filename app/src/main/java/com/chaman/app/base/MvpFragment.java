package com.chaman.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.utils.ToastUtils;
import com.chaman.android.mvp.BaseMvpFragment;
import com.chaman.android.mvp.BaseMvpPresenter;
import com.chaman.android.mvp.BaseView;
import com.chaman.app.MyApplication;
import com.chaman.app.dagger.component.AppComponent;

/**
 * ****************************************************************
 * Author: Chaman
 * Date: 2017/5/23 下午6:11
 * Desc:
 * *****************************************************************
 */

public abstract class MvpFragment<P extends BaseMvpPresenter> extends BaseMvpFragment<P> implements BaseView {
    protected MyApplication application;
    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void showMessage(String str) {
        ToastUtils.showShortToast(str);
    }



    @Override
    public void finishView() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.onAttachView(this);
        initView();
        initData();

    }



    @Override
    protected void ComponentInject() {
        application = (MyApplication) getActivity().getApplication();
        setupActivityComponent(application.getAppComponent());
    }


    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupActivityComponent(AppComponent appComponent);




}
