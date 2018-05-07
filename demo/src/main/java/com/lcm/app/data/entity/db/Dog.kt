package com.lcm.app.data.entity.db

import io.realm.RealmObject
import io.realm.annotations.RealmClass

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/7 15:31
 * Desc:
 * *****************************************************************
 */

open class Dog: RealmObject(){

    open var name:String?=null

    open var age:Int = 0


    override fun toString(): String {
        return "Dog(name=$name, age=$age)"
    }
}