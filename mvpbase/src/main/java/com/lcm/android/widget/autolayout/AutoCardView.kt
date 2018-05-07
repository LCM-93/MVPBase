package com.lcm.android.widget.autolayout

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet

import com.zhy.autolayout.AutoFrameLayout
import com.zhy.autolayout.utils.AutoLayoutHelper

/**
 * Created by jess on 9/3/16 21:40
 * Contact with jess.yan.effort@gmail.com
 */
class AutoCardView : CardView {
    private val mHelper = AutoLayoutHelper(this)

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun generateLayoutParams(attrs: AttributeSet): AutoFrameLayout.LayoutParams {
        return AutoFrameLayout.LayoutParams(context, attrs)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!isInEditMode) {
            mHelper.adjustChildren()
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


}