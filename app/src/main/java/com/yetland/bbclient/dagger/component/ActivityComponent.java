package com.yetland.bbclient.dagger.component;

import com.yetland.base.dagger.component.AppComponent;
import com.yetland.bbclient.MainActivity;
import com.yetland.bbclient.dagger.module.ActivityModule;
import com.yetland.bbclient.launcher.LauncherActivity;
import com.yetland.data.dagger.component.DataComponent;

import dagger.Component;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */

@Component(dependencies = {AppComponent.class, DataComponent.class}, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void injectLauncher(LauncherActivity launcherActivity);
}
