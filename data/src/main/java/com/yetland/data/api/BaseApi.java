package com.yetland.data.api;


import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.Gson;
import com.yetland.data.entity.ErrorEntity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
class BaseApi {
    private static final String NETWORK_ERROR = "连接服务器失败";
    private static final String NO_NETWORK_ERROR = "连接服务器失败，请检查网络连接";

    <T> void execute(Call<T> call, Subscriber<? super T> subscriber) {
        execute(call, subscriber, false);
    }

    <T> void execute(Call<T> call, Subscriber<? super T> subscriber, boolean cache) {
        if (!NetworkUtils.isConnected()) {
            subscriber.onError(new Throwable(NO_NETWORK_ERROR));
        } else {
            try {
                Response<T> response = call.execute();
                if (response.isSuccessful()) {
                    subscriber.onNext(response.body());
                } else {
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        String errorResp = responseBody.string();
                        ErrorEntity entity = new Gson().fromJson(errorResp, ErrorEntity.class);
                        subscriber.onError(new Throwable(entity.getError()));
                    } else {
                        subscriber.onError(new Throwable(NETWORK_ERROR));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                subscriber.onError(new Throwable(e.getMessage()));
            }
        }
        subscriber.onCompleted();
    }

}
