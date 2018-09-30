package com.yetland.data.net;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
public class HttpClientSetting {
    private HttpLoggingInterceptor mHttpLoggingInterceptor;
    private Interceptor mHeaderInterceptor;
    private OkHttpClient mOkHttpClient;
    private String mBaseUrl;

    public HttpClientSetting() {
        initHttpLoggingInterceptor();
        initHeaderInterceptor();
        initHttpClient();
        mBaseUrl = "https://leancloud.cn:443/1.1/";
    }

    private void initHttpClient() {
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(mHttpLoggingInterceptor)
                .addNetworkInterceptor(mHeaderInterceptor)
                .readTimeout(8000, TimeUnit.MILLISECONDS)
                .writeTimeout(8000, TimeUnit.MILLISECONDS)
                .build();
    }

    private void initHeaderInterceptor() {

        mHeaderInterceptor = new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                return chain.proceed(request.newBuilder()
                        .addHeader("x-avoscloud-application-id", "hq5TP1JfC8IalSWXQCYNwVk4-gzGzoHsz")
                        .addHeader("x-avoscloud-application-key", "O51KjzzevmasxS3RRCbTDwq0")
                        .addHeader("Content-Type", "application/json")
                        .method(request.method(), request.body())
                        .build());
            }
        };
    }

    private void initHttpLoggingInterceptor() {
        mHttpLoggingInterceptor = new HttpLoggingInterceptor();

        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }
}
