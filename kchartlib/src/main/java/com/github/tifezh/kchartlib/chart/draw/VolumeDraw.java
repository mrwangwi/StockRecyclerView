package com.github.tifezh.kchartlib.chart.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.github.tifezh.kchartlib.R;
import com.github.tifezh.kchartlib.chart.BaseKChartView;
import com.github.tifezh.kchartlib.chart.entity.IVolume;
import com.github.tifezh.kchartlib.chart.base.IChartDraw;
import com.github.tifezh.kchartlib.chart.base.IValueFormatter;
import com.github.tifezh.kchartlib.chart.formatter.BigValueFormatter;
import com.github.tifezh.kchartlib.utils.ViewUtil;

/**
 * Created by hjm on 2017/11/14 17:49.
 */

public class VolumeDraw implements IChartDraw<IVolume> {

    private Paint mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mGreenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ma5Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ma10Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int pillarWidth = 0;
    private int lineWidth;

    public VolumeDraw(BaseKChartView view) {
        Context context = view.getContext();
        mRedPaint.setColor(ContextCompat.getColor(context, R.color.chart_red));
        mRedPaint.setStyle(Paint.Style.STROKE);
        lineWidth = ViewUtil.Dp2Px(context, 1);
        mRedPaint.setStrokeWidth(lineWidth);
        mGreenPaint.setColor(ContextCompat.getColor(context, R.color.chart_green));
        pillarWidth = ViewUtil.Dp2Px(context, 4);
    }

    @Override
    public void drawTranslated(
            @Nullable IVolume lastPoint, @NonNull IVolume curPoint, float lastX, float curX,
            @NonNull Canvas canvas, @NonNull BaseKChartView view, int position) {

        drawHistogram(canvas, curPoint, lastPoint, curX, view, position, false);
        view.drawChildLine(canvas, ma5Paint, lastX, lastPoint.getMA5Volume(), curX, curPoint.getMA5Volume());
        view.drawChildLine(canvas, ma10Paint, lastX, lastPoint.getMA10Volume(), curX, curPoint.getMA10Volume());
    }

    private void drawHistogram(
            Canvas canvas, IVolume curPoint, IVolume lastPoint, float curX,
            BaseKChartView view, int position, boolean isTwo) {

        float r = pillarWidth / 2;
        float top = isTwo ? view.getChildY1(curPoint.getVolume()) : view.getChildY(curPoint.getVolume());
        int bottom = isTwo ? view.getmChildRect1().bottom : view.getChildRect().bottom;
        if (curPoint.getClosePrice() >= curPoint.getOpenPrice()) {//涨
            canvas.drawRect(curX - r, top, curX + r, bottom, mRedPaint);
        } else {
            canvas.drawRect(curX - r, top, curX + r, bottom, mGreenPaint);
        }

    }

    @Override
    public void drawText(
            @NonNull Canvas canvas, @NonNull BaseKChartView view, int position, float x, float y) {
        String text = "成交量";
        canvas.drawText(text, x, y, view.getmTextBlackPaint());
        text = "成交成交";
        x += view.getTextPaint().measureText(text);
        IVolume point = (IVolume) view.getItem(position);
        text = "" + getValueFormatter().format(point.getVolume()) + " ";
        canvas.drawText(text, x, y, view.getTextPaint());
        x += view.getTextPaint().measureText(text);
        text = "MA5:" + getValueFormatter().format(point.getMA5Volume()) + " ";
        canvas.drawText(text, x, y, ma5Paint);
        x += ma5Paint.measureText(text);
        text = "10:" + getValueFormatter().format(point.getMA10Volume()) + " ";
        canvas.drawText(text, x, y, ma10Paint);
        text = getValueFormatter().format(view.getmChildMaxValue());
        canvas.drawText(text, 0, y + view.getTextSize() * 1.2f, view.getTextPaint());
    }

    @Override
    public float getMaxValue(IVolume point) {
        return Math.max(point.getVolume(), Math.max(point.getMA5Volume(), point.getMA10Volume()));
    }

    @Override
    public float getMinValue(IVolume point) {
        return Math.min(point.getVolume(), Math.min(point.getMA5Volume(), point.getMA10Volume()));
    }

    @Override
    public IValueFormatter getValueFormatter() {
        return new BigValueFormatter();
    }

    @Override
    public void drawTranslated1(@Nullable IVolume lastPoint, @NonNull IVolume curPoint, float lastX, float curX, @NonNull Canvas canvas, @NonNull BaseKChartView view, int position) {
        drawHistogram(canvas, curPoint, lastPoint, curX, view, position, true);
        view.drawChildLine1(canvas, ma5Paint, lastX, lastPoint.getMA5Volume(), curX, curPoint.getMA5Volume());
        view.drawChildLine1(canvas, ma10Paint, lastX, lastPoint.getMA10Volume(), curX, curPoint.getMA10Volume());
    }

    @Override
    public void drawText1(@NonNull Canvas canvas, @NonNull BaseKChartView view, int position, float x, float y) {
        String text = "成交量";
        canvas.drawText(text, x, y, view.getmTextBlackPaint());
        text = "成交成交";
        x += view.getTextPaint().measureText(text);
        IVolume point = (IVolume) view.getItem(position);
        text = "" + getValueFormatter().format(point.getVolume()) + " ";
        canvas.drawText(text, x, y, view.getTextPaint());
        x += view.getTextPaint().measureText(text);
        text = "MA5:" + getValueFormatter().format(point.getMA5Volume()) + " ";
        canvas.drawText(text, x, y, ma5Paint);
        x += ma5Paint.measureText(text);
        text = "10:" + getValueFormatter().format(point.getMA10Volume()) + " ";
        canvas.drawText(text, x, y, ma10Paint);
        text = getValueFormatter().format(view.getmChildMaxValue1());
        canvas.drawText(text, 0, y + view.getTextSize() * 1.2f, view.getTextPaint());
    }

    /**
     * 设置 MA5 线的颜色
     *
     * @param color
     */
    public void setMa5Color(int color) {
        this.ma5Paint.setColor(color);
    }

    /**
     * 设置 MA10 线的颜色
     *
     * @param color
     */
    public void setMa10Color(int color) {
        this.ma10Paint.setColor(color);
    }

    public void setLineWidth(float width) {
        this.ma5Paint.setStrokeWidth(width);
        this.ma10Paint.setStrokeWidth(width);
    }

    /**
     * 设置文字大小
     *
     * @param textSize
     */
    public void setTextSize(float textSize) {
        this.ma5Paint.setTextSize(textSize);
        this.ma10Paint.setTextSize(textSize);
    }

}
