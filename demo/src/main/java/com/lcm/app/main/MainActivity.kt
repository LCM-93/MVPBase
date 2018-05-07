package com.lcm.app.main

import android.content.Context
import android.widget.Button
import android.widget.TextView

import com.jakewharton.rxbinding2.view.RxView
import com.lcm.android.mvp.BaseMvpActivity
import com.lcm.app.MyApplication
import com.lcm.app.R
import com.lcm.app.dagger.component.DaggerActivityComponent

import java.util.concurrent.TimeUnit

import butterknife.BindView


class MainActivity : BaseMvpActivity<MainPresenter>(), MainView {

    @BindView(R.id.tv)
    lateinit var tv: TextView
    @BindView(R.id.btn)
    lateinit var btn: Button
    @BindView(R.id.btn2)
    lateinit var btn2: Button
    @BindView(R.id.btn3)
    lateinit var btn3: Button


    override fun rootView(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mPresenter.onAttachView(this)
        RxView.clicks(btn)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe { _ -> mPresenter.load() }

        RxView.clicks(btn2)
                .subscribe { _ -> mPresenter.getSplash() }

        RxView.clicks(btn3)
                .subscribe { _ -> mPresenter.readDb() }
    }

    override fun initData() {

    }


    override fun onLoadSuccess(str: String) {
        tv.text = str
    }

    override fun getContext(): Context {
        return this
    }


    override fun ComponentInject() {
        DaggerActivityComponent.builder()
                .appComponent((application as MyApplication).appComponent)
                .build()
                .inject(this)
    }

    override fun finishView() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetachView()
    }
}
