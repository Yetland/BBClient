package com.yetland.base.app;

import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.Utils;
import com.yetland.base.dagger.component.AppComponent;
import com.yetland.base.dagger.component.DaggerAppComponent;
import com.yetland.base.dagger.module.AppModule;
import com.yetland.data.dagger.component.DaggerDataComponent;
import com.yetland.data.dagger.component.DataComponent;
import com.yetland.data.dagger.module.DataModule;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
public class App extends MultiDexApplication {
    private DataComponent mDataComponent;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDataComponent();
        initAppComponent();

        Utils.init(this);
        CrashUtils.init();
    }

    private void initDataComponent() {
        mDataComponent = DaggerDataComponent.builder()
                .dataModule(new DataModule())
                .build();
    }

    private void initAppComponent() {
        mAppComponent = DaggerAppComponent.builder()
//                .dataComponent(mDataComponent)
                .appModule(new AppModule(this, this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public DataComponent getDataComponent() {
        return mDataComponent;
    }
}
