package com.lcm.app.ui.main


import com.lcm.android.mvp.BaseMvpPresenter
import com.lcm.app.data.db.DBManager
import com.lcm.app.weight.ProgressObserver
import com.lcm.app.data.entity.HttpBaseResult
import com.lcm.app.data.entity.db.Dog
import java.util.*

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
        mainModel.splash()
                .subscribe { listHttpBaseResult ->
                    val results = listHttpBaseResult.results
                    mvpView?.onLoadSuccess(results.toString())
                }

    }

    fun loadWithCache() {
        mainModel.load()
                .subscribe(object : ProgressObserver<HttpBaseResult<ArrayList<String>>>(mvpView?.getContext()!!) {
                    override fun onNext(listHttpBaseResult: HttpBaseResult<ArrayList<String>>) {
                        super.onNext(listHttpBaseResult)
                        mvpView?.onLoadSuccess(listHttpBaseResult.results.toString())
                    }
                })
    }


    fun readDb() {
        var findAllDog = dbManager.findAllDog()
        mvpView?.onLoadSuccess(findAllDog.toString())
    }

    fun inputDb() {
        val i = Random().nextInt(100)
        val dog = Dog()
        dog.name = "dog_$i"
        dog.age = i
        dbManager.addDog(dog)
    }
}
