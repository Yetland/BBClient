package com.yetland.bbclient.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.yetland.base.base.BaseMvpActivity;
import com.yetland.base.base.BasePresenter;
import com.yetland.bbclient.R;

/**
 * @author YETLAND
 */
public class Main2Activity extends BaseMvpActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                default:
                    break;
            }
            return false;
        }
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
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
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
