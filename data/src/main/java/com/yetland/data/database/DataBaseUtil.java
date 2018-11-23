package com.yetland.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.yetland.data.database.greenDao.DaoMaster;
import com.yetland.data.database.greenDao.DaoSession;

/**
 * @author YETLAND
 * @date 2018/11/22 16:09
 */
public class DataBaseUtil {

    private static DaoSession mDaoSession;

    public static void init(@NonNull Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "bb_client.db");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }


}
