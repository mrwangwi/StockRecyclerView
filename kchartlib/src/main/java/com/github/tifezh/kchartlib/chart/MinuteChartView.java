package com.github.tifezh.kchartlib.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;

import com.github.tifezh.kchartlib.R;
import com.github.tifezh.kchartlib.chart.base.IValueFormatter;
import com.github.tifezh.kchartlib.chart.entity.IMinuteLine;
import com.github.tifezh.kchartlib.chart.formatter.BigValueFormatter;
import com.github.tifezh.kchartlib.common.StockTickBean;
import com.github.tifezh.kchartlib.utils.DateUtil;
import com.github.tifezh.kchartlib.utils.TextUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 分时图
 * 简单的分时图示例 更丰富的需求可能需要在此基础上再作修改
 */
public class MinuteChartView extends View implements GestureDetector.OnGestureListener {

    private final static int ONE_MINUTE = 60000;
    private int mHeight = 0;
    private int mWidth = 0;
    private int mVolumeHeight = 100;
    private int mTopPadding = 15;
    private int mBottomPadding = 15;
    private int mGridRows = 4;
    private int GridColumns = 4;
    private Paint inputPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mAvgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mGridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPricePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mVolumePaintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mVolumePaintGreen = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mVolumePaintGary = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint bgTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mBackgroundColor;
    private float mValueMin;
    private float mValueMax;
    private float mVolumeMax;
    private float mValueStart;
    private float mValueHeight;
    private float mValueLow;
    private float mScaleY = 1;
    private float mVolumeScaleY = 1;
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
    private StockTickBean xianshouPoint;
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    private boolean isSimpleTime = true;

    private boolean isBeforeHours;
    private boolean isAfterHours;
    private int afterHoursAmount;
    private int afterHoursAmountLast;

    private float inputPrice;

    TextView tvAgv;
    TextView tvNew;

    private String stockType = "";

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public void setXianshouPoint(StockTickBean xianshouPoint) {
        this.xianshouPoint = xianshouPoint;
        invalidate();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (isHandling) {
            getParent().requestDisallowInterceptTouchEvent(false);
        } else {
            getParent().requestDisallowInterceptTouchEvent(isLongPress);
        }
        return super.dispatchTouchEvent(event);
    }

    private IValueFormatter mVolumeFormatter;

    public MinuteChartView(Context context) {
        super(context);
        init();
    }

