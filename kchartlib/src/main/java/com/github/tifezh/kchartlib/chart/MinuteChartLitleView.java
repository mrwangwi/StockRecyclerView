package com.github.tifezh.kchartlib.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;

import com.github.tifezh.kchartlib.R;
import com.github.tifezh.kchartlib.chart.entity.IMinuteLine;
import com.github.tifezh.kchartlib.chart.base.IValueFormatter;
import com.github.tifezh.kchartlib.chart.formatter.BigValueFormatter;
import com.github.tifezh.kchartlib.utils.DateUtil;
import com.github.tifezh.kchartlib.view.IndexViewPager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 分时图
 * 简单的分时图示例 更丰富的需求可能需要在此基础上再作修改
 */
public class MinuteChartLitleView extends View implements GestureDetector.OnGestureListener {

    private final static int ONE_MINUTE = 60000;
    private IndexViewPager indexViewPager;
    private int mHeight = 0;
    private int mWidth = 0;
    private int mTopPadding = 2;
    private int mBottomPadding = 2;

    private Paint mAvgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mGridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPricePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mVolumePaintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mVolumePaintGreen = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint bgTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mBackgroundColor;
    private float mValueMin;
    private float mValueMax;
    private float mValueStart;
    private float mScaleY = 1;
    private float mTextSize = 10;
    private boolean isLongPress = false;
    private int selectedIndex;
    private GestureDetectorCompat mDetector;
    private final List<IMinuteLine> mPoints = new ArrayList<>();
    private Date mFirstStartTime;
    private Date mFirstEndTime;
    private Date mSecondStartTime;
    private Date mSecondEndTime;
    private long mTotalTime;
    private float mPointWidth;

    private float pxChangeRate;
    Path path;

    public void setPxChangeRate(float pxChangeRate) {
        this.pxChangeRate = pxChangeRate;
    }


    private IValueFormatter mVolumeFormatter;

    public MinuteChartLitleView(Context context) {
        super(context);
        init();
    }

    public void setIndexViewPager(IndexViewPager indexViewPager) {
        this.indexViewPager = indexViewPager;
    }

