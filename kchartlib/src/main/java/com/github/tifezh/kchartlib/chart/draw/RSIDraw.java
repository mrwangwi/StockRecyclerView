package com.github.tifezh.kchartlib.chart.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.tifezh.kchartlib.chart.BaseKChartView;
import com.github.tifezh.kchartlib.chart.entity.IRSI;
import com.github.tifezh.kchartlib.chart.base.IChartDraw;
import com.github.tifezh.kchartlib.chart.base.IValueFormatter;
import com.github.tifezh.kchartlib.chart.formatter.ValueFormatter;

/**
 * RSI实现类
 * Created by tifezh on 2016/6/19.
 */

public class RSIDraw implements IChartDraw<IRSI> {

    private Paint mRSI1Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mRSI2Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mRSI3Paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RSIDraw(BaseKChartView view) {

    }

    @Override
    public void drawTranslated(@Nullable IRSI lastPoint, @NonNull IRSI curPoint, float lastX, float curX, @NonNull Canvas canvas, @NonNull BaseKChartView view, int position) {
        view.drawChildLine(canvas, mRSI1Paint, lastX, lastPoint.getRsi1(), curX, curPoint.getRsi1());
        view.drawChildLine(canvas, mRSI2Paint, lastX, lastPoint.getRsi2(), curX, curPoint.getRsi2());
        view.drawChildLine(canvas, mRSI3Paint, lastX, lastPoint.getRsi3(), curX, curPoint.getRsi3());
    }

    @Override
    public void drawText(@NonNull Canvas canvas, @NonNull BaseKChartView view, int position, float x, float y) {
        String text = "RSI";
        canvas.drawText(text, x, y, view.getmTextBlackPaint());
        text = "成交成交";
        x += view.getTextPaint().measureText(text);
        text = "(6,12,24)";
        canvas.drawText(text, x, y, view.getmTextBlackPaint());
        x += view.getTextPaint().measureText(text);

        IRSI point = (IRSI) view.getItem(position);
        text = "6:" + view.formatValue(point.getRsi1()) + " ";
        canvas.drawText(text, x, y, mRSI1Paint);
        x += mRSI1Paint.measureText(text);
        text = "12:" + view.formatValue(point.getRsi2()) + " ";
        canvas.drawText(text, x, y, mRSI2Paint);
        x += mRSI2Paint.measureText(text);
        text = "24:" + view.formatValue(point.getRsi3()) + " ";
        canvas.drawText(text, x, y, mRSI3Paint);

        text = view.formatValue(view.getmChildMaxValue());
        canvas.drawText(text, 0, y + view.getTextSize() * 1.2f, view.getTextPaint());
        text = view.formatValue(view.getmChildMinValue());
        canvas.drawText(text, 0, view.getChildRect().bottom - view.getTextSize() * 0.1f, view.getTextPaint());
    }

    @Override
    public float getMaxValue(IRSI point) {
        return Math.max(point.getRsi1(), Math.max(point.getRsi2(), point.getRsi3()));
    }

    @Override
    public float getMinValue(IRSI point) {
        return Math.min(point.getRsi1(), Math.min(point.getRsi2(), point.getRsi3()));
    }

    @Override
    public IValueFormatter getValueFormatter() {
        return new ValueFormatter();
    }

    @Override
    public void drawTranslated1(@Nullable IRSI lastPoint, @NonNull IRSI curPoint, float lastX, float curX, @NonNull Canvas canvas, @NonNull BaseKChartView view, int position) {
        view.drawChildLine1(canvas, mRSI1Paint, lastX, lastPoint.getRsi1(), curX, curPoint.getRsi1());
        view.drawChildLine1(canvas, mRSI2Paint, lastX, lastPoint.getRsi2(), curX, curPoint.getRsi2());
        view.drawChildLine1(canvas, mRSI3Paint, lastX, lastPoint.getRsi3(), curX, curPoint.getRsi3());
    }

    @Override
    public void drawText1(@NonNull Canvas canvas, @NonNull BaseKChartView view, int position, float x, float y) {
        String text = "RSI";
        canvas.drawText(text, x, y, view.getmTextBlackPaint());
        text = "成交成交";
        x += view.getTextPaint().measureText(text);
        text = "(6,12,24)";
        canvas.drawText(text, x, y, view.getmTextBlackPaint());
        x += view.getTextPaint().measureText(text);

        IRSI point = (IRSI) view.getItem(position);
        text = "6:" + view.formatValue(point.getRsi1()) + " ";
        canvas.drawText(text, x, y, mRSI1Paint);
        x += mRSI1Paint.measureText(text);
        text = "12:" + view.formatValue(point.getRsi2()) + " ";
        canvas.drawText(text, x, y, mRSI2Paint);
        x += mRSI2Paint.measureText(text);
        text = "24:" + view.formatValue(point.getRsi3()) + " ";
        canvas.drawText(text, x, y, mRSI3Paint);

        text = view.formatValue(view.getmChildMaxValue1());
        canvas.drawText(text, 0, y + view.getTextSize() * 1.2f, view.getTextPaint());
        text = view.formatValue(view.getmChildMinValue1());
        canvas.drawText(text, 0, view.getmChildRect1().bottom - view.getTextSize() * 0.1f, view.getTextPaint());
    }

    public void setRSI1Color(int color) {
        mRSI1Paint.setColor(color);
    }

    public void setRSI2Color(int color) {
        mRSI2Paint.setColor(color);
    }

    public void setRSI3Color(int color) {
        mRSI3Paint.setColor(color);
    }

    /**
     * 设置曲线宽度
     */
    public void setLineWidth(float width) {
        mRSI1Paint.setStrokeWidth(width);
        mRSI2Paint.setStrokeWidth(width);
        mRSI3Paint.setStrokeWidth(width);
    }

    /**
     * 设置文字大小
     */
    public void setTextSize(float textSize) {
        mRSI2Paint.setTextSize(textSize);
        mRSI3Paint.setTextSize(textSize);
        mRSI1Paint.setTextSize(textSize);
    }
}
