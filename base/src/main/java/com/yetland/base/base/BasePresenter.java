package com.yetland.base.base;

import rx.Subscription;

/**
 * @author YETLAND
 * @date 2018/10/8 16:55
 */
public abstract class BasePresenter<M, V> {
    protected BaseActivity mBaseActivity;
    protected M mM;
    protected V mV;

    public BasePresenter(BaseActivity baseActivity, M m) {
        mBaseActivity = baseActivity;
        mM = m;
    }

    public M getM() {
        return mM;
    }

    public void setM(M m) {
        mM = m;
    }

    public V getV() {
        return mV;
    }

    public void setV(V v) {
        mV = v;
    }

    public abstract void onDestroy();

    public void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}

