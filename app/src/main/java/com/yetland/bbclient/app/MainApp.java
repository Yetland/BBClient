package com.yetland.bbclient.app;

import com.blankj.utilcode.util.Utils;
import com.yetland.base.app.App;
import com.yetland.data.DataCenter;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
public class MainApp extends App {

    @Override
    public void onCreate() {
        super.onCreate();
        DataCenter.init(this);
        Utils.init(this);
    }
}
