package com.lcm.app.dagger.module;


import com.lcm.app.data.db.DBManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/4/26 19:57
 * Desc:
 * *****************************************************************
 */
@Module
public class DBModule {

    @Singleton
    @Provides
    DBManager provideDBManager(){
        return new DBManager();
    }
}
