package com.lcm.android.base

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.zhy.autolayout.AutoFrameLayout
import com.zhy.autolayout.AutoLinearLayout
import com.zhy.autolayout.AutoRelativeLayout

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 17:10
 * Desc:
 * *****************************************************************
 */
abstract class BaseActivity : RxAppCompatActivity() {
    protected var mApplication: BaseApplication? = null
    protected var mContext: Context? = null

    private var bind: Unbinder? = null

    companion object {
        val LAYOUT_LINEARLAYOUT: String = "LinearLayout"
        val LAYOUT_FRAMELAYOUT: String = "FrameLayout"
        val LAYOUT_RELATIVELAYOUT: String = "RelativeLayout"
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View?{
        var view: View? = null
        if (name == LAYOUT_FRAMELAYOUT) {
            view = AutoFrameLayout(context, attrs)
        }
        if (name == LAYOUT_LINEARLAYOUT) {
            view = AutoLinearLayout(context, attrs)
        }
        if (name == LAYOUT_RELATIVELAYOUT) {
            view = AutoRelativeLayout(context, attrs)
        }

        return if (view != null) view else super.onCreateView(name, context, attrs)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootView())
        bind = ButterKnife.bind(this)
        mApplication = application as BaseApplication
        mContext = this

        synchronized(BaseActivity::class.java) {
            mApplication?.mActivityList?.add(this)
        }

        initView()
        initData()
    }

    abstract fun rootView(): Int

    abstract fun initView()

    abstract fun initData()


    override fun onDestroy() {
        super.onDestroy()
        synchronized(BaseActivity::class.java) {
            mApplication?.mActivityList?.remove(this)
        }
        bind?.unbind()
    }
}