package com.example.application.view.stockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.utils.DisplayUtil;

public class MyDecoration extends RecyclerView.ItemDecoration {

    private Context context;
    private float mDividerHeight;
    private Paint mPaint;
    private int margin;

    public MyDecoration(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(context.getResources().getColor(R.color.border_color));
        this.context = context;
    }

    public MyDecoration(int margin, Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        this.margin = margin;
        mPaint.setColor(context.getResources().getColor(R.color.border_color));
        this.context = context;
    }

    public MyDecoration(int margin, int color, Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        this.margin = margin;
        mPaint.setColor(color);
        this.context = context;
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != 0) {
            //这里直接硬编码为1px
            outRect.top = 1;
            mDividerHeight = 1;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);


        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);

            int index = parent.getChildAdapterPosition(view);
            //第一个ItemView不需要绘制
            if (index == 0) {
                continue;
            }
            float dividerTop = view.getTop() - mDividerHeight;
            float dividerLeft = parent.getPaddingLeft();
            float dividerBottom = view.getTop();
            float dividerRight = parent.getWidth() - parent.getPaddingRight();
            c.drawRect(dividerLeft + DisplayUtil.dpToPx(context, margin), dividerTop, dividerRight - DisplayUtil.dpToPx(context, margin), dividerBottom, mPaint);
        }

    }
}
