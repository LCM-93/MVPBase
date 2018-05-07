package com.lcm.app.data.db;


import com.lcm.app.data.entity.db.Dog;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/4/26 19:43
 * Desc:
 * *****************************************************************
 */
@Singleton
public class DBManager {

    private Realm realm = null;

    @Inject
    public DBManager() {
    }


    public void addDog(Dog dog){
        if(realm == null) realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(dog);
        realm.commitTransaction();
        close();
    }


    public List<Dog> findAllDog(){
        if(realm == null) realm = Realm.getDefaultInstance();
        RealmResults<Dog> all = realm.where(Dog.class).findAll();
        List<Dog> res = realm.copyFromRealm(all);
        close();
        return res;
    }


    private void close(){
        if(realm != null){
            realm.close();
            realm = null;
        }
    }

}
