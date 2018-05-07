package com.lcm.app.ui.main;

import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.db.DBManager;
import com.lcm.app.data.entity.WelfareBean;
import com.lcm.app.data.entity.db.Dog;
import com.lcm.app.weight.ProgressObserver;
import com.lcm.app.data.entity.HttpBaseResult;


import java.util.List;
import java.util.Random;

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
        mainModel.getSplash()
                .subscribe(new Consumer<HttpBaseResult<List<WelfareBean>>>() {
                    @Override
                    public void accept(HttpBaseResult<List<WelfareBean>> listHttpBaseResult) throws Exception {
                        List<WelfareBean> results = listHttpBaseResult.getResults();
                        getMvpView().onLoadSuccess("网络下载数据：："+results.toString());
                    }
                });


    }

    public void loadWithCache() {
        mainModel.load()
                .subscribe(new ProgressObserver<HttpBaseResult<List<String>>>(getMvpView().getActivityContext()) {
                    @Override
                    public void onNext(HttpBaseResult<List<String>> listHttpBaseResult) {
                        super.onNext(listHttpBaseResult);
                        getMvpView().onLoadSuccess(listHttpBaseResult.getResults().toString());
                    }
                });
    }

    public void inputDb(){
        Random random = new Random();
        int i = random.nextInt(100);

        Dog dog = new Dog();
        dog.setName("dog_"+i);
        dog.setAge(i);
        dbManager.addDog(dog);
    }

    public void readDb() {
        List<Dog> allDog = dbManager.findAllDog();
        getMvpView().onLoadSuccess(allDog.toString());
    }
}
