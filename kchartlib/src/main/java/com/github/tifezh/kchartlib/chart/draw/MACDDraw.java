package com.github.tifezh.kchartlib.chart.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.github.tifezh.kchartlib.R;
import com.github.tifezh.kchartlib.chart.BaseKChartView;
import com.github.tifezh.kchartlib.chart.entity.IMACD;
import com.github.tifezh.kchartlib.chart.base.IChartDraw;
import com.github.tifezh.kchartlib.chart.base.IValueFormatter;
import com.github.tifezh.kchartlib.chart.formatter.ValueFormatter;
import com.github.tifezh.kchartlib.utils.ViewUtil;

/**
 * macd实现类
 * Created by tifezh on 2016/6/19.
 */

public class MACDDraw implements IChartDraw<IMACD> {

    private Paint mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mGreenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mDIFPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mDEAPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mMACDPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int lineWidth;
    /**
     * macd 中柱子的宽度
     */
    private float mMACDWidth = 0;

    public MACDDraw(BaseKChartView view) {
        Context context = view.getContext();
        lineWidth = ViewUtil.Dp2Px(context, 1);
        mRedPaint.setColor(ContextCompat.getColor(context, R.color.chart_red));
        mGreenPaint.setColor(ContextCompat.getColor(context, R.color.chart_green));
    }

    @Override
    public void drawTranslated(@Nullable IMACD lastPoint, @NonNull IMACD curPoint, float lastX, float curX, @NonNull Canvas canvas, @NonNull BaseKChartView view, int position) {
        drawMACD(canvas, view, curX, curPoint.getMacd(), false);
        view.drawChildLine(canvas, mDIFPaint, lastX, lastPoint.getDea(), curX, curPoint.getDea());
        view.drawChildLine(canvas, mDEAPaint, lastX, lastPoint.getDif(), curX, curPoint.getDif());
    }

    @Override
    public void drawText(@NonNull Canvas canvas, @NonNull BaseKChartView view, int position, float x, float y) {
        String text = "MACD";
        canvas.drawText(text, x, y, view.getmTextBlackPaint());
        text = "成交成交";
        x += view.getTextPaint().measureText(text);

        text = "(12,26,9)";
        canvas.drawText(text, x, y, view.getmTextBlackPaint());
        x += view.getTextPaint().measureText(text);

        IMACD point = (IMACD) view.getItem(position);
        text = "DIF:" + view.formatValue(point.getDif()) + " ";
        canvas.drawText(text, x, y, mDEAPaint);
        x += mDIFPaint.measureText(text);
        text = "DEA:" + view.formatValue(point.getDea()) + " ";
        canvas.drawText(text, x, y, mDIFPaint);
        x += mDEAPaint.measureText(text);
        text = "MACD:" + view.formatValue(point.getMacd()) + " ";
        canvas.drawText(text, x, y, mMACDPaint);

        text = view.formatValue(view.getmChildMaxValue());
        canvas.drawText(text, 0, y + view.getTextSize() * 1.2f, view.getTextPaint());
        text = view.formatValue(view.getmChildMinValue());
        canvas.drawText(text, 0, view.getChildRect().bottom - view.getTextSize() * 0.1f, view.getTextPaint());
    }

    @Override
    public float getMaxValue(IMACD point) {
        return Math.max(point.getMacd(), Math.max(point.getDea(), point.getDif()));
    }

    @Override
    public float getMinValue(IMACD point) {
        return Math.min(point.getMacd(), Math.min(point.getDea(), point.getDif()));
    }

    @Override
    public IValueFormatter getValueFormatter() {
        return new ValueFormatter();
    }

    @Override
    public void drawTranslated1(@Nullable IMACD lastPoint, @NonNull IMACD curPoint, float lastX, float curX, @NonNull Canvas canvas, @NonNull BaseKChartView view, int position) {
        drawMACD(canvas, view, curX, curPoint.getMacd(), true);
        view.drawChildLine1(canvas, mDIFPaint, lastX, lastPoint.getDea(), curX, curPoint.getDea());
        view.drawChildLine1(canvas, mDEAPaint, lastX, lastPoint.getDif(), curX, curPoint.getDif());
    }

    @Override
    public void drawText1(@NonNull Canvas canvas, @NonNull BaseKChartView view, int position, float x, float y) {
        String text = "MACD";
        canvas.drawText(text, x, y, view.getmTextBlackPaint());
        text = "成交成交";
        x += view.getTextPaint().measureText(text);

        text = "(12,26,9)";
        canvas.drawText(text, x, y, view.getmTextBlackPaint());
        x += view.getTextPaint().measureText(text);

        IMACD point = (IMACD) view.getItem(position);
        text = "DIF:" + view.formatValue(point.getDif()) + " ";
        canvas.drawText(text, x, y, mDEAPaint);
        x += mDIFPaint.measureText(text);
        text = "DEA:" + view.formatValue(point.getDea()) + " ";
        canvas.drawText(text, x, y, mDIFPaint);
        x += mDEAPaint.measureText(text);
        text = "MACD:" + view.formatValue(point.getMacd()) + " ";
        canvas.drawText(text, x, y, mMACDPaint);

        text = view.formatValue(view.getmChildMaxValue1());
        canvas.drawText(text, 0, y + view.getTextSize() * 1.2f, view.getTextPaint());
        text = view.formatValue(view.getmChildMinValue1());
        canvas.drawText(text, 0, view.getmChildRect1().bottom - view.getTextSize() * 0.1f, view.getTextPaint());
    }

    /**
     * 画macd
     *
     * @param canvas
     * @param x
     * @param macd
     */
    private void drawMACD(Canvas canvas, BaseKChartView view, float x, float macd, boolean isTwo) {
        float macdy = isTwo ? view.getChildY1(macd) : view.getChildY(macd);
        float r = lineWidth / 2.0f;
        float zeroy = isTwo ? view.getChildY1(0) : view.getChildY(0);
        if (macd > 0) {
            //               left   top   right  bottom
            canvas.drawRect(x - r, macdy, x + r, zeroy, mRedPaint);
        } else {
            canvas.drawRect(x - r, zeroy, x + r, macdy, mGreenPaint);
        }
    }

    /**
     * 设置DIF颜色
     */
    public void setDIFColor(int color) {
        this.mDIFPaint.setColor(color);
    }

    /**
     * 设置DEA颜色
     */
    public void setDEAColor(int color) {
        this.mDEAPaint.setColor(color);
    }

    /**
     * 设置MACD颜色
     */
    public void setMACDColor(int color) {
        this.mMACDPaint.setColor(color);
    }

    /**
     * 设置MACD的宽度
     *
     * @param MACDWidth
     */
    public void setMACDWidth(float MACDWidth) {
        mMACDWidth = MACDWidth;
    }

    /**
     * 设置曲线宽度
     */
    public void setLineWidth(float width) {
        mDEAPaint.setStrokeWidth(width);
        mDIFPaint.setStrokeWidth(width);
        mMACDPaint.setStrokeWidth(width);
    }

    /**
     * 设置文字大小
     */
    public void setTextSize(float textSize) {
        mDEAPaint.setTextSize(textSize);
        mDIFPaint.setTextSize(textSize);
        mMACDPaint.setTextSize(textSize);
    }
}
