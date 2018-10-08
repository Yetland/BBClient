package com.yetland.data.api;

import com.yetland.data.entity.Launcher;
import com.yetland.data.entity.resp.LauncherResp;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
public class AppApiImpl extends BaseApi implements AppApi {

    private AppService mAppService;

    @Inject
    public AppApiImpl(AppService appService) {
        mAppService = appService;
    }

    @Override
    public Observable<LauncherResp<Launcher>> getLauncher() {
        return Observable.create(new Observable.OnSubscribe<LauncherResp<Launcher>>() {
            @Override
            public void call(Subscriber<? super LauncherResp<Launcher>> subscriber) {
                try {
                    Response<LauncherResp<Launcher>> respResponse = mAppService.getLauncher().execute();
                    if (respResponse.isSuccessful()) {
                        subscriber.onNext(respResponse.body());
                    } else {
                        subscriber.onError(new Throwable(NETWORK_ERROR));
                    }
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(new Throwable(e.getMessage()));
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
