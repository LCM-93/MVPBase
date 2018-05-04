package com.lcm.app.dagger.component

import com.lcm.android.dagger.scope.FragmentScope

import dagger.Component

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/4/26 19:24
 * Desc:
 * *****************************************************************
 */
@FragmentScope
@Component(dependencies = arrayOf(AppComponent::class))
interface FragmentComponent
