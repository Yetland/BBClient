package com.yetland.data.dagger.component;

import com.yetland.data.api.AppApiImpl;
import com.yetland.data.api.UserApiImpl;
import com.yetland.data.dagger.module.DataModule;

import dagger.Component;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
@Component(modules = DataModule.class)
public interface DataComponent {
    UserApiImpl provideUserApiImpl();

    AppApiImpl provideAppServiceImpl();
}
