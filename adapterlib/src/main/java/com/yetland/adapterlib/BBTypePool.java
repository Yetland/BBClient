package com.yetland.adapterlib;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YETLAND
 * @date 2018/11/14 11:11
 */
public class BBTypePool implements TypePool {
    private List<Class<?>> mClassList;
    private List<BBBinder<?, ?>> mBinderList;

    BBTypePool() {
        mClassList = new ArrayList<>();
        mBinderList = new ArrayList<>();
    }

    @Override
    public <T> void register(Class<? extends T> clazz, BBBinder binder) {
        mClassList.add(clazz);
        mBinderList.add(binder);
    }

    @Override
    public int getViewType(Class<?> clazz) {
        for (int i = 0; i < mClassList.size(); i++) {
            if (mClassList.get(i) == clazz) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public BBBinder getBinder(int type) {
        return mBinderList.get(type);
    }
}
