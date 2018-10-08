package com.yetland.bbclient.launcher;

import com.yetland.base.base.BaseActivity;
import com.yetland.base.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscription;

/**
 * @author YETLAND
 * @date 2018/10/8 16:58
 */
public class LauncherPresenter extends BasePresenter<LauncherModel, LauncherView> {

    private Subscription mSubscription;
    @Inject
    LauncherPresenter(BaseActivity baseActivity, LauncherModel launcherModel) {
        super(baseActivity, launcherModel);
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null && mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }

    void getLauncher() {
        mSubscription = mM.getLauncher().subscribe(launcherLauncherResp -> {
            if (launcherLauncherResp.getResults() != null
                    && launcherLauncherResp.getResults().size() > 0) {
                mV.success(launcherLauncherResp.getResults().get(0));
            } else {
                mV.failed("null");
            }
        }, throwable -> mV.failed(throwable.getMessage()));
    }
}
