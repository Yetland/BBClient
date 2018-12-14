package com.yetland.bbclient.main;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;

import com.yetland.base.base.BaseMvpActivity;
import com.yetland.base.base.BasePresenter;
import com.yetland.bbclient.MainActivity;
import com.yetland.bbclient.MainDialog;
import com.yetland.bbclient.R;

/**
 * @author YETLAND
 */
public class Main2Activity extends BaseMvpActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_dashboard:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.navigation_notifications:
                MainDialog dialog = new MainDialog(this);
                dialog.show();
                return true;
            default:
                break;
        }
        return false;
    };

    @Override
    protected String getTitleText() {
        return null;
    }

    @Override
    protected boolean isShowTitleBar() {
        return false;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void findViews() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void inject() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

}
