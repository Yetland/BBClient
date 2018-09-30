package com.yetland.data.api;

import com.yetland.data.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
public interface UserService {
    @GET("login")
    Call<User> login(@Query("username") String username, @Query("password") String password);
}
