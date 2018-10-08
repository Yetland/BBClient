package com.yetland.data.api;

import com.yetland.data.entity.Launcher;
import com.yetland.data.entity.resp.LauncherResp;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
public interface AppService {
    @GET("classes/Launcher")
    Call<LauncherResp<Launcher>> getLauncher();
}
