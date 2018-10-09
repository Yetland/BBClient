package com.yetland.base.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * @author YETLAND
 * @date 2018/10/9 14:05
 */
public class BBButton extends AppCompatButton {

    private int mMinClickDelayTime = 1000;
    private long mLastClickTime = 0;

    public BBButton(Context context) {
        super(context);
    }

    public BBButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BBButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMinClickDelayTime(int minClickDelayTime) {
        mMinClickDelayTime = minClickDelayTime;
    }

    public boolean judge() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastClickTime > mMinClickDelayTime) {
            mLastClickTime = currentTime;
            return true;
        } else {
            return false;
        }
    }
}
