package com.yetland.data.api;

import com.yetland.data.entity.User;

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
public class UserApiImpl extends BaseApi implements UserApi {

    private UserService mUserService;

    @Inject
    public UserApiImpl(UserService userService) {
        mUserService = userService;
    }

    @Override
    public Observable<User> login(final String username, final String password) {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try {
                    Response<User> response = mUserService.login(username, password).execute();
                    if (response.isSuccessful()) {
                        subscriber.onNext(response.body());
                    } else {
                        subscriber.onError(new Throwable(NETWORK_ERROR));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(new Throwable(e.getMessage()));
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
