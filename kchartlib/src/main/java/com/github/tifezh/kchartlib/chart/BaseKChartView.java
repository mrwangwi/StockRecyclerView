package com.github.tifezh.kchartlib.chart;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import androidx.core.view.GestureDetectorCompat;

import com.github.tifezh.kchartlib.R;
import com.github.tifezh.kchartlib.chart.base.IAdapter;
import com.github.tifezh.kchartlib.chart.base.IChartDraw;
import com.github.tifezh.kchartlib.chart.base.IDateTimeFormatter;
import com.github.tifezh.kchartlib.chart.base.IValueFormatter;
import com.github.tifezh.kchartlib.chart.entity.ICandle;
import com.github.tifezh.kchartlib.chart.entity.IKLine;
import com.github.tifezh.kchartlib.chart.formatter.TimeFormatter;
import com.github.tifezh.kchartlib.chart.formatter.ValueFormatter;
import com.github.tifezh.kchartlib.common.KLineEntity;
import com.github.tifezh.kchartlib.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * k线图
 * Created by tian on 2016/5/3.
 */
public abstract class BaseKChartView extends ScrollAndScaleView {
    private int mChildDrawPosition = 0;
    private int mChildDrawPosition1 = 0;

    private float mTranslateX = Float.MIN_VALUE;

    private int mWidth = 0;

    private int mTopPadding;

    private int mBottomPadding;

    private float mMainScaleY = 1;

    private float mChildScaleY = 1;

    private float mChildScaleY1 = 1;

    private float mDataLen = 0;

    private float mMainMaxValue = Integer.MIN_VALUE;

    private float mMainMinValue = Integer.MAX_VALUE;

    private float mChildMaxValue = Integer.MIN_VALUE;

    private float mChildMinValue = Integer.MAX_VALUE;

    private float mChildMaxValue1 = Integer.MIN_VALUE;

    private float mChildMinValue1 = Integer.MAX_VALUE;

    private int mStartIndex = 0;

    private int mStopIndex = 0;

    private float mPointWidth = 6;

    private int mGridRows = 4;

    private int mGridColumns = 4;

    private Paint mGridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mTextBlackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mSelectedLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint bgTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int mSelectedIndex;

    private IChartDraw mMainDraw;

    private IAdapter mAdapter;

    private IKLine maxPoint;
    private IKLine minPoint;

    private CheckBox cb;

    public CheckBox getCb() {
        return cb;
    }

    public void setCb(CheckBox cb) {
        this.cb = cb;
    }

    public boolean isTwo = false;

