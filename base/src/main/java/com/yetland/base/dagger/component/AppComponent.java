package com.yetland.base.dagger.component;

import android.app.Application;
import android.content.Context;

import com.yetland.base.dagger.module.AppModule;

import dagger.Component;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
@Component(modules = AppModule.class)
public interface AppComponent {

    Context context();

    Application application();
}
