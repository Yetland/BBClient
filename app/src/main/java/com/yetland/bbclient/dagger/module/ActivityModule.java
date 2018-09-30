package com.yetland.bbclient.dagger.module;

import com.yetland.base.base.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
@Module
public class ActivityModule {

    private BaseActivity mBaseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        mBaseActivity = baseActivity;
    }

    @Provides
    BaseActivity provideBaseActivity(){
        return mBaseActivity;
    }
}
