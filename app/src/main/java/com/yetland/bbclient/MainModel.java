package com.yetland.bbclient;

import android.util.Log;
import android.widget.Toast;

import com.yetland.base.base.BaseActivity;
import com.yetland.data.api.UserApiImpl;
import com.yetland.data.entity.User;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
public class MainModel {

    @Inject
    UserApiImpl mUserApi;

    private BaseActivity mBaseActivity;

    @Inject
    MainModel(BaseActivity baseActivity) {
        mBaseActivity = baseActivity;
    }

    void login(String userName, String password) {
        mUserApi.login(userName, password).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                Toast.makeText(mBaseActivity, "SUCCESS", Toast.LENGTH_SHORT).show();
                Log.e("MainModel", "call: call" + user.toString());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Toast.makeText(mBaseActivity, "ERROR " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainModel", "call: error");

            }
        });
    }
}
