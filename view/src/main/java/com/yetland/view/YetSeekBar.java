package com.yetland.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author YETLAND
 * @date 2018/11/26 17:11
 */
public class YetSeekBar extends View {

    private Context mContext;
    private Paint mPaint;
    private int mWidth, mHeight;

    public YetSeekBar(@NonNull Context context) {
        super(context);
        mContext = context;
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawCircle();
    }
}
