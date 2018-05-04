package com.lcm.app.main


import com.lcm.android.mvp.BaseMvpPresenter
import com.lcm.app.data.db.DBManager
import com.lcm.app.weight.ProgressObserver
import com.lcm.app.data.entity.HttpBaseResult

import javax.inject.Inject



/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午12:59
 * Desc:
 * *****************************************************************
 */

class MainPresenter @Inject
constructor() : BaseMvpPresenter<MainView>() {

    @Inject
    lateinit var mainModel: MainModel

    @Inject
    lateinit var dbManager: DBManager

    fun load() {
        mainModel!!.load()
                .subscribe(object : ProgressObserver<HttpBaseResult<List<String>>>(mvpView.getContext()) {
                    override fun onNext(listHttpBaseResult: HttpBaseResult<List<String>>) {
                        super.onNext(listHttpBaseResult)
                        mvpView.onLoadSuccess(listHttpBaseResult.results.toString())
                    }
                })
    }


    fun getSplash() {
        mainModel!!.splah()
                .subscribe { listHttpBaseResult ->
                    val results = listHttpBaseResult.results
                    mvpView.onLoadSuccess("网络下载数据：：" + results.toString())
                    for (w in results) {
                        //                            dbManager.getWelfareBeanDao().insert(w);
                    }
                }
    }


    fun readDb() {
        //        Query<WelfareBean> build = dbManager.getWelfareBeanDao().queryBuilder().build();
        //        List<WelfareBean> list = build.list();
        //        getMvpView().onLoadSuccess("从数据库中读取：："+list.toString());
    }
}
