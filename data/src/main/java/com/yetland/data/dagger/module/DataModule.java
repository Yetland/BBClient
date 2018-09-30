package com.yetland.data.dagger.module;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.yetland.data.api.AppService;
import com.yetland.data.api.UserApiImpl;
import com.yetland.data.api.UserService;
import com.yetland.data.net.HttpClientSetting;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
@Module
public class DataModule {


    private HttpClientSetting mHttpClientSetting;
    private Retrofit mUserRetrofit;
    private Retrofit mAppRetrofit;
    private UserService mUserService;
    private AppService mAppService;

    public DataModule() {
        mHttpClientSetting = new HttpClientSetting();
        mUserRetrofit = provideUserRetrofit(mHttpClientSetting.getOkHttpClient(),
                mHttpClientSetting.getBaseUrl());

        mAppRetrofit = provideAppRetrofit(mHttpClientSetting.getOkHttpClient(),
                mHttpClientSetting.getBaseUrl());

        mUserService = provideUserService(mUserRetrofit);
        mAppService = provideAppService(mAppRetrofit);
    }

    AppService provideAppService(@NonNull Retrofit retrofit) {
        return retrofit.create(AppService.class);
    }


    UserService provideUserService(@NonNull Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

    Retrofit provideUserRetrofit(OkHttpClient okHttpClient, String baseUrl) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    Retrofit provideAppRetrofit(OkHttpClient okHttpClient, String baseUrl) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    @Provides
    UserApiImpl provideUserApi() {
        return new UserApiImpl(mUserService);
    }
}
