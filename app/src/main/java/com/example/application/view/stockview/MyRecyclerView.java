package com.example.application.view.stockview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerView extends RecyclerView {


    private RecyclerView recyclerView;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (recyclerView != null) {
            recyclerView.onTouchEvent(e);
            return true;
        }
        return super.onTouchEvent(e);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (recyclerView != null) {
            recyclerView.onInterceptTouchEvent(e);
            return true;
        }
        return super.onInterceptTouchEvent(e);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (recyclerView != null) {
            recyclerView.dispatchTouchEvent(ev);
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
