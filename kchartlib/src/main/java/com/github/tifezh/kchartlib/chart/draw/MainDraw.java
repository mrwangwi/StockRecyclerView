package com.github.tifezh.kchartlib.chart.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.github.tifezh.kchartlib.R;
import com.github.tifezh.kchartlib.chart.BaseKChartView;
import com.github.tifezh.kchartlib.chart.KChartView;
import com.github.tifezh.kchartlib.chart.entity.ICandle;
import com.github.tifezh.kchartlib.chart.entity.IKLine;
import com.github.tifezh.kchartlib.chart.base.IChartDraw;
import com.github.tifezh.kchartlib.chart.base.IValueFormatter;
import com.github.tifezh.kchartlib.chart.formatter.ValueFormatter;
import com.github.tifezh.kchartlib.utils.TextUtil;
import com.github.tifezh.kchartlib.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 主图的实现类
 * Created by tifezh on 2016/6/14.
 */

public class MainDraw implements IChartDraw<ICandle> {

    private float mCandleWidth = 0;
    private float mCandleLineWidth = 0;
    private Paint mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mGreenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ma5Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ma10Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ma20Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ma30Paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mSelectorTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mSelectorBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Context mContext;

    private boolean mCandleSolid = true;

    public MainDraw(BaseKChartView view) {
        Context context = view.getContext();
        mContext = context;
        mRedPaint.setColor(ContextCompat.getColor(context, R.color.chart_red));
        mGreenPaint.setColor(ContextCompat.getColor(context, R.color.chart_green));
        mTextPaint.setColor(0xff333333);
    }

    @Override
    public void drawTranslated(@Nullable ICandle lastPoint, @NonNull ICandle curPoint, float lastX, float curX, @NonNull Canvas canvas, @NonNull BaseKChartView view, int position) {
        drawCandle(curPoint, view, canvas, curX, curPoint.getHighPrice(), curPoint.getLowPrice(), curPoint.getOpenPrice(), curPoint.getClosePrice(), position);
        //画ma5
        if (lastPoint.getMA5Price() != 0) {
            view.drawMainLine(canvas, ma5Paint, lastX, lastPoint.getMA5Price(), curX, curPoint.getMA5Price());
        }
        //画ma10
        if (lastPoint.getMA10Price() != 0) {
            view.drawMainLine(canvas, ma10Paint, lastX, lastPoint.getMA10Price(), curX, curPoint.getMA10Price());
        }
        //画ma20
        if (lastPoint.getMA20Price() != 0) {
            view.drawMainLine(canvas, ma20Paint, lastX, lastPoint.getMA20Price(), curX, curPoint.getMA20Price());
        }
    }

    @Override
    public void drawText(@NonNull Canvas canvas, @NonNull BaseKChartView view, int position, float x, float y) {
        ICandle point = (IKLine) view.getItem(position);
        String text = "";
        if (view instanceof KChartView) {
            KChartView v = (KChartView) view;
            if (!TextUtil.isEmpty(v.getkLineType())) {
                text = v.getkLineType() + "   ";
                canvas.drawText(text, x, y, mTextPaint);
                x += mTextPaint.measureText(text);
            }
        }
        text = "MA5:" + view.formatValue(point.getMA5Price()) + " ";
        canvas.drawText(text, x, y, ma5Paint);
        x += ma5Paint.measureText(text);
        text = "10:" + view.formatValue(point.getMA10Price()) + " ";
        canvas.drawText(text, x, y, ma10Paint);
        x += ma10Paint.measureText(text);
        text = "20:" + view.formatValue(point.getMA20Price()) + " ";
        canvas.drawText(text, x, y, ma20Paint);
        x += ma20Paint.measureText(text);
        text = "30:" + view.formatValue(point.getMA30Price()) + " ";
        canvas.drawText(text, x, y, ma30Paint);
        if (view.isLongPress()) {
//            drawSelector(view, canvas);
        }
    }

    @Override
    public float getMaxValue(ICandle point) {
        return Math.max(point.getHighPrice(), point.getMA20Price());
    }

    @Override
    public float getMinValue(ICandle point) {
        return Math.min(point.getMA20Price(), point.getLowPrice());
    }

    @Override
    public IValueFormatter getValueFormatter() {
        return new ValueFormatter();
    }

    @Override
    public void drawTranslated1(@Nullable ICandle lastPoint, @NonNull ICandle curPoint, float lastX, float curX, @NonNull Canvas canvas, @NonNull BaseKChartView view, int position) {
        drawCandle1(curPoint, view, canvas, curX, curPoint.getHighPrice(), curPoint.getLowPrice(), curPoint.getOpenPrice(), curPoint.getClosePrice(), position);
    }

