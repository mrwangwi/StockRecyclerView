package com.example.application.view.stockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.application.utils.DisplayUtil;


public class MyShadowView extends LinearLayout {
    private float borderWidth;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MyShadowView(Context context) {
        super(context);
    }

    public MyShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(0x00000000);
        borderWidth = DisplayUtil.dpToPx(context, 3);
    }

    public MyShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        LinearGradient linearGradient = new LinearGradient(getWidth() - borderWidth, 0, getWidth(), 0, 0x00000000, 0x80E5E5E5, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);

        Path path = new Path();

        path.moveTo(getWidth(), getHeight());
        path.lineTo(getWidth() - borderWidth, getHeight());
        path.lineTo(getWidth() - borderWidth, 0);
        path.lineTo(getWidth(), 0);
        path.close();
        canvas.drawPath(path, paint);
    }

    public void start() {
        paint.setColor(0xFF000000);
        invalidate();
    }

    public void finish() {
        paint.setColor(0x00000000);
        invalidate();
    }
}
