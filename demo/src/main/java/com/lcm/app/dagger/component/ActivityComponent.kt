package com.lcm.app.dagger.component

import com.lcm.android.dagger.scope.ActivityScope
import com.lcm.app.ui.main.MainActivity

import dagger.Component

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午1:59
 * Desc:
 * *****************************************************************
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}
