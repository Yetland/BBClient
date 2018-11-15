package com.yetland.base.base;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * @author YETLAND
 * @date 2018/10/8 16:55
 */
public abstract class BasePresenter<M, V> {
    protected List<Subscription> mSubscriptionList;
    protected BaseActivity mBaseActivity;
    protected M mM;
    protected V mV;

    public BasePresenter(BaseActivity baseActivity, M m) {
        mSubscriptionList = new ArrayList<>();
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

    public void onDestroy() {
        unsubscribe();
    }

    protected void addSubscription(Subscription subscription) {
        mSubscriptionList.add(subscription);
    }

    private void unsubscribe() {
        for (Subscription subscription : mSubscriptionList) {
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    protected void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            mSubscriptionList.remove(subscription);
            subscription.unsubscribe();
        }
    }
}

