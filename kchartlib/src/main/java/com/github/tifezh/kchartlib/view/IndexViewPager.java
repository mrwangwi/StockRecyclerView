package com.github.tifezh.kchartlib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class IndexViewPager extends ViewPager {

    private boolean isCanScroll = true;

    public IndexViewPager(@NonNull Context context) {
        super(context);
    }

    public IndexViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置其是否能滑动换页
     *
     * @param isCanScroll false 不能换页， true 可以滑动换页
     */
    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        try {
            return isCanScroll && super.onInterceptTouchEvent(ev);
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onTouchEvent(ev);

    }
}
