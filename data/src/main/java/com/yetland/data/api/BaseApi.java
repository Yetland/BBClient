package com.yetland.data.api;


import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
class BaseApi {
    static final String NETWORK_ERROR = "连接服务器失败";

    <T> void execute(Call<T> call, Subscriber<? super T> subscriber) {
        if (!NetworkUtils.isConnected()) {
            subscriber.onError(new Throwable(NETWORK_ERROR));
        } else {
            try {
                Response<T> response = call.execute();
                if (response.isSuccessful()) {
                    subscriber.onNext(response.body());
                } else {
                    subscriber.onError(new Throwable(NETWORK_ERROR));
                }
            } catch (IOException e) {
                e.printStackTrace();
                subscriber.onError(new Throwable(e.getMessage()));
            }
        }
        subscriber.onCompleted();
    }
}
