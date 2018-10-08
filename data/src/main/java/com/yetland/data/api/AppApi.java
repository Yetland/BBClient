package com.yetland.data.api;

import com.yetland.data.entity.Launcher;
import com.yetland.data.entity.resp.LauncherResp;

import rx.Observable;

/**
 * @author YETLAND
 * @date 2018/10/8 17:09
 */
public interface AppApi {
    Observable<LauncherResp<Launcher>> getLauncher();
}
