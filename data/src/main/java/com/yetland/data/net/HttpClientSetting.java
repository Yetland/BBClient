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
    private String mBaseUrl = "https://leancloud.cn:443/1.1/";
    private String mId = "hq5TP1JfC8IalSWXQCYNwVk4-gzGzoHsz";
    private String mKey = "O51KjzzevmasxS3RRCbTDwq0";

    private HttpClientSetting mHttpClientSetting;

    public static class Builder {
        String mBaseUrl;
        String mId;
        String mKey;

        public Builder baseUrl(String baseUrl) {
            this.mBaseUrl = baseUrl;
            return this;
        }

        public Builder id(String id) {
            this.mId = id;
            return this;
        }

        public Builder key(String key) {
            this.mKey = key;
            return this;
        }

        public HttpClientSetting build() {
            return new HttpClientSetting(mBaseUrl, mId, mKey);
        }
    }

    HttpClientSetting(String baseUrl, String id, String key) {
        super();
        mBaseUrl = baseUrl;
        mId = id;
        mKey = key;
    }

    public HttpClientSetting() {
        initHttpLoggingInterceptor();
        initHeaderInterceptor();
        initHttpClient();
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
                        .addHeader("x-avoscloud-application-id", mId)
                        .addHeader("x-avoscloud-application-key", mKey)
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
