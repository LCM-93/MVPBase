package com.lcm.app.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.greedao.dao.DaoMaster;
import com.greedao.dao.DaoSession;
import com.greedao.dao.WelfareBeanDao;
import com.lcm.app.BuildConfig;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/4/26 19:43
 * Desc:
 * *****************************************************************
 */
@Singleton
public class DBManager {
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private final String DBName = BuildConfig.DB_Name;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private SQLiteDatabase db;
    private Context context;
    private WelfareBeanDao welfareBeanDao;

    @Inject
    public DBManager(Context context) {
        this.context = context;
    }

    private void setDatabase() {
        devOpenHelper = new DaoMaster.DevOpenHelper(context, DBName, null);
        db = devOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    private DaoSession getDaoSession() {
        if (mDaoSession == null) {
            setDatabase();
        }
        return mDaoSession;
    }

    public WelfareBeanDao getWelfareBeanDao() {
        if (welfareBeanDao == null) {
            welfareBeanDao = getDaoSession().getWelfareBeanDao();
        }
        return welfareBeanDao;
    }
}
