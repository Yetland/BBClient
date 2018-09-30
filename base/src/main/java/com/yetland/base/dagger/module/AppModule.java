package com.yetland.base.dagger.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
@Module
public class AppModule {

    private Context mContext;
    private Application mApplication;

    public AppModule(Context context, Application application) {
        mContext = context;
        mApplication = application;
    }

    @Provides
    Context provideContext(){
        return mContext;
    }

    @Provides
    Application provideApplication(){
        return mApplication;
    }
}
