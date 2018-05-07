package com.lcm.app.data.db



import com.lcm.app.data.entity.db.Dog
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/4/26 19:43
 * Desc:
 * *****************************************************************
 */
@Singleton
class DBManager @Inject constructor() {

    private var realm: Realm?=null

    fun addDog(dog:Dog){
        if(realm == null) realm = Realm.getDefaultInstance()
        realm!!.beginTransaction()
        realm!!.copyToRealm(dog)
        realm!!.commitTransaction()
        close()
    }

    fun findAllDog():List<Dog>{
        if(realm == null) realm = Realm.getDefaultInstance()
        val findAll:RealmResults<Dog> = realm!!.where(Dog::class.java).findAll()
        val res =  realm!!.copyFromRealm(findAll)
        close()
        return res
    }

    private fun close(){
        if(realm != null){
            realm!!.close()
            realm = null
        }
    }
}
