package com.yetland.adapterlib;

/**
 * @author YETLAND
 * @date 2018/11/14 11:10
 */
public interface TypePool {

    <T> void register(Class<? extends T> clazz, BBBinder binder);

    int getViewType(Class<?> clazz);

    BBBinder getBinder(int type);
}
