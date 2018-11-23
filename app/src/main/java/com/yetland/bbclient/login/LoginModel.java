package com.yetland.bbclient.login;

import com.yetland.base.base.BaseModel;
import com.yetland.data.api.UserApiImpl;
import com.yetland.data.entity.User;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author YETLAND
 * @date 2018/11/23 15:30
 */
public class LoginModel extends BaseModel {
    private UserApiImpl mUserApi;

    @Inject
    public LoginModel(UserApiImpl userApi) {
        mUserApi = userApi;
    }

    Observable<User> login(String userName, String password) {
        return mUserApi.login(userName, password);
    }
}
