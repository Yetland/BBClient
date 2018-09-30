package com.yetland.data.api;

import com.yetland.data.entity.User;

import rx.Observable;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
public interface UserApi {
    Observable<User> login(String username, String password);
}
