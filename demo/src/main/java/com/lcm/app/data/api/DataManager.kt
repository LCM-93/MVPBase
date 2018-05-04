package com.lcm.app.data.api

import com.lcm.android.http.BaseDataManager

import javax.inject.Inject
import javax.inject.Singleton

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/4/26 18:57
 * Desc:
 * *****************************************************************
 */
@Singleton
class DataManager @Inject
constructor(val commonApi: CommonApi, val commonCache: CommonCache) : BaseDataManager()
