package com.yetland.bbclient.login;

import android.widget.Button;
import android.widget.EditText;

import com.yetland.base.base.BaseMvpActivity;
import com.yetland.base.base.BasePresenter;
import com.yetland.base.data.SPUtil;
import com.yetland.bbclient.R;
import com.yetland.bbclient.dagger.component.DaggerActivityComponent;
import com.yetland.bbclient.dagger.module.ActivityModule;
import com.yetland.data.entity.User;

import javax.inject.Inject;

/**
 * @author YETLAND
 */
public class LoginActivity extends BaseMvpActivity implements LoginView {

    @Inject
    LoginPresenter mLoginPresenter;
    private EditText editText;
    private EditText editText2;
    private Button button;

    @Override
    protected String getTitleText() {
        return "登录";
    }

    @Override
    protected boolean isShowTitleBar() {
        return true;
    }

    @Override
    protected void init() {
        button.setOnClickListener(v -> {
            login(editText.getText().toString().trim(), editText2.getText().toString().trim());
        });
    }

    @Override
    protected void findViews() {
        editText2 = findViewById(R.id.editText2);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void inject() {
        DaggerActivityComponent.builder()
                .dataComponent(mDataComponent)
                .appComponent(mAppComponent)
                .activityModule(new ActivityModule(this))
                .build()
                .injectLogin(this);
    }

    @Override
    public void login(String username, String password) {
        mLoginPresenter.login(username, password);
    }

    @Override
    public void loading(String msg) {

    }

    @Override
    public void success(User user) {
        SPUtil.save(user.getClass().toString(), user);
    }

    @Override
    public void failed(String msg) {

    }


    @Override
    protected BasePresenter getPresenter() {
        return mLoginPresenter;
    }
}
