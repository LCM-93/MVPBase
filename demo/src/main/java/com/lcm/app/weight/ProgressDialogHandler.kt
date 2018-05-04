package com.lcm.app.weight

import android.content.Context
import android.os.Handler
import android.os.Message
import com.kaopiz.kprogresshud.KProgressHUD

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 10:34
 * Desc:
 * *****************************************************************
 */
class ProgressDialogHandler : Handler {

    companion object {
        val SHOW_PROGRESS_DIALOG: Int = 1
        val DISMISS_PROGRESS_DIALOG: Int = 2
    }

    private var context: Context
    private var mProgressCancelListener: ProgressCancelListener
    private var cancelable: Boolean
    private var show: Boolean

    private var pd: KProgressHUD? = null


    constructor(context: Context, mProgressCancelListener: ProgressCancelListener, cancelable: Boolean, show: Boolean) : super() {
        this.context = context
        this.mProgressCancelListener = mProgressCancelListener
        this.cancelable = cancelable
        this.show = show
    }

    private fun initProgressDialog() {
        if (pd == null) {
            pd = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show()

            if (!pd!!.isShowing && show) {
                pd!!.show()
            }
        }
    }

    private fun dismissProgressDialog() {
        if (pd != null) {
            pd!!.dismiss()
            pd = null
        }
    }

    override fun handleMessage(msg: Message) {
        when (msg.what) {
            SHOW_PROGRESS_DIALOG -> initProgressDialog()
            DISMISS_PROGRESS_DIALOG -> dismissProgressDialog()
        }
    }
}