package com.yetland.bbclient.launcher;

import com.yetland.base.base.BaseModel;
import com.yetland.data.api.AppApiImpl;
import com.yetland.data.entity.Launcher;
import com.yetland.data.entity.resp.LauncherResp;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author YETLAND
 * @date 2018/10/8 16:58
 */
public class LauncherModel extends BaseModel {

    private AppApiImpl mAppService;

    @Inject
    public LauncherModel(AppApiImpl appService) {
        mAppService = appService;
    }

    Observable<LauncherResp<Launcher>> getLauncher() {
        return mAppService.getLauncher();
    }
}
