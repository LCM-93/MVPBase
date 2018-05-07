package com.lcm.android.widget.autolayout

import android.content.Context
import android.support.design.widget.CollapsingToolbarLayout
import android.util.AttributeSet
import android.view.ViewGroup

import com.zhy.autolayout.AutoLayoutInfo
import com.zhy.autolayout.utils.AutoLayoutHelper

/**
 * Created by jess on 16/4/14.
 */
class AutoCollapsingToolbarLayout : CollapsingToolbarLayout {
    private val mHelper = AutoLayoutHelper(this)

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!isInEditMode)
            mHelper.adjustChildren()
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return AutoCollapsingToolbarLayout.LayoutParams(context, attrs)
    }


    class LayoutParams : CollapsingToolbarLayout.LayoutParams, AutoLayoutHelper.AutoLayoutParams {
        private var mAutoLayoutInfo: AutoLayoutInfo?=null

        constructor(c: Context, attrs: AttributeSet) : super(c, attrs) {
            mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs)
        }

        override fun getAutoLayoutInfo(): AutoLayoutInfo {
            return mAutoLayoutInfo!!
        }


        constructor(width: Int, height: Int) : super(width, height) {}


        constructor(source: ViewGroup.LayoutParams) : super(source) {}

        constructor(source: ViewGroup.MarginLayoutParams) : super(source) {}

    }

}
