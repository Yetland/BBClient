package com.yetland.bbclient.launcher;

import com.yetland.base.base.BasePresenter;
import com.yetland.data.entity.Launcher;
import com.yetland.data.entity.resp.LauncherResp;

import rx.functions.Action1;

/**
 * @author YETLAND
 * @date 2018/10/8 16:58
 */
public class LauncherPresenter extends BasePresenter<LauncherModel, LauncherView> {


    public LauncherPresenter(LauncherModel launcherModel, LauncherView launcherView) {
        super(launcherModel, launcherView);
    }

    void getLauncher() {
        mM.getLauncher().subscribe(new Action1<LauncherResp<Launcher>>() {
            @Override
            public void call(LauncherResp<Launcher> launcherLauncherResp) {
                if (launcherLauncherResp.getResults() != null
                        && launcherLauncherResp.getResults().size() > 0) {
                    mV.success(launcherLauncherResp.getResults().get(0));
                } else {
                    mV.failed("null");
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mV.failed(throwable.getMessage());
            }
        });
    }
}
