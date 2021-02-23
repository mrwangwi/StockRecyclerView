package com.github.tifezh.kchartlib.chart;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.core.content.ContextCompat;

import com.github.tifezh.kchartlib.R;
import com.github.tifezh.kchartlib.chart.draw.BOLLDraw;
import com.github.tifezh.kchartlib.chart.draw.BalanceDraw;
import com.github.tifezh.kchartlib.chart.draw.KDJDraw;
import com.github.tifezh.kchartlib.chart.draw.MACDDraw;
import com.github.tifezh.kchartlib.chart.draw.MainDraw;
import com.github.tifezh.kchartlib.chart.draw.RSIDraw;
import com.github.tifezh.kchartlib.chart.draw.VolumeDraw;

/**
 * k线图
 * Created by tian on 2016/5/20.
 */
public class KChartView extends BaseKChartView {

    ProgressBar mProgressBar;
    private boolean isRefreshing = false;
    private boolean isLoadMoreEnd = false;
    private boolean mLastScrollEnable;
    private boolean mLastScaleEnable;

    private KChartRefreshListener mRefreshListener;

    private MACDDraw mMACDDraw;
    private BOLLDraw mBOLLDraw;
    private RSIDraw mRSIDraw;
    private MainDraw mMainDraw;
    private KDJDraw mKDJDraw;
    private VolumeDraw mVolumeDraw;
    private BalanceDraw balanceDraw;

    private String kLineType;

    private MACDDraw mMACDDraw1;
    private BOLLDraw mBOLLDraw1;
    private RSIDraw mRSIDraw1;
    private KDJDraw mKDJDraw1;
    private VolumeDraw mVolumeDraw1;
    private BalanceDraw balanceDraw1;

    public String getkLineType() {
        return kLineType;
    }

    public void setkLineType(String kLineType) {
        this.kLineType = kLineType;
    }

    public KChartView(Context context) {
        this(context, null);
    }