    public MinuteChartLitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MinuteChartLitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDetector = new GestureDetectorCompat(getContext(), this);
        mTopPadding = dp2px(mTopPadding);
        mBottomPadding = dp2px(mBottomPadding);
        mTextSize = sp2px(mTextSize);
        mGridPaint.setColor(Color.parseColor("#F0F0F0"));
        mGridPaint.setStrokeWidth(1);
        mTextPaint.setColor(Color.parseColor("#999999"));
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setStrokeWidth(dp2px(0.5f));
        mBorderPaint.setColor(Color.parseColor("#333333"));
        mBorderPaint.setStrokeWidth(dp2px(0.5f));
        mAvgPaint.setColor(Color.parseColor("#FB7B16"));
        mAvgPaint.setStrokeWidth(dp2px(0.75f));
        mAvgPaint.setTextSize(mTextSize);
        mPricePaint.setColor(Color.parseColor("#2255F7"));
        mPricePaint.setStrokeWidth(dp2px(1f));
        mPricePaint.setTextSize(mTextSize);
        mVolumePaintGreen.setColor(ContextCompat.getColor(getContext(), R.color.chart_green));
        mVolumePaintRed.setColor(ContextCompat.getColor(getContext(), R.color.chart_red));
        mBackgroundColor = Color.parseColor("#FFFFFF");
        mBackgroundPaint.setColor(mBackgroundColor);
        bgPaint.setColor(0xFFE0EDFF);
        bgTextPaint.setColor(0xFF2255F7);
        bgTextPaint.setTextAlign(Paint.Align.CENTER);
        bgTextPaint.setTextSize(dp2px(9));
        mVolumeFormatter = new BigValueFormatter();
        path = new Path();
    }

    private void calculateSelectedX(float x) {
        if (mPoints.size() == 0) return;
        selectedIndex = (int) (x * 1f / getX(mPoints.size() - 1) * (mPoints.size() - 1) + 0.5f);
        if (selectedIndex < 0) {
            selectedIndex = 0;
        }
        if (selectedIndex > mPoints.size() - 1) {
            selectedIndex = mPoints.size() - 1;
        }
    }

    /**
     * 根据索引获取x的值
     */
    private float getX(int position) {
        Date date = mPoints.get(position).getDate();
        if (mSecondStartTime != null && date.getTime() >= mSecondStartTime.getTime()) {
            return 1f * (date.getTime() - mSecondStartTime.getTime() + 60000 +
                    mFirstEndTime.getTime() - mFirstStartTime.getTime()) / mTotalTime * (mWidth - mPointWidth) + mPointWidth / 2f;
        } else {
            return 1f * (date.getTime() - mFirstStartTime.getTime()) / mTotalTime * (mWidth - mPointWidth) + mPointWidth / 2f;
        }
    }

    /**
     * 获取最大能有多少个点
     */
    private long getMaxPointCount() {
        return mTotalTime / ONE_MINUTE;
    }

    /**
     * @param data            数据源
     * @param startTime       显示的开始时间
     * @param endTime         显示的结束时间
     * @param firstEndTime    休息开始时间 可空
     * @param secondStartTime 休息结束时间 可空
     * @param yesClosePrice   昨收价
     */
    public void initData(Collection<? extends IMinuteLine> data,
                         @NonNull Date startTime,
                         @NonNull Date endTime,
                         @Nullable Date firstEndTime,
                         @Nullable Date secondStartTime,
                         float yesClosePrice) {

        this.mFirstStartTime = startTime;
        this.mSecondEndTime = endTime;
        if (mFirstStartTime.getTime() >= mSecondEndTime.getTime())
            throw new IllegalStateException("开始时间不能大于结束时间");
        mTotalTime = mSecondEndTime.getTime() - mFirstStartTime.getTime();
        if (firstEndTime != null && secondStartTime != null) {
            this.mFirstEndTime = firstEndTime;
            this.mSecondStartTime = secondStartTime;
            if (!(mFirstStartTime.getTime() < mFirstEndTime.getTime() &&
                    mFirstEndTime.getTime() < mSecondStartTime.getTime() &&
                    mSecondStartTime.getTime() < mSecondEndTime.getTime())) {
                throw new IllegalStateException("时间区间有误");
            }
            mTotalTime -= mSecondStartTime.getTime() - mFirstEndTime.getTime() - 60000;
        }
        setValueStart(yesClosePrice);
        if (data != null) {
            mPoints.clear();
            this.mPoints.addAll(data);
        }
        notifyChanged();
    }

    /**
     * 当数据发生变化时调用
     */
    public void notifyChanged() {
        mValueMax = Float.MIN_VALUE;
        mValueMin = Float.MAX_VALUE;
        for (int i = 0; i < mPoints.size(); i++) {
            IMinuteLine point = mPoints.get(i);
            mValueMax = Math.max(mValueMax, point.getPrice());
            mValueMin = Math.min(mValueMin, point.getPrice());
        }
        //最大值和开始值的差值
        float offsetValueMax = mValueMax - mValueStart;
        float offsetValueMin = mValueStart - mValueMin;
        //以开始的点为中点值   上下间隙多出20%
        float offset = (offsetValueMax > offsetValueMin ? offsetValueMax : offsetValueMin) * 1f;
        //坐标轴高度以开始的点对称
        mValueMax = mValueStart + offset;
        mValueMin = mValueStart - offset;
        //y轴的缩放值

        //判断最大值和最小值是否一致
        if (mValueMax == mValueMin) {
            //当最大值和最小值都相等的时候 分别增大最大值和 减小最小值
            mValueMax += Math.abs(mValueMax * 0.05f);
            mValueMin -= Math.abs(mValueMax * 0.05f);
            if (mValueMax == 0) {
                mValueMax = 1;
            }
        }
        //成交量的缩放值
        mPointWidth = (float) mWidth / getMaxPointCount();
        mVolumePaintRed.setStrokeWidth(mPointWidth * 0.8f);
        mVolumePaintGreen.setStrokeWidth(mPointWidth * 0.8f);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mHeight = getHeight();
        mWidth = getWidth();
        mScaleY = (getHeight() - mTopPadding - mBottomPadding) / (mValueMax - mValueMin);
        if (mWidth == 0 || mHeight == 0 || mPoints == null || mFirstStartTime == null) {
            return;
        }
        if (pxChangeRate < 0) {
            mPricePaint.setColor(ContextCompat.getColor(getContext(), R.color.chart_green));
            mShadowPaint.setShader(new LinearGradient(0, 0, 0, mHeight, 0xFFDBF1E9, 0x00FFFFFF, Shader.TileMode.CLAMP));
        } else {
            mPricePaint.setColor(ContextCompat.getColor(getContext(), R.color.chart_red));
            mShadowPaint.setShader(new LinearGradient(0, 0, 0, mHeight, 0x30E73146, 0x00FFFFFF, Shader.TileMode.CLAMP));
        }
        path.reset();
        if (mPoints.size() > 0) {
            IMinuteLine lastPoint = mPoints.get(0);
            float lastX = getX(0);
            path.moveTo(lastX, getY(lastPoint.getPrice()));
            for (int i = 0; i < mPoints.size(); i++) {
                if (i % 4 != 0) continue;
                IMinuteLine curPoint = mPoints.get(i);
                float curX = getX(i);
                canvas.drawLine(lastX, getY(lastPoint.getPrice()), curX, getY(curPoint.getPrice()), mPricePaint);
                path.lineTo(curX, getY(curPoint.getPrice()));
                lastPoint = curPoint;
                lastX = curX;
            }
            path.lineTo(getX(mPoints.size() - 1), mHeight);
            path.lineTo(getX(0), mHeight);
            path.close();
            canvas.drawPath(path, mShadowPaint);
        }
    }

    /**
     * 修正y值
     */
    private float getY(float value) {
        return (mValueMax - value) * mScaleY + mBottomPadding;
    }

    /**
     * 解决text居中的问题
     */
    public float fixTextY(float y) {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        return (y + (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent);
    }

    public int dp2px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 保留2位小数
     */
    public String floatToString(float value) {
        String s = String.format("%.2f", value);
        char end = s.charAt(s.length() - 1);
        while (s.contains(".") && (end == '0' || end == '.')) {
            s = s.substring(0, s.length() - 1);
            end = s.charAt(s.length() - 1);
        }
        return s;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        isLongPress = true;
        calculateSelectedX(e.getX());
        invalidate();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    /**
     * 设置开始的值 对称轴线
     */
    public void setValueStart(float valueStart) {
        mValueStart = valueStart;
    }

    /**
     * 修改某个点的值
     *
     * @param position 索引值
     */
    public void changePoint(int position, IMinuteLine point) {
        mPoints.set(position, point);
        notifyChanged();
    }

    /**
     * 获取点的个数
     */
    private int getItemSize() {
        return mPoints.size();
    }

    /**
     * 刷新最后一个点
     */
    public void refreshLastPoint(IMinuteLine point) {
        changePoint(getItemSize() - 1, point);
    }

    /**
     * 添加一个点
     */
    public void addPoint(IMinuteLine point) {
        mPoints.add(point);
        notifyChanged();
    }

    /**
     * 根据索引获取点
     */
    public IMinuteLine getItem(int position) {
        return mPoints.get(position);
    }

    /**
     * 设置成交量格式化器
     *
     * @param volumeFormatter {@link IValueFormatter} 成交量格式化器
     */
    public void setVolumeFormatter(IValueFormatter volumeFormatter) {
        mVolumeFormatter = volumeFormatter;
    }
}