    private DataSetObserver mDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            mItemCount = getAdapter().getCount();
            setNum();
        }

        @Override
        public void onInvalidated() {
            mItemCount = getAdapter().getCount();
            notifyChanged();
        }
    };
    //当前点的个数
    private int mItemCount;
    private IChartDraw mChildDraw;
    private IChartDraw mChildDraw1;
    private List<IChartDraw> mChildDraws = new ArrayList<>();
    private List<IChartDraw> mChildDraws1 = new ArrayList<>();

    private IValueFormatter mValueFormatter;
    private IDateTimeFormatter mDateTimeFormatter;

    protected KChartTabView mKChartTabView;

    protected KChartTabView mKChartTabView1;

    private ValueAnimator mAnimator;

    private long mAnimationDuration = 0;

    private float mOverScrollRange = 0;

    private OnSelectedChangedListener mOnSelectedChangedListener = null;

    private OnSelectListener onSelectListener = null;

    private Rect mMainRect;

    private Rect mTabRect;

    private Rect mChildRect;

    private Rect mTabRect1;

    private Rect mChildRect1;

    private float mLineWidth;

    private int textHeight;


    private float num = 0;
    private float cloumNum;
    private int requestNum;


    public BaseKChartView(Context context) {
        super(context);
        init();
    }

    public BaseKChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @SuppressLint("ResourceType")
    public BaseKChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.KChartView);
        if (array != null) {
            try {
                //public
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setTextSize(array.getDimension(R.styleable.KChartView_kc_text_size, 10));
                isTwo = array.getBoolean(R.styleable.KChartView_is_two, false);
                Paint.FontMetrics fm = paint.getFontMetrics();
                textHeight = (int) (fm.descent - fm.ascent);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                array.recycle();
            }
        }
        init();
    }

    private void init() {
        setWillNotDraw(false);
        mTextBlackPaint.setColor(0xFF333333);
        bgPaint.setColor(0xFFE0EDFF);
        bgTextPaint.setColor(0xFF2255F7);
        bgTextPaint.setTextAlign(Paint.Align.CENTER);
        bgTextPaint.setTextSize(dp2px(9));
        mDetector = new GestureDetectorCompat(getContext(), this);
        mScaleDetector = new ScaleGestureDetector(getContext(), this);
        mTopPadding = (int) getResources().getDimension(R.dimen.chart_top_padding);
//        mBottomPadding = (int) getResources().getDimension(R.dimen.chart_bottom_padding);
        mBottomPadding = 0;

        mKChartTabView = new KChartTabView(getContext());
        addView(mKChartTabView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mKChartTabView.setOnTabSelectListener(new KChartTabView.TabSelectListener() {
            @Override
            public void onTabSelected(int type) {
                mChildDrawPosition = type;
                setChildDraw();
            }
        });

        if (isTwo) {
            mKChartTabView1 = new KChartTabView(getContext());
            addView(mKChartTabView1, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mKChartTabView1.setOnTabSelectListener(new KChartTabView.TabSelectListener() {
                @Override
                public void onTabSelected(int type) {
                    mChildDrawPosition1 = type;
                    setChildDraw1();
                }
            });
        }

        mAnimator = ValueAnimator.ofFloat(0f, 1f);
        mAnimator.setDuration(mAnimationDuration);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        if (mItemCount != 0) {
            setNum();
        }
        initRect(w, h);
        mKChartTabView.setTranslationY(mMainRect.bottom);
        if (isTwo) {
            mKChartTabView1.setTranslationY(mChildRect.bottom);
        }
        setTranslateXFromScrollX(mScrollX);
    }

    private void initRect(int w, int h) {

        if (isTwo) {
            int mMainChildSpace = mKChartTabView.getMeasuredHeight();
            int displayHeight = (int) (h - mTopPadding / 3 * 4.5 - mTopPadding - 2 * mMainChildSpace);
            int mMainHeight = (int) (displayHeight * 0.6f);
            int mChildHeight = (int) (displayHeight * 0.2f);
            mMainRect = new Rect(0, (int) (mTopPadding / 3 * 4.5), mWidth, (int) (mTopPadding / 3 * 4.5) + mMainHeight);
            mTabRect = new Rect(0, mMainRect.bottom + mTopPadding, mWidth, mMainRect.bottom + mTopPadding + mMainChildSpace);
            mChildRect = new Rect(0, mTabRect.bottom, mWidth, mTabRect.bottom + mChildHeight);
            mTabRect1 = new Rect(0, mChildRect.bottom, mWidth, mChildRect.bottom + mMainChildSpace);
            mChildRect1 = new Rect(0, mChildRect.bottom + mMainChildSpace, mWidth, mChildRect.bottom + mMainChildSpace + mChildHeight);
        } else {
            int mMainChildSpace = mKChartTabView.getMeasuredHeight();
            int displayHeight = (int) (h - mTopPadding / 3 * 4.5 - mTopPadding - mMainChildSpace);
            int mMainHeight = (int) (displayHeight * 0.75f);
            int mChildHeight = (int) (displayHeight * 0.25f);
            mMainRect = new Rect(0, (int) (mTopPadding / 3 * 4.5), mWidth, (int) (mTopPadding / 3 * 4.5 + mMainHeight));
            mTabRect = new Rect(0, mMainRect.bottom + mTopPadding, mWidth, mMainRect.bottom + mTopPadding + mMainChildSpace);
            mChildRect = new Rect(0, mTabRect.bottom, mWidth, mTabRect.bottom + mChildHeight);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(mBackgroundPaint.getColor());
        if (mWidth == 0 || mMainRect.height() == 0 || mItemCount == 0) {
            return;
        }
        calculateValue();
        canvas.save();
        canvas.scale(1, 1);
        drawGird(canvas);
        drawK(canvas);
        drawHeightLow(canvas);
        drawText(canvas);
        drawValue(canvas, isLongPress ? mSelectedIndex : mStopIndex);
        canvas.restore();
    }

    public float getMainY(float value) {
        return (mMainMaxValue - value) * mMainScaleY + mMainRect.top;
    }

    public float getChildY(float value) {
        return (mChildMaxValue - value) * mChildScaleY + mChildRect.top + textHeight;
    }

    public float getChildY1(float value) {
        return (mChildMaxValue1 - value) * mChildScaleY1 + mChildRect1.top + textHeight;
    }

    /**
     * 解决text居中的问题
     */
    public float fixTextY(float y) {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        return (y + (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent);
    }

    /**
     * 画表格
     *
     * @param canvas
     */
    private void drawGird(Canvas canvas) {
        //-----------------------上方k线图------------------------
        //横向的grid
        float rowSpace = mMainRect.height() / mGridRows;
        for (int i = 0; i <= mGridRows; i++) {
            canvas.drawLine(0, rowSpace * i + mMainRect.top, mWidth, rowSpace * i + mMainRect.top, mGridPaint);
        }
        //-----------------------下方子图------------------------
        canvas.drawLine(0, mChildRect.top, mWidth, mChildRect.top, mGridPaint);
        canvas.drawLine(0, mChildRect.bottom, mWidth, mChildRect.bottom, mGridPaint);
        if (isTwo) {
            canvas.drawLine(0, mChildRect1.top, mWidth, mChildRect1.top, mGridPaint);
            canvas.drawLine(0, mChildRect1.bottom, mWidth, mChildRect1.bottom, mGridPaint);
//            canvas.drawLine(0, mChildRect.bottom + textHeight, mWidth, mChildRect.bottom + textHeight, mGridPaint);
        }
        //纵向的grid
        float columnSpace = mWidth / mGridColumns;
        for (int i = 0; i <= mGridColumns; i++) {
            canvas.drawLine(columnSpace * i, mMainRect.top, columnSpace * i, mMainRect.bottom, mGridPaint);
            canvas.drawLine(columnSpace * i, mChildRect.top, columnSpace * i, mChildRect.bottom, mGridPaint);
            if (isTwo) {
                canvas.drawLine(columnSpace * i, mChildRect1.top, columnSpace * i, mChildRect1.bottom, mGridPaint);
            }
        }
    }

    /**
     * 画k线图
     *
     * @param canvas
     */
    private void drawK(Canvas canvas) {
        //保存之前的平移，缩放
        canvas.save();
        canvas.translate(mTranslateX * mScaleX, 0);
        canvas.scale(mScaleX, 1);
        for (int i = mStartIndex; i <= mStopIndex; i++) {
            Object currentPoint = getItem(i);
            float currentPointX = getX(i - num);
            Object lastPoint = i == 0 ? currentPoint : getItem(i - 1);
            float lastX = i == 0 ? currentPointX : getX(i - 1 - num);
            if (mMainDraw != null) {
                mMainDraw.drawTranslated(lastPoint, currentPoint, lastX, currentPointX, canvas, this, i);
            }
            if (mChildDraw != null) {
                mChildDraw.drawTranslated(lastPoint, currentPoint, lastX, currentPointX, canvas, this, i);
            }
            if (mChildDraw1 != null) {
                mChildDraw1.drawTranslated1(lastPoint, currentPoint, lastX, currentPointX, canvas, this, i);
            }

        }
        //画选择线
        if (isLongPress) {
            IKLine point = (IKLine) getItem(mSelectedIndex);
            if (point == null) return;
            float x = getX(mSelectedIndex);
            float y = getMainY(point.getClosePrice());
            canvas.drawLine(x - num * mPointWidth, mMainRect.top, x - num * mPointWidth, mMainRect.bottom, mSelectedLinePaint);
            canvas.drawLine(-mTranslateX, y, -mTranslateX + mWidth / mScaleX, y, mSelectedLinePaint);
            canvas.drawLine(x - num * mPointWidth, mChildRect.top, x - num * mPointWidth, mChildRect.bottom, mSelectedLinePaint);
            if (isTwo) {
                canvas.drawLine(x - num * mPointWidth, mChildRect1.top, x - num * mPointWidth, mChildRect1.bottom, mSelectedLinePaint);
            }

            if (getParent().getParent() != null)
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
        }
        //还原 平移缩放
        canvas.restore();
    }

    private void drawHeightLow(Canvas canvas) {
        for (int i = mStartIndex; i <= mStopIndex; i++) {
            Object currentPoint = getItem(i);
            float currentPointX = translateXtoX(getX(i));
            if (mMainDraw != null) {
                Object lastPoint = i == 0 ? currentPoint : getItem(i - 1);
                float lastX = i == 0 ? currentPointX : getX(i - 1 - num);
                if (mMainDraw != null) {
                    mMainDraw.drawTranslated1(lastPoint, currentPoint, lastX, currentPointX, canvas, this, i);
                }
            }
        }
    }

    /**
     * 画文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float textHeight = fm.descent - fm.ascent;
        float baseLine = (textHeight - fm.bottom - fm.top) / 2;
        //--------------画上方k线图的值-------------
        if (mMainDraw != null) {
            canvas.drawText(formatValue(mMainMaxValue), 0, baseLine + mMainRect.top, mTextPaint);
            canvas.drawText(formatValue(mMainMinValue), 0, mMainRect.bottom - textHeight + baseLine, mTextPaint);
            float rowValue = (mMainMaxValue - mMainMinValue) / mGridRows;
            float rowSpace = mMainRect.height() / mGridRows;
            for (int i = 1; i < mGridRows; i++) {
//                String text = formatValue(rowValue * (mGridRows - i) + mMainMinValue);
//                canvas.drawText(text, 0, fixTextY(rowSpace * i + mMainRect.top), mTextPaint);
            }
        }
        //--------------画下方子图的值-------------
//        if (mChildDraw != null) {
//            canvas.drawText(mChildDraw.getValueFormatter().format(mChildMaxValue), 0, mChildRect.top + baseLine, mTextPaint);
//            canvas.drawText(mChildDraw.getValueFormatter().format(mChildMinValue), 0, mChildRect.bottom, mTextPaint);
//        }
//        if (mChildDraw1 != null) {
//            canvas.drawText(mChildDraw1.getValueFormatter().format(mChildMaxValue1), 0, mChildRect1.top + baseLine, mTextPaint);
//            canvas.drawText(mChildDraw1.getValueFormatter().format(mChildMinValue1), 0, mChildRect1.bottom, mTextPaint);
//        }
//        //--------------画时间---------------------
        float columnSpace = mWidth / mGridColumns;
        float y = mChildRect.top + baseLine * 7 / 6 - mTopPadding;
//        if (isTwo) {
//            y = mChildRect1.bottom + baseLine;
//        } else {
//            y = mChildRect.bottom + baseLine;
//        }

        if (num == 0) {
            float startX = getX(mStartIndex) - mPointWidth / 2;
            float stopX = getX(mStopIndex) + mPointWidth / 2;

            for (int i = 1; i < mGridColumns; i++) {
                float translateX = xToTranslateX(columnSpace * i);
                if (translateX >= startX && translateX <= stopX) {
                    int index = indexOfTranslateX(translateX);
                    String text = formatDateTime(mAdapter.getDate(index));
                    if (i == 2) {
                        canvas.drawText(text, columnSpace * i - mTextPaint.measureText(text) / 2, y, mTextPaint);
                    }
                }
            }

            float translateX = xToTranslateX(0);
            if (translateX >= startX && translateX <= stopX) {
                canvas.drawText(formatDateTime(getAdapter().getDate(mStartIndex)), 0, y, mTextPaint);
            }
            translateX = xToTranslateX(mWidth);
            if (translateX >= startX && translateX <= stopX) {
                String text = formatDateTime(getAdapter().getDate(mStopIndex));
                canvas.drawText(text, mWidth - mTextPaint.measureText(text), y, mTextPaint);
            }
        } else {
            float startX = getX(mStartIndex) - mPointWidth * mScaleX / 2 - num * mPointWidth;
            float stopX = getX(mStopIndex) + mPointWidth * mScaleX / 2 - num * mPointWidth;

            for (int i = 1; i < mGridColumns; i++) {
                float translateX = xToTranslateX(columnSpace * i);
                if (translateX >= startX && translateX <= stopX) {
                    int index = indexOfTranslateX(xToTranslateX(columnSpace * i + num * mPointWidth * mScaleX));
                    String text = formatDateTime(mAdapter.getDate(index));
                    if (i == 2) {
                        canvas.drawText(text, columnSpace * i - mTextPaint.measureText(text) / 2, y, mTextPaint);
                    }
                }
            }
            canvas.drawText(formatDateTime(getAdapter().getDate(mStartIndex)), 0, y, mTextPaint);
        }
        if (isLongPress) {
            IKLine point = (IKLine) getItem(mSelectedIndex);
            if (point == null) return;
            String text = formatValue(point.getClosePrice());
            float r = textHeight / 2;
            y = getMainY(point.getClosePrice());
            float x;
//            if (translateXtoX(getX(mSelectedIndex)) < getChartWidth() / 2) {
            x = 0;
            canvas.drawRoundRect(x, y - r, mTextPaint.measureText(text) + 10, y + r, 10, 10, bgPaint);
//            } else {
//                x = mWidth - mTextPaint.measureText(text);
//                canvas.drawRect(x, y - r, mWidth, y + r, mBackgroundPaint);
//            }
            canvas.drawText(text, (mTextPaint.measureText(text) + 10) / 2, fixTextY(y), bgTextPaint);


            if (mAdapter.getItem(mSelectedIndex) != null) {
                float yy = mChildRect.top - textHeight * 3 / 12;
                KLineEntity entity = (KLineEntity) mAdapter.getItem(mSelectedIndex);
                text = getDateTimeFormatter().format(mAdapter.getDate(mSelectedIndex));
                float w = mTextPaint.measureText(text) + 10;
                x = (getSelectedIndex() - mStartIndex) * mPointWidth * mScaleX;
                float a = x - w / 2;
                float b = x + w / 2;
                if (a < 0) {
                    a = 0;
                    b = w;
                }
                if (b > mWidth) {
                    a = mWidth - w;
                    b = mWidth;
                }
                canvas.drawRoundRect(a, mMainRect.bottom + textHeight * 1 / 10, b, mMainRect.bottom + textHeight + textHeight * 1 / 10, 10, 10, bgPaint);
                canvas.drawText(text, (a + b) / 2, yy, bgTextPaint);
            }
        }
    }

    /**
     * 画值
     *
     * @param canvas
     * @param position 显示某个点的值
     */
    private void drawValue(Canvas canvas, int position) {
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float textHeight = fm.descent - fm.ascent;
        float baseLine = (textHeight - fm.bottom - fm.top) / 2;
        if (position >= 0 && position < mItemCount) {
            if (mMainDraw != null) {
                float y = mTopPadding / 6f * 4.6f;
                float x = 0;
                mMainDraw.drawText(canvas, this, position, x, y);
            }
            if (mChildDraw != null) {
                float y = mChildRect.top + baseLine;
//                float x = mTextPaint.measureText(mChildDraw.getValueFormatter().format(mChildMaxValue) + " ");
                mChildDraw.drawText(canvas, this, position, 0, y);
            }

            if (mChildDraw1 != null) {
                float y = mChildRect1.top + baseLine;
//                float x = mTextPaint.measureText(mChildDraw1.getValueFormatter().format(mChildMaxValue1) + " ");
                mChildDraw1.drawText1(canvas, this, position, 0, y);
            }
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
     * 格式化值
     */
    public String formatValue(float value) {
        if (getValueFormatter() == null) {
            setValueFormatter(new ValueFormatter());
        }
        return getValueFormatter().format(value);
    }

    /**
     * 重新计算并刷新线条
     */
    public void notifyChanged() {
        if (mItemCount != 0) {
            mDataLen = (mItemCount - 1) * mPointWidth;
            checkAndFixScrollX();
            setTranslateXFromScrollX(mScrollX);
        } else {
            setScrollX(0);
        }
        invalidate();
    }

    private void calculateSelectedX(float x) {
        mSelectedIndex = indexOfTranslateX(xToTranslateX(x));
        if (mSelectedIndex < mStartIndex) {
            mSelectedIndex = mStartIndex;
        }
        if (mSelectedIndex > mStopIndex) {
            mSelectedIndex = mStopIndex;
        }
    }

    @Override
    public void onLongPress(MotionEvent e) {
        super.onLongPress(e);
        int lastIndex = mSelectedIndex;
        calculateSelectedX(e.getX() + num * mPointWidth * mScaleX);
        if (lastIndex != mSelectedIndex) {
            onSelectedChanged(this, getItem(mSelectedIndex), mSelectedIndex);
        }
        invalidate();

        if (onSelectListener != null) {
            if (mAdapter.getItem(mSelectedIndex) != null) {
                KLineEntity entity = (KLineEntity) mAdapter.getItem(mSelectedIndex);
                onSelectListener.onSelected(entity, true);
            }
        }
    }

    @Override
    public void touchUp() {
        super.touchUp();
    }


    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public OnSelectListener getOnSelectListener() {
        return onSelectListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        setTranslateXFromScrollX(mScrollX);
    }

    @Override
    protected void onScaleChanged(float scale, float oldScale) {
        checkAndFixScrollX();
        setTranslateXFromScrollX(mScrollX);
        super.onScaleChanged(scale, oldScale);
        setNum();
    }

    /**
     * 计算当前的显示区域
     */
    private void calculateValue() {
        if (!isLongPress()) {
            mSelectedIndex = -1;
        }
        mMainMaxValue = Integer.MIN_VALUE;
        mMainMinValue = Integer.MAX_VALUE;
        mChildMaxValue = Integer.MIN_VALUE;
        mChildMinValue = Integer.MAX_VALUE;
        mChildMaxValue1 = Integer.MIN_VALUE;
        mChildMinValue1 = Integer.MAX_VALUE;
        mStartIndex = indexOfTranslateX(xToTranslateX(0));
        mStopIndex = indexOfTranslateX(xToTranslateX(mWidth));
        maxPoint = null;
        minPoint = null;
        for (int i = mStartIndex; i <= mStopIndex; i++) {
            IKLine point = (IKLine) getItem(i);
            if (maxPoint == null || point.getHighPrice() > maxPoint.getHighPrice()) {
                point.setPosition(i);
                maxPoint = point;
            }
            if (minPoint == null || point.getLowPrice() < minPoint.getLowPrice()) {
                point.setPosition(i);
                minPoint = point;
            }
            if (mMainDraw != null) {
                mMainMaxValue = Math.max(mMainMaxValue, mMainDraw.getMaxValue(point));
                mMainMinValue = Math.min(mMainMinValue, mMainDraw.getMinValue(point));
            }
            if (mChildDraw != null) {
                mChildMaxValue = Math.max(mChildMaxValue, mChildDraw.getMaxValue(point));
                mChildMinValue = Math.min(mChildMinValue, mChildDraw.getMinValue(point));
            }

            if (mChildDraw1 != null) {
                mChildMaxValue1 = Math.max(mChildMaxValue1, mChildDraw1.getMaxValue(point));
                mChildMinValue1 = Math.min(mChildMinValue1, mChildDraw1.getMinValue(point));
            }
        }
        if (mMainMaxValue != mMainMinValue) {
            float padding = (mMainMaxValue - mMainMinValue) * 0.05f;
            mMainMaxValue += padding;
            mMainMinValue -= padding;
        } else {
            //当最大值和最小值都相等的时候 分别增大最大值和 减小最小值
            mMainMaxValue += Math.abs(mMainMaxValue * 0.05f);
            mMainMinValue -= Math.abs(mMainMinValue * 0.05f);
            if (mMainMaxValue == 0) {
                mMainMaxValue = 1;
            }
        }

        if (mChildMaxValue == mChildMinValue) {
            //当最大值和最小值都相等的时候 分别增大最大值和 减小最小值
            mChildMaxValue += Math.abs(mChildMaxValue * 0.05f);
            mChildMinValue -= Math.abs(mChildMinValue * 0.05f);
            if (mChildMaxValue == 0) {
                mChildMaxValue = 1;
            }
        }

        if (mChildMaxValue1 == mChildMinValue1) {
            //当最大值和最小值都相等的时候 分别增大最大值和 减小最小值
            mChildMaxValue1 += Math.abs(mChildMaxValue1 * 0.05f);
            mChildMinValue1 -= Math.abs(mChildMinValue1 * 0.05f);
            if (mChildMaxValue1 == 0) {
                mChildMaxValue1 = 1;
            }
        }

        mMainScaleY = mMainRect.height() * 1f / (mMainMaxValue - mMainMinValue);
        mChildScaleY = (mChildRect.height() * 1f - textHeight) / (mChildMaxValue - mChildMinValue);
        mChildScaleY1 = (mChildRect.height() * 1f - textHeight) / (mChildMaxValue1 - mChildMinValue1);
        if (mAnimator.isRunning()) {
            float value = (float) mAnimator.getAnimatedValue();
            mStopIndex = mStartIndex + Math.round(value * (mStopIndex - mStartIndex));
        }
    }

    /**
     * 获取平移的最小值
     *
     * @return
     */
    private float getMinTranslateX() {
        return -mDataLen + mWidth / mScaleX - mPointWidth / 2;
    }

    /**
     * 获取平移的最大值
     *
     * @return
     */
    private float getMaxTranslateX() {
        if (!isFullScreen()) {
            return getMinTranslateX();
        }
        return mPointWidth / 2;
    }

    public float getmPointWidth2() {
        return mPointWidth / 2;
    }

    @Override
    public int getMinScrollX() {
        return (int) -(mOverScrollRange / mScaleX);
    }

    public int getMaxScrollX() {
        return Math.round(getMaxTranslateX() - getMinTranslateX());
    }

    public int indexOfTranslateX(float translateX) {
        return indexOfTranslateX(translateX, 0, mItemCount - 1);
    }

    /**
     * 在主区域画线
     *
     * @param startX    开始点的横坐标
     * @param stopX     开始点的值
     * @param stopX     结束点的横坐标
     * @param stopValue 结束点的值
     */
    public void drawMainLine(Canvas canvas, Paint paint, float startX, float startValue, float stopX, float stopValue) {
        canvas.drawLine(startX, getMainY(startValue), stopX, getMainY(stopValue), paint);
    }

    /**
     * 在子区域画线
     *
     * @param startX     开始点的横坐标
     * @param startValue 开始点的值
     * @param stopX      结束点的横坐标
     * @param stopValue  结束点的值
     */
    public void drawChildLine(Canvas canvas, Paint paint, float startX, float startValue, float stopX, float stopValue) {
        canvas.drawLine(startX, getChildY(startValue), stopX, getChildY(stopValue), paint);
    }

    public void drawChildLine1(Canvas canvas, Paint paint, float startX, float startValue, float stopX, float stopValue) {
        canvas.drawLine(startX, getChildY1(startValue), stopX, getChildY1(stopValue), paint);
    }

    /**
     * 根据索引获取实体
     *
     * @param position 索引值
     * @return
     */
    public Object getItem(int position) {
        if (mAdapter != null && position < mAdapter.getCount()) {
            return mAdapter.getItem(position);
        } else {
            return null;
        }
    }

    /**
     * 根据索引索取x坐标
     *
     * @param position 索引值
     * @return
     */
    public float getX(float position) {
        return position * mPointWidth;
    }

    /**
     * 获取适配器
     *
     * @return
     */
    public IAdapter getAdapter() {
        return mAdapter;
    }

    /**
     * 设置子图的绘制方法
     *
     * @param
     */
    private void setChildDraw() {
        this.mChildDraw = mChildDraws.get(mChildDrawPosition);
        invalidate();
    }

    private void setChildDraw1() {
        if (isTwo && mChildDraws1.size() > 0) {
            this.mChildDraw1 = mChildDraws1.get(mChildDrawPosition1);
        }
        invalidate();
    }

    /**
     * 设置子图的绘制方法
     *
     * @param
     */
    private void setChildDrawClick() {
        mChildDrawPosition++;
        this.mChildDraw = mChildDraws.get(mChildDrawPosition % mChildDraws.size());
        invalidate();
    }

    private void setChildDraw1Click() {
        if (isTwo && mChildDraws1.size() > 0) {
            mChildDrawPosition1++;
            this.mChildDraw1 = mChildDraws1.get(mChildDrawPosition1 % mChildDraws1.size());
        }
        invalidate();
    }

    /**
     * 给子区域添加画图方法
     *
     * @param name      显示的文字标签
     * @param childDraw IChartDraw
     */
    public void addChildDraw(String name, IChartDraw childDraw) {
        mChildDraws.add(childDraw);
        mKChartTabView.addTab(name);
    }


    /**
     * 给子区域添加画图方法
     *
     * @param name      显示的文字标签
     * @param childDraw IChartDraw
     */
    public void addChildDraw1(String name, IChartDraw childDraw) {
        mChildDraws1.add(childDraw);
        mKChartTabView1.addTab(name, 3);
    }

    /**
     * scrollX 转换为 TranslateX
     *
     * @param scrollX
     */
    private void setTranslateXFromScrollX(int scrollX) {
        mTranslateX = scrollX + getMinTranslateX();
    }

    /**
     * 获取ValueFormatter
     *
     * @return
     */
    public IValueFormatter getValueFormatter() {
        return mValueFormatter;
    }

    /**
     * 设置ValueFormatter
     *
     * @param valueFormatter value格式化器
     */
    public void setValueFormatter(IValueFormatter valueFormatter) {
        this.mValueFormatter = valueFormatter;
    }

    /**
     * 获取DatetimeFormatter
     *
     * @return 时间格式化器
     */
    public IDateTimeFormatter getDateTimeFormatter() {
        return mDateTimeFormatter;
    }

    /**
     * 设置dateTimeFormatter
     *
     * @param dateTimeFormatter 时间格式化器
     */
    public void setDateTimeFormatter(IDateTimeFormatter dateTimeFormatter) {
        mDateTimeFormatter = dateTimeFormatter;
    }

    /**
     * 格式化时间
     *
     * @param date
     */
    public String formatDateTime(Date date) {
        if (getDateTimeFormatter() == null) {
            setDateTimeFormatter(new TimeFormatter());
        }
        return getDateTimeFormatter().format(date);
    }

    /**
     * 获取主区域的 IChartDraw
     *
     * @return IChartDraw
     */
    public IChartDraw getMainDraw() {
        return mMainDraw;
    }

    /**
     * 设置主区域的 IChartDraw
     *
     * @param mainDraw IChartDraw
     */
    public void setMainDraw(IChartDraw mainDraw) {
        mMainDraw = mainDraw;
    }

    /**
     * 二分查找当前值的index
     *
     * @return
     */
    public int indexOfTranslateX(float translateX, int start, int end) {
        if (end == start) {
            return start;
        }
        if (end - start == 1) {
            float startValue = getX(start);
            float endValue = getX(end);
            return Math.abs(translateX - startValue) < Math.abs(translateX - endValue) ? start : end;
        }
        int mid = start + (end - start) / 2;
        float midValue = getX(mid);
        if (translateX < midValue) {
            return indexOfTranslateX(translateX, start, mid);
        } else if (translateX > midValue) {
            return indexOfTranslateX(translateX, mid, end);
        } else {
            return mid;
        }
    }

    /**
     * 设置数据适配器
     */
    public void setAdapter(IAdapter adapter) {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }
        mAdapter = adapter;
        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(mDataSetObserver);
            mItemCount = mAdapter.getCount();
        } else {
            mItemCount = 0;
        }
        notifyChanged();
    }

    /**
     * 开始动画
     */
    public void startAnimation() {
        if (mAnimator != null) {
            mAnimator.start();
        }
    }

    /**
     * 设置动画时间
     */
    public void setAnimationDuration(long duration) {
        if (mAnimator != null) {
            mAnimator.setDuration(duration);
        }
    }

    /**
     * 设置表格行数
     */
    public void setGridRows(int gridRows) {
        if (gridRows < 1) {
            gridRows = 1;
        }
        mGridRows = gridRows;
    }

    /**
     * 设置表格列数
     */
    public void setGridColumns(int gridColumns) {
        if (gridColumns < 1) {
            gridColumns = 1;
        }
        mGridColumns = gridColumns;
    }

    /**
     * view中的x转化为TranslateX
     *
     * @param x
     * @return
     */
    public float xToTranslateX(float x) {
        return -mTranslateX + x / mScaleX;
    }

    /**
     * translateX转化为view中的x
     *
     * @param translateX
     * @return
     */
    public float translateXtoX(float translateX) {
        return (translateX + mTranslateX) * mScaleX - num * mPointWidth * mScaleX;
    }

    /**
     * 获取上方padding
     */
    public float getTopPadding() {
        return mTopPadding;
    }

    /**
     * 获取图的宽度
     *
     * @return
     */
    public int getChartWidth() {
        return mWidth;
    }

    /**
     * 是否长按
     */
    public boolean isLongPress() {
        return isLongPress;
    }

    /**
     * 获取选择索引
     */
    public int getSelectedIndex() {
        return mSelectedIndex;
    }

    public Rect getChildRect() {
        return mChildRect;
    }

    public Rect getmMainRect() {
        return mMainRect;
    }

    public Rect getmChildRect1() {
        return mChildRect1;
    }

    /**
     * 设置选择监听
     */
    public void setOnSelectedChangedListener(OnSelectedChangedListener l) {
        this.mOnSelectedChangedListener = l;
    }

    public void onSelectedChanged(BaseKChartView view, Object point, int index) {
        if (this.mOnSelectedChangedListener != null) {
            mOnSelectedChangedListener.onSelectedChanged(view, point, index);
        }
    }

    /**
     * 数据是否充满屏幕
     *
     * @return
     */
    public boolean isFullScreen() {
        return mDataLen >= mWidth / mScaleX;
    }

    /**
     * 设置超出右方后可滑动的范围
     */
    public void setOverScrollRange(float overScrollRange) {
        if (overScrollRange < 0) {
            overScrollRange = 0;
        }
        mOverScrollRange = overScrollRange;
    }

    /**
     * 设置上方padding
     *
     * @param topPadding
     */
    public void setTopPadding(int topPadding) {
        mTopPadding = topPadding;
    }

    /**
     * 设置下方padding
     *
     * @param bottomPadding
     */
    public void setBottomPadding(int bottomPadding) {
        mBottomPadding = bottomPadding;
    }

    /**
     * 设置表格线宽度
     */
    public void setGridLineWidth(float width) {
        mGridPaint.setStrokeWidth(1);
    }

    /**
     * 设置表格线颜色
     */
    public void setGridLineColor(int color) {
        mGridPaint.setColor(0xfff0f0f0);
    }

    /**
     * 设置选择线宽度
     */
    public void setSelectedLineWidth(float width) {
        mSelectedLinePaint.setStrokeWidth(width);
    }

    /**
     * 设置表格线颜色
     */
    public void setSelectedLineColor(int color) {
        mSelectedLinePaint.setColor(color);
    }

    /**
     * 设置文字颜色
     */
    public void setTextColor(int color) {
        mTextPaint.setColor(color);
    }

    /**
     * 设置文字大小
     */
    public void setTextSize(float textSize) {
        mTextPaint.setTextSize(textSize);
        mTextBlackPaint.setTextSize(textSize);
    }

    /**
     * 设置背景颜色
     */
    public void setBackgroundColor(int color) {
        mBackgroundPaint.setColor(color);
    }


    /**
     * 选中点变化时的监听
     */
    public interface OnSelectedChangedListener {
        /**
         * 当选点中变化时
         *
         * @param view  当前view
         * @param point 选中的点
         * @param index 选中点的索引
         */
        void onSelectedChanged(BaseKChartView view, Object point, int index);
    }

    public interface OnSelectListener {
        void onSelected(Object point, boolean isSelect);
    }

    /**
     * 获取文字大小
     */
    public float getTextSize() {
        return mTextPaint.getTextSize();
    }

    /**
     * 获取曲线宽度
     */
    public float getLineWidth() {
        return mLineWidth;
    }

    /**
     * 设置曲线的宽度
     */
    public void setLineWidth(float lineWidth) {
        mLineWidth = lineWidth;
    }

    /**
     * 设置每个点的宽度
     */
    public void setPointWidth(float pointWidth) {
        mPointWidth = pointWidth;
    }

    public Paint getGridPaint() {
        return mGridPaint;
    }

    public Paint getTextPaint() {
        return mTextPaint;
    }

    public Paint getBackgroundPaint() {
        return mBackgroundPaint;
    }

    public Paint getSelectedLinePaint() {
        return mSelectedLinePaint;
    }


    private void setNum() {
        cloumNum = mWidth / mPointWidth / mScaleX;
        if (cloumNum > mItemCount) {
            Log.e("setPointWidth: ", cloumNum + "");
            num = cloumNum - mItemCount;
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    setScrollEnable(mItemCount >= requestNum);
                }
            });
        } else {
            num = 0;
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    setScrollEnable(true);
                }
            });
        }
        notifyChanged();
    }

    public void setRequestNum(int requestNum) {
        this.requestNum = requestNum;
    }

    public boolean isRight(float x) {
        return x - num * mPointWidth * mScaleX > mWidth / 2;
    }


    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (getChildRect() != null) {
            if (e.getY() > getChildRect().top && e.getY() < getChildRect().bottom) {
                setChildDrawClick();
            }
        }
        if (getmChildRect1() != null) {
            if (e.getY() > getmChildRect1().top && e.getY() < getmChildRect1().bottom) {
                setChildDraw1Click();
            }
        }
        if (onSelectListener != null) {
            onSelectListener.onSelected(null, false);
        }
        return super.onSingleTapUp(e);
    }

    public Paint getmTextBlackPaint() {
        return mTextBlackPaint;
    }


    public IKLine getMaxPoint() {
        return maxPoint;
    }

    public IKLine getMinPoint() {
        return minPoint;
    }


    public void setLongPressFalse() {
        isLongPress = false;
        if (onSelectListener != null) {
            onSelectListener.onSelected(null, false);
        }
    }


    public float getmChildMaxValue() {
        return mChildMaxValue;
    }


    public float getmChildMinValue() {
        return mChildMinValue;
    }

    public float getmChildMaxValue1() {
        return mChildMaxValue1;
    }

    public void setmChildMaxValue1(float mChildMaxValue1) {
        this.mChildMaxValue1 = mChildMaxValue1;
    }

    public float getmChildMinValue1() {
        return mChildMinValue1;
    }

    public void setmChildMinValue1(float mChildMinValue1) {
        this.mChildMinValue1 = mChildMinValue1;
    }
}
