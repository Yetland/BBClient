package com.yetland.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;
import com.yetland.data.database.BaseCacheEntity;
import com.yetland.data.database.greenDao.BaseCacheEntityDao;
import com.yetland.data.database.greenDao.DaoMaster;
import com.yetland.data.database.greenDao.DaoSession;

/**
 * @author YETLAND
 * @date 2018/10/9 10:10
 */
public class DataCenter {
    public static Context sContext;
    private static DaoSession mDaoSession;

    public static void init(@NonNull Context context) {
        sContext = context;
        Utils.init(context);

        // init database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "bb_client.db");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }

    public static BaseCacheEntity getCache(String id) {
        return mDaoSession.getBaseCacheEntityDao()
                .queryBuilder()
                .where(BaseCacheEntityDao.Properties.Id.eq(id))
                .build()
                .unique();
    }

    public static void cache(String id, String value) {
        BaseCacheEntity cacheEntity = new BaseCacheEntity();
        cacheEntity.setObjectId(id);
        cacheEntity.setValue(value);
        mDaoSession.insertOrReplace(cacheEntity);
    }

    public static void clearCache() {

    }

}
