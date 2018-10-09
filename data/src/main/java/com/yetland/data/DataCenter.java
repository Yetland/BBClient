package com.yetland.data;

import android.content.Context;

import com.blankj.utilcode.util.Utils;

/**
 * @author YETLAND
 * @date 2018/10/9 10:10
 */
public class DataCenter {
    public static Context sContext;

    public static void init(Context context) {
        sContext = context;
        Utils.init(context);
    }
}
