package com.yetland.data.api;


import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.Gson;
import com.yetland.data.DataCenter;
import com.yetland.data.database.BaseCacheEntity;
import com.yetland.data.entity.ErrorEntity;
import com.yetland.data.error.NoNetworkError;

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
        execute(call, subscriber, false, null, null);
    }

    @SuppressWarnings("unchecked")
    <T> void execute(Call<T> call, Subscriber<? super T> subscriber, boolean cache, String cacheId, Class clazz) {
        // 如果要去取缓存数据，那么优先取缓存数据显示
        if (cache) {
            BaseCacheEntity cacheEntity = DataCenter.getCache(cacheId);
            if (cacheEntity != null) {
                String value = cacheEntity.getValue();
                if (!TextUtils.isEmpty(value)) {
                    T t = (T) new Gson().toJson(value, clazz);
                    subscriber.onNext(t);
                    // 如果有缓存数据，而且还没网络，那么直接就结束了
                    if (!NetworkUtils.isConnected()) {
                        subscriber.onCompleted();
                        return;
                    }
                }
            }
        } else {
            // 如果不缓存，而且也没网，那么就直接失败了
            if (!NetworkUtils.isConnected()) {
                subscriber.onError(new NoNetworkError());
                subscriber.onCompleted();
                return;
            }
        }
        // 正常去获取数据
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                if (cache && response.body() != null) {
                    String value = new Gson().toJson(response.body());
                    DataCenter.cache(cacheId, value);
                }
                subscriber.onNext(response.body());
            } else {
                ResponseBody responseBody = response.errorBody();
                if (responseBody != null) {
                    String errorResp = responseBody.string();
                    ErrorEntity entity = new Gson().fromJson(errorResp, ErrorEntity.class);
                    subscriber.onError(new Throwable(entity.getError()));
                } else {
                    subscriber.onError(new NoNetworkError());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            subscriber.onError(new Throwable(e.getMessage()));
        }
        subscriber.onCompleted();
    }
}