    public KChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initAttrs(attrs);
    }

    private void initView() {
        mProgressBar = new ProgressBar(getContext());
        LayoutParams layoutParams = new LayoutParams(dp2px(50), dp2px(50));
        layoutParams.addRule(CENTER_IN_PARENT);
        addView(mProgressBar, layoutParams);
        mProgressBar.setVisibility(GONE);
        mVolumeDraw = new VolumeDraw(this);
        mMACDDraw = new MACDDraw(this);
        mKDJDraw = new KDJDraw(this);
        mRSIDraw = new RSIDraw(this);
        mBOLLDraw = new BOLLDraw(this);
        mMainDraw = new MainDraw(this);
        balanceDraw = new BalanceDraw(this);
        addChildDraw("VOL", mVolumeDraw);
        addChildDraw("BOLL", balanceDraw);
        addChildDraw("KDJ", mKDJDraw);
        addChildDraw("MACD", mMACDDraw);
        addChildDraw("RSI", mRSIDraw);
        if (isTwo) {
            mVolumeDraw1 = new VolumeDraw(this);
            mMACDDraw1 = new MACDDraw(this);
            mKDJDraw1 = new KDJDraw(this);
            mRSIDraw1 = new RSIDraw(this);
            mBOLLDraw1 = new BOLLDraw(this);
            balanceDraw1 = new BalanceDraw(this);
            addChildDraw1("VOL", mVolumeDraw1);
            addChildDraw1("BOLL", balanceDraw1);
            addChildDraw1("KDJ", mKDJDraw1);
            addChildDraw1("MACD", mMACDDraw1);
            addChildDraw1("RSI", mRSIDraw1);
        }
        setMainDraw(mMainDraw);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.KChartView);
        if (array != null) {
            try {
                //public
                setPointWidth(array.getDimension(R.styleable.KChartView_kc_point_width, getDimension(R.dimen.chart_point_width)));
                setTextSize(array.getDimension(R.styleable.KChartView_kc_text_size, getDimension(R.dimen.chart_text_size)));
                setTextColor(array.getColor(R.styleable.KChartView_kc_text_color, getColor(R.color.chart_text)));
                setLineWidth(array.getDimension(R.styleable.KChartView_kc_line_width, getDimension(R.dimen.chart_line_width)));
                setBackgroundColor(array.getColor(R.styleable.KChartView_kc_background_color, getColor(R.color.chart_background)));
                setSelectedLineColor(array.getColor(R.styleable.KChartView_kc_selected_line_color, getColor(R.color.chart_text)));
                setSelectedLineWidth(array.getDimension(R.styleable.KChartView_kc_selected_line_width, getDimension(R.dimen.chart_line_width)));
                setGridLineWidth(array.getDimension(R.styleable.KChartView_kc_grid_line_width, getDimension(R.dimen.chart_grid_line_width)));
                setGridLineColor(array.getColor(R.styleable.KChartView_kc_grid_line_color, getColor(R.color.chart_grid_line)));
                //macd
                setMACDWidth(array.getDimension(R.styleable.KChartView_kc_macd_width, getDimension(R.dimen.chart_candle_width)));
                setDIFColor(array.getColor(R.styleable.KChartView_kc_dif_color, getColor(R.color.chart_ma5)));
                setDEAColor(array.getColor(R.styleable.KChartView_kc_dea_color, getColor(R.color.chart_ma10)));
                setMACDColor(array.getColor(R.styleable.KChartView_kc_macd_color, getColor(R.color.chart_ma20)));
                //kdj
                setKColor(array.getColor(R.styleable.KChartView_kc_dif_color, getColor(R.color.chart_ma5)));
                setDColor(array.getColor(R.styleable.KChartView_kc_dea_color, getColor(R.color.chart_ma10)));
                setJColor(array.getColor(R.styleable.KChartView_kc_macd_color, getColor(R.color.chart_ma20)));
                //rsi
                setRSI1Color(array.getColor(R.styleable.KChartView_kc_dif_color, getColor(R.color.chart_ma5)));
                setRSI2Color(array.getColor(R.styleable.KChartView_kc_dea_color, getColor(R.color.chart_ma10)));
                setRSI3Color(array.getColor(R.styleable.KChartView_kc_macd_color, getColor(R.color.chart_ma20)));
                //boll
                setUpColor(array.getColor(R.styleable.KChartView_kc_dif_color, getColor(R.color.chart_ma5)));
                setMbColor(array.getColor(R.styleable.KChartView_kc_dea_color, getColor(R.color.chart_ma10)));
                setDnColor(array.getColor(R.styleable.KChartView_kc_macd_color, getColor(R.color.chart_ma20)));
                //main
                setMa5Color(array.getColor(R.styleable.KChartView_kc_dif_color, getColor(R.color.chart_ma5)));
                setMa10Color(array.getColor(R.styleable.KChartView_kc_dea_color, getColor(R.color.chart_ma10)));
                setMa20Color(array.getColor(R.styleable.KChartView_kc_macd_color, getColor(R.color.chart_ma20)));
                setMa30Color(array.getColor(R.styleable.KChartView_kc_macd_color1, getColor(R.color.chart_ma30)));
                setCandleWidth(array.getDimension(R.styleable.KChartView_kc_candle_width, getDimension(R.dimen.chart_candle_width)));
                setCandleLineWidth(array.getDimension(R.styleable.KChartView_kc_candle_line_width, getDimension(R.dimen.chart_candle_line_width)));
                setSelectorBackgroundColor(array.getColor(R.styleable.KChartView_kc_selector_background_color, getColor(R.color.chart_selector)));
                setSelectorTextSize(array.getDimension(R.styleable.KChartView_kc_selector_text_size, getDimension(R.dimen.chart_selector_text_size)));
                setCandleSolid(array.getBoolean(R.styleable.KChartView_kc_candle_solid, true));
                //tab
                mKChartTabView.setIndicatorColor(array.getColor(R.styleable.KChartView_kc_tab_indicator_color, getColor(R.color.chart_tab_indicator)));
                mKChartTabView.setBackgroundColor(array.getColor(R.styleable.KChartView_kc_tab_background_color, getColor(R.color.chart_tab_background)));
                ColorStateList colorStateList = array.getColorStateList(R.styleable.KChartView_kc_tab_text_color);
                if (colorStateList == null) {
                    mKChartTabView.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.tab_text_color_selector));
                } else {
                    mKChartTabView.setTextColor(colorStateList);
                }

                if (mKChartTabView1 != null) {
                    mKChartTabView1.setIndicatorColor(array.getColor(R.styleable.KChartView_kc_tab_indicator_color, getColor(R.color.chart_tab_indicator)));
                    mKChartTabView1.setBackgroundColor(array.getColor(R.styleable.KChartView_kc_tab_background_color, getColor(R.color.chart_tab_background)));
                    if (colorStateList == null) {
                        mKChartTabView1.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.tab_text_color_selector));
                    } else {
                        mKChartTabView1.setTextColor(colorStateList);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                array.recycle();
            }
        }
    }

    private float getDimension(@DimenRes int resId) {
        return getResources().getDimension(resId);
    }

    private int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(getContext(), resId);
    }

    @Override
    public void onLeftSide() {
        showLoading();
    }

    @Override
    public void onRightSide() {
    }

    public void showLoading() {
        if (!isLoadMoreEnd && !isRefreshing) {
            isRefreshing = true;
            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
            if (mRefreshListener != null) {
                mRefreshListener.onLoadMoreBegin(this);
            }
            mLastScaleEnable = isScaleEnable();
            mLastScrollEnable = isScrollEnable();
            super.setScrollEnable(false);
            super.setScaleEnable(false);
        }
    }

    private void hideLoading() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
        super.setScrollEnable(mLastScrollEnable);
        super.setScaleEnable(mLastScaleEnable);
    }

    /**
     * 刷新完成
     */
    public void refreshComplete() {
        isRefreshing = false;
        hideLoading();
    }

    /**
     * 刷新完成，没有数据
     */
    public void refreshEnd() {
        isLoadMoreEnd = true;
        isRefreshing = false;
        hideLoading();
    }

    /**
     * 重置加载更多
     */
    public void resetLoadMoreEnd() {
        isLoadMoreEnd = false;
    }

    public interface KChartRefreshListener {
        /**
         * 加载更多
         *
         * @param chart
         */
        void onLoadMoreBegin(KChartView chart);
    }

    @Override
    public void setScaleEnable(boolean scaleEnable) {
        if (isRefreshing) {
            return;
//            throw new IllegalStateException("请勿在刷新状态设置属性");
        }
        super.setScaleEnable(scaleEnable);

    }

    @Override
    public void setScrollEnable(boolean scrollEnable) {
        if (isRefreshing) {
            return;
//            throw new IllegalStateException("请勿在刷新状态设置属性");
        }
        super.setScrollEnable(scrollEnable);
    }

    /**
     * 设置DIF颜色
     */
    public void setDIFColor(int color) {
        mMACDDraw.setDIFColor(color);
        if (isTwo) {
            mMACDDraw1.setDIFColor(color);
        }
    }

    /**
     * 设置DEA颜色
     */
    public void setDEAColor(int color) {
        mMACDDraw.setDEAColor(color);
        if (isTwo) {
            mMACDDraw1.setDEAColor(color);
        }
    }

    /**
     * 设置MACD颜色
     */
    public void setMACDColor(int color) {
        mMACDDraw.setMACDColor(color);
        if (isTwo) {
            mMACDDraw1.setMACDColor(color);
        }
    }

    /**
     * 设置MACD的宽度
     *
     * @param MACDWidth
     */
    public void setMACDWidth(float MACDWidth) {
        mMACDDraw.setMACDWidth(MACDWidth);
        if (isTwo) {
            mMACDDraw1.setMACDWidth(MACDWidth);
        }
    }

    /**
     * 设置up颜色
     */
    public void setUpColor(int color) {
        mBOLLDraw.setUpColor(color);
        if (isTwo) {
            mBOLLDraw1.setUpColor(color);
        }
    }

    /**
     * 设置mb颜色
     *
     * @param color
     */
    public void setMbColor(int color) {
        mBOLLDraw.setMbColor(color);
        if (isTwo) {
            mBOLLDraw1.setMbColor(color);
        }
    }

    /**
     * 设置dn颜色
     */
    public void setDnColor(int color) {
        mBOLLDraw.setDnColor(color);
        if (isTwo) {
            mBOLLDraw1.setDnColor(color);
        }
    }

    /**
     * 设置K颜色
     */
    public void setKColor(int color) {
        mKDJDraw.setKColor(color);
        if (isTwo) {
            mKDJDraw1.setKColor(color);
        }
    }

    /**
     * 设置D颜色
     */
    public void setDColor(int color) {
        mKDJDraw.setDColor(color);
        if (isTwo) {
            mKDJDraw1.setDColor(color);
        }
    }

    /**
     * 设置J颜色
     */
    public void setJColor(int color) {
        mKDJDraw.setJColor(color);
        if (isTwo) {
            mKDJDraw1.setJColor(color);
        }
    }

    /**
     * 设置ma5颜色
     *
     * @param color
     */
    public void setMa5Color(int color) {
        mMainDraw.setMa5Color(color);
        mVolumeDraw.setMa5Color(color);
        balanceDraw.setMa5Color(color);
        if (isTwo) {
            mVolumeDraw1.setMa5Color(color);
            balanceDraw1.setMa5Color(color);
        }
    }

    /**
     * 设置ma10颜色
     *
     * @param color
     */
    public void setMa10Color(int color) {
        mMainDraw.setMa10Color(color);
        mVolumeDraw.setMa10Color(color);
        balanceDraw.setMa10Color(color);
        if (isTwo) {
            mVolumeDraw1.setMa10Color(color);
            balanceDraw1.setMa10Color(color);
        }
    }

    /**
     * 设置ma20颜色
     *
     * @param color
     */
    public void setMa20Color(int color) {
        mMainDraw.setMa20Color(color);
    }

    /**
     * 设置ma20颜色
     *
     * @param color
     */
    public void setMa30Color(int color) {
        mMainDraw.setMa30Color(color);
    }

    /**
     * 设置选择器文字大小
     *
     * @param textSize
     */
    public void setSelectorTextSize(float textSize) {
        mMainDraw.setSelectorTextSize(textSize);
    }

    /**
     * 设置选择器背景
     *
     * @param color
     */
    public void setSelectorBackgroundColor(int color) {
        mMainDraw.setSelectorBackgroundColor(color);
    }

    /**
     * 设置蜡烛宽度
     *
     * @param candleWidth
     */
    public void setCandleWidth(float candleWidth) {
        mMainDraw.setCandleWidth(candleWidth);
    }

    /**
     * 设置蜡烛线宽度
     *
     * @param candleLineWidth
     */
    public void setCandleLineWidth(float candleLineWidth) {
        mMainDraw.setCandleLineWidth(candleLineWidth);
    }

    /**
     * 蜡烛是否空心
     */
    public void setCandleSolid(boolean candleSolid) {
        mMainDraw.setCandleSolid(candleSolid);
    }

    public void setRSI1Color(int color) {
        mRSIDraw.setRSI1Color(color);
        if (isTwo) {
            mRSIDraw1.setRSI1Color(color);
        }
    }

    public void setRSI2Color(int color) {
        mRSIDraw.setRSI2Color(color);
        if (isTwo) {
            mRSIDraw1.setRSI2Color(color);
        }
    }

    public void setRSI3Color(int color) {
        mRSIDraw.setRSI3Color(color);
        if (isTwo) {
            mRSIDraw1.setRSI3Color(color);
        }
    }

    @Override
    public void setTextSize(float textSize) {
        super.setTextSize(textSize);
        mMainDraw.setTextSize(textSize);
        mBOLLDraw.setTextSize(textSize);
        mRSIDraw.setTextSize(textSize);
        mMACDDraw.setTextSize(textSize);
        mKDJDraw.setTextSize(textSize);
        mVolumeDraw.setTextSize(textSize);
        balanceDraw.setTextSize(textSize);
        if (isTwo) {
            mBOLLDraw1.setTextSize(textSize);
            mRSIDraw1.setTextSize(textSize);
            mMACDDraw1.setTextSize(textSize);
            mKDJDraw1.setTextSize(textSize);
            mVolumeDraw1.setTextSize(textSize);
            balanceDraw1.setTextSize(textSize);
        }
    }

    @Override
    public void setLineWidth(float lineWidth) {
        super.setLineWidth(lineWidth);
        mMainDraw.setLineWidth(lineWidth);
        mBOLLDraw.setLineWidth(lineWidth);
        mRSIDraw.setLineWidth(lineWidth);
        mMACDDraw.setLineWidth(lineWidth);
        mKDJDraw.setLineWidth(lineWidth);
        mVolumeDraw.setLineWidth(lineWidth);
        balanceDraw.setLineWidth(lineWidth);
        if (isTwo) {
            mBOLLDraw1.setLineWidth(lineWidth);
            mRSIDraw1.setLineWidth(lineWidth);
            mMACDDraw1.setLineWidth(lineWidth);
            mKDJDraw1.setLineWidth(lineWidth);
            mVolumeDraw1.setLineWidth(lineWidth);
            balanceDraw1.setLineWidth(lineWidth);
        }
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        mMainDraw.setSelectorTextColor(color);
    }

    /**
     * 设置刷新监听
     */
    public void setRefreshListener(KChartRefreshListener refreshListener) {
        mRefreshListener = refreshListener;
    }
}
