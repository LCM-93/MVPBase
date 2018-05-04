package com.lcm.app.data.entity

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 16:17
 * Desc:
 * *****************************************************************
 */
class HttpBaseResult<T> {

    private var error:Boolean=false
    var results:T?=null

    override fun toString(): String {
        return "HttpBaseResult(error=$error, results=$results)"
    }

    fun isError():Boolean{
        return error
    }

    fun setError(error: Boolean){
        this.error = error
    }


}