package com.yetland.data.dagger.module;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.yetland.data.api.AppApiImpl;
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


    private UserService mUserService;
    private AppService mAppService;

    public DataModule() {
        HttpClientSetting httpClientSetting = new HttpClientSetting();
        Retrofit userRetrofit = provideUserRetrofit(httpClientSetting.getOkHttpClient(),
                httpClientSetting.getBaseUrl());

        Retrofit appRetrofit = provideAppRetrofit(httpClientSetting.getOkHttpClient(),
                httpClientSetting.getBaseUrl());

        mUserService = provideUserService(userRetrofit);
        mAppService = provideAppService(appRetrofit);
    }

    private AppService provideAppService(@NonNull Retrofit retrofit) {
        return retrofit.create(AppService.class);
    }


    private UserService provideUserService(@NonNull Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

    private Retrofit provideUserRetrofit(OkHttpClient okHttpClient, String baseUrl) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    private Retrofit provideAppRetrofit(OkHttpClient okHttpClient, String baseUrl) {
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

    @Provides
    AppApiImpl provideAppServiceImpl(){
        return new AppApiImpl(mAppService);
    }
}