    public MinuteChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MinuteChartView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mAvgPaint.setColor(Color.parseColor("#FFBF0B"));
        mAvgPaint.setStrokeWidth(dp2px(0.75f));
        mAvgPaint.setTextSize(mTextSize);
        mPricePaint.setColor(Color.parseColor("#2255F7"));
        mPricePaint.setStrokeWidth(dp2px(1f));
        mPricePaint.setTextSize(mTextSize);
        inputPaint.setColor(Color.parseColor("#999999"));
        inputPaint.setStrokeWidth(dp2px(0.5f));
        inputPaint.setTextSize(mTextSize);
        inputPaint.setStyle(Paint.Style.STROKE);
        DashPathEffect pathEffect = new DashPathEffect(new float[]{dp2px(2), dp2px(2)}, 0);
        inputPaint.setPathEffect(pathEffect);
        mVolumePaintGary.setColor(ContextCompat.getColor(getContext(), R.color.chart_gary));
        mVolumePaintGreen.setColor(ContextCompat.getColor(getContext(), R.color.chart_green));
        mVolumePaintRed.setColor(ContextCompat.getColor(getContext(), R.color.chart_red));
        mBackgroundColor = Color.parseColor("#FFFFFF");
        mBackgroundPaint.setColor(mBackgroundColor);
        bgPaint.setColor(0xFFE0EDFF);
        bgTextPaint.setColor(0xFF2255F7);
        bgTextPaint.setTextAlign(Paint.Align.CENTER);
        bgTextPaint.setTextSize(dp2px(9));
        mVolumeFormatter = new BigValueFormatter();
    }

    private Handler handler = new Handler();
    private boolean isHandling;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            isLongPress = false;
            isHandling = false;
            invalidate();
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                handler.removeCallbacks(runnable);
                //一个点的时候滑动
                if (event.getPointerCount() == 1) {
                    //长按之后移动
                    if (isLongPress) {
                        calculateSelectedX(event.getX());
                        invalidate();
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isHandling = true;
                handler.postDelayed(runnable, 2000);
                break;
        }
        return true;
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

    public void setInputPrice(String inputPrice) {
        this.inputPrice = TextUtil.parseFloat(inputPrice);
        invalidate();
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


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int height = h - mTopPadding - mBottomPadding;
        mVolumeHeight = getHeight() / 4;
        this.mHeight = height - mVolumeHeight;
        this.mWidth = w;
        notifyChanged();
    }

    /**
     * @param data            数据源
     * @param startTime       显示的开始时间
     * @param endTime         显示的结束时间
     * @param firstEndTime    休息开始时间 可空
     * @param secondStartTime 休息结束时间 可空
     * @param yesClosePrice   昨收价
     */
    public void initData(List<? extends IMinuteLine> data,
                         @NonNull Date startTime,
                         @NonNull Date endTime,
                         @Nullable Date firstEndTime,
                         @Nullable Date secondStartTime,
                         float yesClosePrice,
                         String mValueHeight,
                         String mValueLow) {

        if (isLongPress) return;
        isSimpleTime = format.format(endTime).equals("15:00");
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
        this.mValueHeight = TextUtil.parseFloat(mValueHeight);
        this.mValueLow = TextUtil.parseFloat(mValueLow);
        setValueStart(yesClosePrice, false);
        if (data != null) {
            isBeforeHours = true;
            isAfterHours = false;
            afterHoursAmount = 0;
            mPoints.clear();
            this.mPoints.addAll(data);
            for (int i = 0; i < data.size(); i++) {
                if (format.format(data.get(i).getDate()).equals("15:00")) {
                    isBeforeHours = true;
                } else if (format.format(data.get(i).getDate()).compareTo("15:00") > 0) {
                    isAfterHours = true;
                    isBeforeHours = false;
                    afterHoursAmount += data.get(i).getVolume();
                    afterHoursAmountLast = (int) data.get(data.size() - 1).getVolume();
                }
            }
        }

        notifyChanged();
    }

    /**
     * 当数据发生变化时调用
     */
    public void notifyChanged() {
        mValueMax = Float.MIN_VALUE;
        mValueMin = Float.MAX_VALUE;
        mVolumeMax = Float.MIN_VALUE;

        if (mPoints.size() == 0) {
            mValueMax = mValueStart + 0.01f;
            mValueMin = mValueStart - 0.01f;
        } else {
            for (int i = 0; i < mPoints.size(); i++) {
                IMinuteLine point = mPoints.get(i);
                mValueMax = Math.max(mValueMax, mValueHeight);
                mValueMax = Math.max(mValueMax, point.getPrice());
                mValueMin = Math.min(mValueMin, mValueLow);
                mValueMin = Math.min(mValueMin, point.getPrice());
                mVolumeMax = Math.max(mVolumeMax, point.getVolume());
                if (!"BLOCK".equals(stockType)) {
                    mValueMax = Math.max(mValueMax, point.getAvgPrice());
                    mValueMin = Math.min(mValueMin, point.getAvgPrice());
                }
            }
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
        mScaleY = mHeight / (mValueMax - mValueMin);
        //判断最大值和最小值是否一致
        if (mValueMax == mValueMin) {
            //当最大值和最小值都相等的时候 分别增大最大值和 减小最小值
            mValueMax += Math.abs(mValueMax * 0.05f);
            mValueMin -= Math.abs(mValueMax * 0.05f);
            if (mValueMax == 0) {
                mValueMax = 1;
            }
        }

        if (mVolumeMax == 0) {
            mVolumeMax = 1;
        }
        //成交量的缩放值
        mVolumeScaleY = mVolumeHeight / mVolumeMax;
        mPointWidth = (float) mWidth / getMaxPointCount();
        mVolumePaintRed.setStrokeWidth(mPointWidth * 0.8f);
        mVolumePaintGreen.setStrokeWidth(mPointWidth * 0.8f);
        mVolumePaintGary.setStrokeWidth(mPointWidth * 0.8f);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(mBackgroundColor);
        mPricePaint.setColor(Color.parseColor("#2255F7"));
        if (mWidth == 0 || mHeight == 0 || mPoints == null || mFirstStartTime == null) {
            return;
        }
        drawGird(canvas);
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, mHeight, 0XFFEFF3FE, 0x30FCFCFE, Shader.TileMode.CLAMP);
        mShadowPaint.setShader(linearGradient);
        Path path = new Path();
        if (mPoints.size() > 0) {
            IMinuteLine lastPoint = mPoints.get(0);
            float lastX = getX(0);
            path.moveTo(lastX, getY(lastPoint.getPrice()));
            for (int i = 0; i < mPoints.size(); i++) {
                IMinuteLine curPoint = mPoints.get(i);
                float curX = getX(i);
                canvas.drawLine(lastX, getY(lastPoint.getPrice()), curX, getY(curPoint.getPrice()), mPricePaint);

                path.lineTo(curX, getY(curPoint.getPrice()));

                //成交量
                Paint volumePaint;
                float price;
                if (i == 0) {
                    price = mValueStart;
                } else {
                    price = lastPoint.getPrice();
                }
                if (curPoint.getPrice() > price) {
                    volumePaint = mVolumePaintRed;
                } else if (curPoint.getPrice() < price) {
                    volumePaint = mVolumePaintGreen;
                } else {
                    volumePaint = mVolumePaintGary;
                }
                canvas.drawLine(curX, getVolumeY(0), curX, getVolumeY(curPoint.getVolume()), volumePaint);
                lastPoint = curPoint;
                lastX = curX;
            }
            path.lineTo(getX(mPoints.size() - 1), mHeight);
            path.lineTo(getX(0), mHeight);
            path.close();
            canvas.drawPath(path, mShadowPaint);

            IMinuteLine lastPoint1 = mPoints.get(0);
            float lastX1 = getX(0);
            for (int i = 0; i < mPoints.size(); i++) {
                IMinuteLine curPoint = mPoints.get(i);
                float curX = getX(i);
                canvas.drawLine(lastX1, getY(lastPoint1.getAvgPrice()), curX, getY(curPoint.getAvgPrice()), mAvgPaint);
                lastPoint1 = curPoint;
                lastX1 = curX;
            }
        }

        if (inputPrice > 0 && inputPrice <= mValueMax && inputPrice >= mValueMin) {
            Path path1 = new Path();
            path1.moveTo(0, getY(inputPrice));
            path1.lineTo(mWidth, getY(inputPrice));
            canvas.drawPath(path1, inputPaint);
        }

        drawText(canvas);
        //画指示线
        if (isLongPress) {
            if (mPoints.size() == 0) return;
            IMinuteLine point = mPoints.get(selectedIndex);
            float x = getX(selectedIndex);
            canvas.drawLine(x, 0, x, mHeight, mBorderPaint);
            canvas.drawLine(x, mHeight + mBottomPadding, x, mHeight + mBottomPadding + mVolumeHeight, mBorderPaint);
            canvas.drawLine(0, getY(point.getPrice()), mWidth, getY(point.getPrice()), mBorderPaint);
            //画指示线的时间
            String text = DateUtil.shortTimeFormat.format(point.getDate());
            x = x - mTextPaint.measureText(text) / 2;
            if (x < 0) {
                x = 0;
            }
            if (x > mWidth - mTextPaint.measureText(text)) {
                x = mWidth - mTextPaint.measureText(text);
            }
            Paint.FontMetrics fm = mTextPaint.getFontMetrics();
            float textHeight = fm.descent - fm.ascent;
            float baseLine = (textHeight - fm.bottom - fm.top) / 2;
            //下方时间
            canvas.drawRoundRect(x, mHeight - baseLine + textHeight * 19 / 20, x + mTextPaint.measureText(text) + 10, mHeight + textHeight * 9 / 8, 10, 10, bgPaint);
            canvas.drawText(text, x + (mTextPaint.measureText(text) + 10) / 2, mHeight + textHeight * 0.9f, bgTextPaint);

            float r = textHeight / 2;
            float y = getY(point.getPrice());
            //左方值
            text = floatToString(point.getPrice());
            canvas.drawRoundRect(0, y - r, mTextPaint.measureText(text) + 10, y + r, 10, 10, bgPaint);
            canvas.drawText(text, (mTextPaint.measureText(text) + 10) / 2, fixTextY(y), bgTextPaint);
            //右方值
//            text = floatToString((point.getPrice() - mValueStart) * 100f / mValueStart) + "%";
//            canvas.drawRect(mWidth - mTextPaint.measureText(text), y - r, mWidth, y + r, mBackgroundPaint);
//            canvas.drawText(text, mWidth - mTextPaint.measureText(text), fixTextY(y), mTextPaint);
        }
        drawValue(canvas, isLongPress ? selectedIndex : mPoints.size() - 1);
    }

    /**
     * 画值
     */
    private void drawValue(Canvas canvas, int index) {
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float textHeight = fm.descent - fm.ascent;
        float baseLine = (textHeight - fm.bottom - fm.top) / 2;
        if (index >= 0 && index < mPoints.size()) {
            float y = baseLine - textHeight;
            IMinuteLine point = mPoints.get(index);
            if (DateUtil.shortTimeFormat.format(point.getDate()).compareTo("15:00") > 0) {
                for (int i = 0; i < mPoints.size(); i++) {
                    if (DateUtil.shortTimeFormat.format(mPoints.get(i).getDate()).equals("15:00")) {
                        float amount = point.getVolume();
                        point = mPoints.get(i);
                        point.setAmount(amount);
                        break;
                    }
                }
            }
            String text = "";
            float x = 0;
            if (point.getChangeRate() > 0) {
                mPricePaint.setColor(ContextCompat.getColor(getContext(), R.color.chart_red));
            } else {
                mPricePaint.setColor(ContextCompat.getColor(getContext(), R.color.chart_green));
            }
            if (!"BLOCK".equals(stockType)) {
                String s = "MRI".equals(stockType) ? "领先:" : "均价:";
                text = s + floatToString(point.getAvgPrice()) + "   ";
                if (tvAgv == null) {
                    canvas.drawText(text, x, y, mAvgPaint);
                    x += mAvgPaint.measureText(text);
                } else {
                    tvAgv.setText(text);
                    tvAgv.setTextColor(mAvgPaint.getColor());
                }
            }
            if (tvNew == null) {
                text = "最新:" + floatToString(point.getPrice()) + "   ";
                canvas.drawText(text, x, y, mPricePaint);

                x += mPricePaint.measureText(text);
                text = point.getChangePrice() + "   ";
                canvas.drawText(text, x, y, mPricePaint);

                x += mPricePaint.measureText(text);
                text = floatToString(point.getChangeRate()) + "%   ";
                canvas.drawText(text, x, y, mPricePaint);
            } else {
                tvNew.setText("最新:" + floatToString(point.getPrice()) + "   " + point.getChangePrice() + "   " + floatToString(point.getChangeRate()) + "%   ");
                tvNew.setTextColor(mPricePaint.getColor());
            }
            //成交量

            //成交量
            Paint volumePaint;
            if (index == 0) {
                volumePaint = mVolumePaintRed;
            } else {
                IMinuteLine last = mPoints.get(index - 1);
                volumePaint = ((index == 0 && point.getPrice() <= mValueStart) || point.getPrice() <= last.getPrice()) ? mVolumePaintGreen : mVolumePaintRed;
            }
            volumePaint.setTextSize(mTextSize);

            float x1 = 0;
            String text1 = "分时量   ";
            mTextPaint.setColor(0xff333333);
            canvas.drawText(text1, x1, mHeight + mBottomPadding + baseLine, mTextPaint);
            x1 += mPricePaint.measureText(text1);
            text1 = "量:";
            mTextPaint.setColor(0xff999999);
            canvas.drawText(text1, x1, mHeight + mBottomPadding + baseLine, mTextPaint);
            x1 += mPricePaint.measureText(text1);
            text1 = TextUtil.getChangeAmountIntegerReturnInt(point.getVolume() + "") + "   ";
            mTextPaint.setColor(0xff333333);
            canvas.drawText(text1, x1, mHeight + mBottomPadding + baseLine, volumePaint);
            x1 += mPricePaint.measureText(text1);


            if (isBeforeHours) {
                if ("STOCK".equals(stockType) && xianshouPoint != null) {
                    text1 = "现手:";
                    mTextPaint.setColor(0xff999999);
                    canvas.drawText(text1, x1, mHeight + mBottomPadding + baseLine, mTextPaint);
                    x1 += mPricePaint.measureText(text1);
                    if (xianshouPoint.getBusinessDirection().equals("1")) {
                        mTextPaint.setColor(getResources().getColor(R.color.chart_red));
                    } else if (xianshouPoint.getBusinessDirection().equals("0")) {
                        mTextPaint.setColor(getResources().getColor(R.color.chart_green));
                    } else {
                        mTextPaint.setColor(0xff333333);
                    }
                    text1 = TextUtil.getChangeAmountIntegerReturnInt((xianshouPoint.getBusinessAmount()) + "") + "   ";
                    canvas.drawText(text1, x1, mHeight + mBottomPadding + baseLine, mTextPaint);
                }
            } else {
                if (isAfterHours) {
                    text1 = "盘后:";
                    mTextPaint.setColor(0xff999999);
                    canvas.drawText(text1, x1, mHeight + mBottomPadding + baseLine, mTextPaint);
                    x1 += mPricePaint.measureText(text1);
                    mTextPaint.setColor(getResources().getColor(R.color.chart_green));
                    if (isLongPress) {
                        if (format.format(point.getDate()).compareTo("15:00") >= 0) {
                            if (point.getVolume1() == 0) {
                                text1 = "0" + "   ";
                                mTextPaint.setColor(0xff333333);
                            } else {
                                text1 = TextUtil.getChangeAmountIntegerReturnInt((point.getVolume1()) + "") + "   ";
                            }
                        } else {
                            if (afterHoursAmountLast == 0) {
                                text1 = "0" + "   ";
                                mTextPaint.setColor(0xff333333);
                            } else {
                                text1 = TextUtil.getChangeAmountIntegerReturnInt((afterHoursAmountLast) + "") + "   ";
                            }
                        }
                    } else {
                        text1 = TextUtil.getChangeAmountIntegerReturnInt(afterHoursAmount + "") + "   ";
                    }
                    canvas.drawText(text1, x1, mHeight + mBottomPadding + baseLine, mTextPaint);
                }
            }
//            if ("STOCK".equals(stockType) && xianshouPoint != null) {
//                if (xianshouPoint.isAfterHours()) {
//                    text1 = "盘后:";
//                } else {
//                    text1 = "现手:";
//                }
//                mTextPaint.setColor(0xff999999);
//                canvas.drawText(text1, x1, mHeight + mBottomPadding + baseLine, mTextPaint);
//                x1 += mPricePaint.measureText(text1);
//                if (xianshouPoint.getBusinessDirection().equals("1")) {
//                    mTextPaint.setColor(getResources().getColor(R.color.chart_red));
//                } else if (xianshouPoint.getBusinessDirection().equals("0")) {
//                    mTextPaint.setColor(getResources().getColor(R.color.chart_green));
//                } else {
//                    mTextPaint.setColor(0xff333333);
//                }
//                if (isLongPress) {
//                    if (point.getVolume1() == 0) {
//                        text1 = "--" + "   ";
//                        mTextPaint.setColor(0xff333333);
//                    } else {
//                        text1 = TextUtil.getChangeAmountIntegerReturnInt((point.getVolume1()) + "") + "   ";
//                    }
//                } else {
//                    text1 = TextUtil.getChangeAmountIntegerReturnInt((xianshouPoint.getBusinessAmount()) + "") + "   ";
//                }
//                canvas.drawText(text1, x1, mHeight + mBottomPadding + baseLine, mTextPaint);
//            }
            mTextPaint.setColor(0xff333333);
        } else {
            float x1 = 0;
            String text1 = "分时量   ";
            mTextPaint.setColor(0xff333333);
            canvas.drawText(text1, x1, mHeight + mBottomPadding + baseLine, mTextPaint);
            x1 += mPricePaint.measureText(text1);
            text1 = "量:";
            mTextPaint.setColor(0xff999999);
            canvas.drawText(text1, x1, mHeight + mBottomPadding + baseLine, mTextPaint);
            x1 += mPricePaint.measureText(text1);
            text1 = "--";
            mTextPaint.setColor(0xff333333);
            canvas.drawText(text1, x1, mHeight + mBottomPadding + baseLine, mTextPaint);
        }
    }

    /**
     * 修正y值
     */
    private float getY(float value) {
        return (mValueMax - value) * mScaleY;
    }

    private float getVolumeY(float value) {
        return (mVolumeMax - value) * mVolumeScaleY + mHeight + mBottomPadding;
    }

    private void drawGird(Canvas canvas) {
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float textHeight = fm.descent - fm.ascent;
        mTextPaint.setColor(0xff999999);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        //先画出坐标轴
        canvas.translate(0, mTopPadding);
        canvas.scale(1, 1);
        //横向的grid
        float rowSpace = mHeight / mGridRows;

        for (int i = 0; i <= mGridRows; i++) {
            canvas.drawLine(0, rowSpace * i, mWidth, rowSpace * i, mGridPaint);
        }
        canvas.drawLine(0, rowSpace * mGridRows / 2, mWidth, rowSpace * mGridRows / 2, mGridPaint);
        canvas.drawLine(0, mHeight + mBottomPadding, mWidth, mHeight + mBottomPadding, mGridPaint);
        canvas.drawLine(0, mHeight + mVolumeHeight + mBottomPadding, mWidth, mHeight + mVolumeHeight + mBottomPadding, mGridPaint);
        //纵向的grid

        float columnSpace;
        if (isSimpleTime) {
            columnSpace = mWidth / GridColumns;
            for (int i = 0; i <= GridColumns; i++) {
                if (i == 2) {
                    //画时间
                    float y = mHeight + textHeight * 0.9f;
                    canvas.drawText(DateUtil.shortTimeFormat.format(mFirstEndTime) + "/" + DateUtil.shortTimeFormat.format(mSecondStartTime), columnSpace * i, y, mTextPaint);
                }
                canvas.drawLine(columnSpace * i, 0, columnSpace * i, mHeight, mGridPaint);
                canvas.drawLine(columnSpace * i, mHeight + mBottomPadding, columnSpace * i, mHeight + mTopPadding + mBottomPadding + mVolumeHeight, mGridPaint);
            }

        } else {
            columnSpace = mWidth * 2 / 9;
            for (int i = 0; i <= GridColumns; i++) {
                if (i == 2) {
                    //画时间
                    float y = mHeight + textHeight * 0.9f;
                    canvas.drawText(DateUtil.shortTimeFormat.format(mFirstEndTime) + "/" + DateUtil.shortTimeFormat.format(mSecondStartTime), columnSpace * i, y, mTextPaint);
                }
                canvas.drawLine(columnSpace * i, 0, columnSpace * i, mHeight, mGridPaint);
                canvas.drawLine(columnSpace * i, mHeight + mBottomPadding, columnSpace * i, mHeight + mTopPadding + mBottomPadding + mVolumeHeight, mGridPaint);
            }
            canvas.drawLine(mWidth, 0, mWidth, getHeight(), mGridPaint);
        }
        mTextPaint.setTextAlign(Paint.Align.LEFT);
    }

    /**
     * 解决text居中的问题
     */
    public float fixTextY(float y) {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        return (y + (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent);
    }

    private void drawText(Canvas canvas) {
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float textHeight = fm.descent - fm.ascent;
        float baseLine = (textHeight - fm.bottom - fm.top) / 2;
        //画左边的值
        mTextPaint.setColor(0xFFE73146);
        canvas.drawText(floatToString(mValueMax), 0, baseLine, mTextPaint);
        mTextPaint.setColor(0xFF07A167);
        canvas.drawText(floatToString(mValueMin), 0, mHeight - baseLine * 0.3f, mTextPaint);
        mTextPaint.setColor(0xFF999999);
        float rowValue = (mValueMax - mValueMin) / mGridRows;
        float rowSpace = mHeight / mGridRows;
        for (int i = 0; i <= mGridRows; i++) {
            String text = floatToString(rowValue * (mGridRows - i) + mValueMin);
//            if (i >= 1 && i < mGridRows) {
            if (i == mGridRows / 2) {
                canvas.drawText(text, 0, fixTextY(rowSpace * i), mTextPaint);
            }
        }
        String text = floatToString((mValueMax - mValueStart) * 100f / mValueStart) + "%";
        mTextPaint.setColor(0xFFE73146);
        canvas.drawText(text, mWidth - mTextPaint.measureText(text), baseLine, mTextPaint);
        text = floatToString((mValueMin - mValueStart) * 100f / mValueStart) + "%";
        mTextPaint.setColor(0xFF07A167);
        canvas.drawText(text, mWidth - mTextPaint.measureText(text), mHeight - baseLine * 0.3f, mTextPaint);
        mTextPaint.setColor(0xFF999999);
        for (int i = 0; i <= mGridRows; i++) {
            text = floatToString((rowValue * (mGridRows - i) + mValueMin - mValueStart) * 100f / mValueStart) + "%";
//            if (i >= 1 && i < mGridRows) {
            if (i == mGridRows / 2) {
                canvas.drawText(text, mWidth - mTextPaint.measureText(text), fixTextY(rowSpace * i), mTextPaint);
            }
        }
        //画时间
        float y = mHeight + textHeight * 0.9f;
        canvas.drawText(DateUtil.shortTimeFormat.format(mFirstStartTime), 0, y, mTextPaint);
        canvas.drawText(DateUtil.shortTimeFormat.format(mSecondEndTime),
                mWidth - mTextPaint.measureText(DateUtil.shortTimeFormat.format(mSecondEndTime)), y, mTextPaint);
        //成交量
        if (mPoints.size() > 0) {
            String text1 = TextUtil.getChangeAmountIntegerReturnInt(mVolumeMax + "");
            canvas.drawText(text1, mWidth - mTextPaint.measureText(text1), mHeight + baseLine + mBottomPadding, mTextPaint);
        }
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
//        String s = String.format("%.2f", value);
//        char end = s.charAt(s.length() - 1);
//        while (s.contains(".") && (end == '0' || end == '.')) {
//            s = s.substring(0, s.length() - 1);
//            end = s.charAt(s.length() - 1);
//        }
        return TextUtil.doubleFormat(value + "");
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
        isLongPress = false;
        invalidate();
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
    public void setValueStart(float valueStart, boolean isSet) {
        if (isSet) {
            if (mValueStart != valueStart) {
                this.mValueStart = valueStart;
                invalidate();
            }
        } else {
            if (mValueStart == 0) {
                this.mValueStart = valueStart;
                invalidate();
            }
        }
    }

    /**
     * 设置开始的值 对称轴线
     */
    public void setValueStart(float valueStart) {
        this.mValueStart = valueStart;
    }

    public float getmValueStart() {
        return mValueStart;
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

    public void setTvAgvNew(TextView tvAgv, TextView tvNew) {
        this.tvAgv = tvAgv;
        this.tvNew = tvNew;
    }
}