    @Override
    public void drawText1(@NonNull Canvas canvas, @NonNull BaseKChartView view, int position, float x, float y) {

    }

    /**
     * 画Candle
     *
     * @param canvas
     * @param x      x轴坐标
     * @param high   最高价
     * @param low    最低价
     * @param open   开盘价
     * @param close  收盘价
     */
    private void drawCandle(ICandle curPoint, BaseKChartView view, Canvas canvas, float x, float high, float low, float open, float close, int pos) {
        high = view.getMainY(high);
        low = view.getMainY(low);
        open = view.getMainY(open);
        close = view.getMainY(close);
        float r = mCandleWidth / 2;
        float lineR = mCandleLineWidth / 2;

        if (view.getCb() != null && view.getCb().isChecked()) {
            bgPaint.setColor(curPoint.getBackground());
            canvas.drawRect(x - view.getmPointWidth2(), view.getmMainRect().top, x + view.getmPointWidth2(), view.getmMainRect().bottom, bgPaint);
        }

        if (open > close) {
            //实心
            if (mCandleSolid) {
                canvas.drawRect(x - r, close, x + r, open, mRedPaint);
                canvas.drawRect(x - lineR, high, x + lineR, low, mRedPaint);
            } else {
                mRedPaint.setStrokeWidth(mCandleLineWidth);
                canvas.drawLine(x, high, x, close, mRedPaint);
                canvas.drawLine(x, open, x, low, mRedPaint);
                canvas.drawLine(x - r + lineR, open, x - r + lineR, close, mRedPaint);
                canvas.drawLine(x + r - lineR, open, x + r - lineR, close, mRedPaint);
                mRedPaint.setStrokeWidth(mCandleLineWidth * view.getScaleX());
                canvas.drawLine(x - r, open, x + r, open, mRedPaint);
                canvas.drawLine(x - r, close, x + r, close, mRedPaint);
            }

        } else if (open < close) {
            canvas.drawRect(x - r, open, x + r, close, mGreenPaint);
            canvas.drawRect(x - lineR, high, x + lineR, low, mGreenPaint);
        } else {
            canvas.drawRect(x - r, open, x + r, close + 1, mRedPaint);
            canvas.drawRect(x - lineR, high, x + lineR, low, mRedPaint);
        }
    }

