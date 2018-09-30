package com.yetland.bbclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yetland.base.base.BaseActivity;
import com.yetland.bbclient.dagger.component.ActivityComponent;
import com.yetland.bbclient.dagger.component.DaggerActivityComponent;
import com.yetland.bbclient.dagger.module.ActivityModule;

import javax.inject.Inject;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
public class MainActivity extends BaseActivity {

    @Inject
    MainModel mMainModel;

    EditText mEditText;
    EditText mEditText2;
    Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editText);
        mEditText2 = findViewById(R.id.editText2);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainModel.login(mEditText.getText().toString(),
                        mEditText2.getText().toString());
            }
        });
    }

    @Override
    protected void inject() {
        ActivityComponent activityComponent =
                DaggerActivityComponent.builder()
                        .appComponent(mAppComponent)
                        .dataComponent(mDataComponent)
                        .activityModule(new ActivityModule(this))
                        .build();
        activityComponent.inject(this);
    }
}
