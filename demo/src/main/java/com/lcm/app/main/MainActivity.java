package com.lcm.app.main;

import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.lcm.app.R;
import com.lcm.app.base.MvpActivity;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerActivityComponent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;


public class MainActivity extends MvpActivity<MainPresenter> implements MainView {


    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;


    @Override
    public void showEmpty() {

    }


    @Override
    protected int rootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        RxView.clicks(btn)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(o -> mPresenter.load());

        RxView.clicks(btn2)
                .subscribe(o -> mPresenter.getSplash());

        RxView.clicks(btn3)
                .subscribe(o -> mPresenter.readDb());
    }


    @Override
    protected void initData() {

    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void onLoadSuccess(String str) {
        tv.setText(str);
    }
}
