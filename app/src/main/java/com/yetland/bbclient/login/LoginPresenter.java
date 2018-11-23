package com.yetland.bbclient.login;

import com.yetland.base.base.BaseActivity;
import com.yetland.base.base.BasePresenter;

import javax.inject.Inject;

/**
 * @author YETLAND
 * @date 2018/11/23 15:30
 */
public class LoginPresenter extends BasePresenter<LoginModel, LoginView> {

    @Inject
    public LoginPresenter(BaseActivity baseActivity, LoginModel loginModel) {
        super(baseActivity, loginModel);
    }

    public void login(String username, String password) {
        addSubscription(mM.login(username, password).subscribe(user -> mV.success(user), throwable -> mV.failed(throwable.getMessage())));
    }
}
