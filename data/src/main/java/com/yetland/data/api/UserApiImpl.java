package com.yetland.data.api;

import com.yetland.data.entity.User;
import com.yetland.data.rx.RxScheduler;

import javax.inject.Inject;

import retrofit2.Call;
import rx.Observable;

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
        return Observable.create((Observable.OnSubscribe<User>) subscriber -> {
            execute(mUserService.login(username, password), subscriber);
        }).compose(RxScheduler.main());
    }
}
