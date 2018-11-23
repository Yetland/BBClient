package com.yetland.bbclient.dagger;

import com.yetland.base.base.BaseMvpActivity;
import com.yetland.bbclient.dagger.component.ActivityComponent;
import com.yetland.bbclient.dagger.component.DaggerActivityComponent;
import com.yetland.bbclient.dagger.module.ActivityModule;

/**
 * @author YETLAND
 * @date 2018/11/22 17:03
 */
public abstract class BaseAppDaggerActivity extends BaseMvpActivity {
    protected ActivityComponent mActivityComponent;

    @Override
    protected void inject() {
        mActivityComponent =
                DaggerActivityComponent.builder()
                        .appComponent(mAppComponent)
                        .dataComponent(mDataComponent)
                        .activityModule(new ActivityModule(mBaseActivity))
                        .build();
    }

    protected abstract void injectActivity();

}
