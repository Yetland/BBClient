package com.yetland.bbclient.login;

import com.yetland.base.base.BaseView;
import com.yetland.data.entity.User;

/**
 * @author YETLAND
 * @date 2018/11/23 15:30
 */
public interface LoginView extends BaseView<User> {
    void login(String username, String password);
}
