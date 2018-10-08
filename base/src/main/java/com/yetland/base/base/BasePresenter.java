package com.yetland.base.base;

/**
 * @author YETLAND
 * @date 2018/10/8 16:55
 */
public class BasePresenter<M, V> {
    protected M mM;
    protected V mV;

    public BasePresenter(M m, V v) {
        mM = m;
        mV = v;
    }
}

