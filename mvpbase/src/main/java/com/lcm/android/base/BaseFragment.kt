package com.lcm.android.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.trello.rxlifecycle2.components.support.RxFragment

import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 16:58
 * Desc:
 * *****************************************************************
 */

abstract class BaseFragment : RxFragment() {

    private var mRootView: View? = null
    protected var mActivity: BaseActivity?=null
    protected var mContext: Context?=null
    private var bind: Unbinder? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(rootView(), null)
        bind = ButterKnife.bind(this, mRootView!!)
        return mRootView
    }

    protected abstract fun rootView(): Int

    protected abstract fun initView()

    protected abstract fun initData()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mActivity = activity as BaseActivity
        mContext = context
        initView()
        initData()
    }


    override fun onDestroy() {
        super.onDestroy()
        bind!!.unbind()
    }
}
