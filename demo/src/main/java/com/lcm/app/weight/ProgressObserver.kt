package com.lcm.app.weight

import android.content.Context
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.ToastUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 11:31
 * Desc:
 * *****************************************************************
 */
open class ProgressObserver<T>(private val context: Context):Observer<T> ,ProgressCancelListener{
    private var mProgressDialogHandler:ProgressDialogHandler?=null
    init {
        mProgressDialogHandler = ProgressDialogHandler(context,this,true,true)
    }

    private fun showProgressDialog(){
        if(mProgressDialogHandler !=null){
            mProgressDialogHandler?.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG)?.sendToTarget()
        }
    }

    private fun dismissProgressDialog(){
        if(mProgressDialogHandler != null){
            mProgressDialogHandler?.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG)?.sendToTarget()
            mProgressDialogHandler=null
        }
    }

    override fun onComplete() {
        dismissProgressDialog()
    }

    override fun onSubscribe(d: Disposable) {
        showProgressDialog()
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        dismissProgressDialog()
        if(!NetworkUtils.isConnected()){
            ToastUtils.showShort("请检查网络连接")
        }
    }

    override fun onCancelProgress() {
    }
}