    private void drawCandle1(ICandle curPoint, BaseKChartView view, Canvas canvas, float x, float high, float low, float open, float close, int pos) {


        high = view.getMainY(high);
        low = view.getMainY(low);

        Paint textBlackPaint = view.getmTextBlackPaint();
        IKLine maxPoint = view.getMaxPoint();
        if (maxPoint != null && maxPoint.getPosition() == pos) {
            float xx = view.translateXtoX(view.getX(maxPoint.getPosition()));
            if (view.isRight(xx)) {
                String text = view.formatValue(maxPoint.getHighPrice()) + " →";
                canvas.drawText(text, x - textBlackPaint.measureText(text), high + view.getTextSize() / 3, textBlackPaint);
            } else {
                canvas.drawText("← " + view.formatValue(maxPoint.getHighPrice()), x, high + view.getTextSize() / 3, textBlackPaint);
            }
        }
        IKLine minPoint = view.getMinPoint();
        if (minPoint != null && minPoint.getPosition() == pos) {
            float xx = view.translateXtoX(view.getX(minPoint.getPosition()));
            if (view.isRight(xx)) {
                String text = view.formatValue(minPoint.getLowPrice()) + " →";
                canvas.drawText(text, x - textBlackPaint.measureText(text), low + view.getTextSize() / 3, textBlackPaint);
            } else {
                canvas.drawText("← " + view.formatValue(minPoint.getLowPrice()), x, low + view.getTextSize() / 3, textBlackPaint);
            }
        }

        if (view.getCb() != null && view.getCb().isChecked()) {
            if (curPoint.getPoint() == 1) {
                bgPaint.setColor(0xFF000000);
                RectF rectF = new RectF(x - view.getmPointWidth2() * 2, low + view.getmPointWidth2(), x + view.getmPointWidth2() * 2, low + view.getmPointWidth2() * 5);
                Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.buy);
                Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                canvas.drawBitmap(bitmap, rect, rectF, bgPaint);
            }

            if (curPoint.getPoint() == -1) {
                bgPaint.setColor(0xFF000000);
                RectF rectF = new RectF(x - view.getmPointWidth2() * 2, high - view.getmPointWidth2() * 5, x + view.getmPointWidth2() * 2, high - view.getmPointWidth2());
                Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.sell);
                Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                canvas.drawBitmap(bitmap, rect, rectF, bgPaint);
            }
        }
    }


    /**
     * draw选择器
     *
     * @param view
     * @param canvas
     */
    private void drawSelector(BaseKChartView view, Canvas canvas) {
        Paint.FontMetrics metrics = mSelectorTextPaint.getFontMetrics();
        float textHeight = metrics.descent - metrics.ascent;

        int index = view.getSelectedIndex();
        float padding = ViewUtil.Dp2Px(mContext, 5);
        float margin = ViewUtil.Dp2Px(mContext, 5);
        float width = 0;
        float left;
        float top = margin + view.getTopPadding();
        float height = padding * 8 + textHeight * 5;

        ICandle point = (ICandle) view.getItem(index);
        List<String> strings = new ArrayList<>();
        strings.add(view.formatDateTime(view.getAdapter().getDate(index)) + view.getAdapter().getTime(index));
        strings.add("高:" + point.getHighPrice());
        strings.add("低:" + point.getLowPrice());
        strings.add("开:" + point.getOpenPrice());
        strings.add("收:" + point.getClosePrice());

        for (String s : strings) {
            width = Math.max(width, mSelectorTextPaint.measureText(s));
        }
        width += padding * 2;

        float x = view.translateXtoX(view.getX(index));
        if (view.isRight(x)) {
            left = margin;
        } else {
            left = view.getChartWidth() - width - margin;
        }

        RectF r = new RectF(left, top, left + width, top + height);
        canvas.drawRoundRect(r, padding, padding, mSelectorBackgroundPaint);
        float y = top + padding * 2 + (textHeight - metrics.bottom - metrics.top) / 2;

        for (String s : strings) {
            canvas.drawText(s, left + padding, y, mSelectorTextPaint);
            y += textHeight + padding;
        }

    }

    /**
     * 设置蜡烛宽度
     *
     * @param candleWidth
     */
    public void setCandleWidth(float candleWidth) {
        mCandleWidth = candleWidth;
    }

    /**
     * 设置蜡烛线宽度
     *
     * @param candleLineWidth
     */
    public void setCandleLineWidth(float candleLineWidth) {
        mCandleLineWidth = candleLineWidth;
    }

    /**
     * 设置ma5颜色
     *
     * @param color
     */
    public void setMa5Color(int color) {
        this.ma5Paint.setColor(color);
    }

    /**
     * 设置ma10颜色
     *
     * @param color
     */
    public void setMa10Color(int color) {
        this.ma10Paint.setColor(color);
    }

    /**
     * 设置ma20颜色
     *
     * @param color
     */
    public void setMa20Color(int color) {
        this.ma20Paint.setColor(color);
    }

    /**
     * 设置ma30颜色
     *
     * @param color
     */
    public void setMa30Color(int color) {
        this.ma30Paint.setColor(color);
    }

    /**
     * 设置选择器文字颜色
     *
     * @param color
     */
    public void setSelectorTextColor(int color) {
        mSelectorTextPaint.setColor(color);
    }

    /**
     * 设置选择器文字大小
     *
     * @param textSize
     */
    public void setSelectorTextSize(float textSize) {
        mSelectorTextPaint.setTextSize(textSize);
    }

    /**
     * 设置选择器背景
     *
     * @param color
     */
    public void setSelectorBackgroundColor(int color) {
        mSelectorBackgroundPaint.setColor(color);
    }

    /**
     * 设置曲线宽度
     */
    public void setLineWidth(float width) {
        ma30Paint.setStrokeWidth(width);
        ma20Paint.setStrokeWidth(width);
        ma10Paint.setStrokeWidth(width);
        ma5Paint.setStrokeWidth(width);
        mTextPaint.setStrokeWidth(width);
    }

    /**
     * 设置文字大小
     */
    public void setTextSize(float textSize) {
        ma30Paint.setTextSize(textSize);
        ma20Paint.setTextSize(textSize);
        ma10Paint.setTextSize(textSize);
        ma5Paint.setTextSize(textSize);
        mTextPaint.setTextSize(textSize);
    }

    /**
     * 蜡烛是否实心
     */
    public void setCandleSolid(boolean candleSolid) {
        mCandleSolid = candleSolid;
    }
}
