package com.yetland.bbclient.launcher;

import com.yetland.base.base.BaseView;
import com.yetland.data.entity.Launcher;

/**
 * @author YETLAND
 * @date 2018/10/8 16:58
 */
public interface LauncherView extends BaseView {
    void getLauncher();

    void show(String imgUrl);

    void success(Launcher launcher);

    void failed(String msg);
}
