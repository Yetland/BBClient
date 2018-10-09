package com.yetland.data.api;

import com.yetland.data.entity.Launcher;
import com.yetland.data.entity.resp.LauncherResp;
import com.yetland.data.rx.RxScheduler;

import javax.inject.Inject;

import rx.Observable;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 *
 * @author YETLAND
 */
public class AppApiImpl extends BaseApi implements AppApi {

    private AppService mAppService;

    @Inject
    public AppApiImpl(AppService appService) {
        mAppService = appService;
    }

    @Override
    public Observable<LauncherResp<Launcher>> getLauncher() {
        return Observable.create((Observable.OnSubscribe<LauncherResp<Launcher>>) subscriber -> {
            execute(mAppService.getLauncher(), subscriber);
        }).compose(RxScheduler.main());
    }
}
