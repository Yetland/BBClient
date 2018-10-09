package com.yetland.base.data;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;

/**
 * @author YETLAND
 * @date 2018/10/9 9:46
 */
public class SPUtil {
    public static <T> void save(String key, T t) {
        Gson gson = new Gson();
        String value = gson.toJson(t);
        SPUtils.getInstance().put(key, value);
    }

    public static String getString(String key) {
        return SPUtils.getInstance().getString(key);
    }
}
