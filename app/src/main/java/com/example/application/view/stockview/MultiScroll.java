package com.example.application.view.stockview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Scroller;

import androidx.recyclerview.widget.RecyclerView;

public class MultiScroll extends HorizontalScrollView {

    private static final int ANIMATION_SCREEN_SET_DURATION_MILLIS = 500;
    // What fraction (1/x) of the screen the user must swipe to indicate a page change
    private static final int FRACTION_OF_SCREEN_WIDTH_FOR_SWIPE = 4;
    private static final int INVALID_SCREEN = -1;
    /*
     * Velocity of a swipe (in density-independent pixels per second) to force a swipe to the
     * next/previous screen. Adjusted into mDensityAdjustedSnapVelocity on init.
     */
    private static final int SNAP_VELOCITY_DIP_PER_SECOND = 600;
    // Argument to getVelocity for units to give pixels per second (1 = pixels per millisecond).
    private static final int VELOCITY_UNIT_PIXELS_PER_SECOND = 1000;

    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_HORIZONTAL_SCROLLING = 1;
    private static final int TOUCH_STATE_VERTICAL_SCROLLING = -1;
    private int mCurrentScreen;
    private int mDensityAdjustedSnapVelocity;
    private boolean mFirstLayout = true;
    private float mLastMotionX;
    private float mLastMotionY;
    //private OnScreenSwitchListener mOnScreenSwitchListener;
    private int mMaximumVelocity;
    private int mNextScreen = INVALID_SCREEN;
    private Scroller mScroller;
    private int mTouchSlop;
    private int mTouchState = TOUCH_STATE_REST;
    private VelocityTracker mVelocityTracker;
    private int mLastSeenLayoutWidth = -1;

    RecyclerView recyclerView;

    MyShadowView myShadowView;

    ImageView ivLeft;
    ImageView ivRight;


    private int screenWidth;
    private int childWidth;


    private int left, right, size;

    public MultiScroll(Context context) {
        super(context);
        init(context);
    }

    public MultiScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public MultiScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        mScroller = new Scroller(getContext());
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        // Calculate the density-dependent snap velocity in pixels
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(displayMetrics);
        mDensityAdjustedSnapVelocity =
                (int) (displayMetrics.density * SNAP_VELOCITY_DIP_PER_SECOND);

        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
    }


    @Override
    public boolean onInterceptTouchEvent(final MotionEvent ev) {
        /*
         * By Yoni Samlan: Modified onInterceptTouchEvent based on standard ScrollView's
         * onIntercept. The logic is designed to support a nested vertically scrolling view inside
         * this one; once a scroll registers for X-wise scrolling, handle it in this view and don't
         * let the children, but once a scroll registers for y-wise scrolling, let the children
         * handle it exclusively.
         */
        final int action = ev.getAction();
        boolean intercept = false;

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                /*
                 * If we're in a horizontal scroll event, take it (intercept further events). But if
                 * we're mid-vertical-scroll, don't even try; let the children deal with it. If we
                 * haven't found a scroll event yet, check for one.
                 */
                if (mTouchState == TOUCH_STATE_HORIZONTAL_SCROLLING) {
                    // Let children handle the events for the duration of the scroll event.
//                    intercept = true;
                } else if (mTouchState == TOUCH_STATE_VERTICAL_SCROLLING) {
                    /*
                     * We've already started a horizontal scroll; set intercept to true so we can
                     * take the remainder of all touch events in onTouchEvent.
                     */
//                    intercept = false;
                } else { // We haven't picked up a scroll event yet; check for one.

                    /*
                     * If we detected a horizontal scroll event, start stealing touch events (mark
                     * as scrolling). Otherwise, see if we had a vertical scroll event -- if so, let
                     * the children handle it and don't look to intercept again until the motion is
                     * done.
                     */

                    final float x = ev.getX();
                    final int xDiff = (int) Math.abs(x - mLastMotionX);
                    boolean xMoved = xDiff > mTouchSlop;

                    final float y = ev.getY();
                    final int yDiff = (int) Math.abs(y - mLastMotionY);
                    boolean yMoved = yDiff > mTouchSlop;


                    if (xMoved) {
                        // Scroll if the user moved far enough along the X axis
                        if (xDiff >= yDiff)
                            mTouchState = TOUCH_STATE_HORIZONTAL_SCROLLING;
                        mLastMotionX = x;
                    }


                    if (yMoved) {
                        if (yDiff > xDiff)
                            mTouchState = TOUCH_STATE_VERTICAL_SCROLLING;
                        mLastMotionY = y;
                    }
                }

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // Release the drag.
                mTouchState = TOUCH_STATE_REST;
//                intercept = false;
                break;
            case MotionEvent.ACTION_DOWN:
                /*
                 * No motion yet, but register the coordinates so we can check for intercept at the
                 * next MOVE event.
                 */
                //Log.i("ViewPager-->", "Action_Down");
                mTouchState = TOUCH_STATE_REST;
                mLastMotionY = ev.getY();
                mLastMotionX = ev.getX();
                break;
            default:
                break;
        }

        if (recyclerView != null) {
            if (mTouchState == -1) {
                recyclerView.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            } else if (mTouchState == 1) {
                recyclerView.getParent().requestDisallowInterceptTouchEvent(false);
                return true;
            }
        }

        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        final int action = ev.getAction();

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                this.scrollType = ScrollType.TOUCH_SCROLL;
                //手指在上面移动的时候   取消滚动监听线程
                mHandler.removeCallbacks(scrollRunnable);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                //手指移动的时候
                mHandler.post(scrollRunnable);
                break;
        }


        return super.onTouchEvent(ev);
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (myShadowView != null) {
            myShadowView.start();
        }
        ivLeft.setVisibility(GONE);
        ivRight.setVisibility(GONE);
    }


    public void setShadowView(MyShadowView myShadowView) {
        this.myShadowView = myShadowView;
    }


    private Handler mHandler = new Handler();

    /**
     * 滚动状态   IDLE 滚动停止  TOUCH_SCROLL 手指拖动滚动         FLING滚动
     *
     * @author DZC
     * @version XHorizontalScrollViewgallery
     * @Time 2014-12-7 上午11:06:52
     */
    enum ScrollType {IDLE, TOUCH_SCROLL, FLING}

    ;

    /**
     * 记录当前滚动的距离
     */
    private int currentX = -9999999;
    /**
     * 当前滚动状态
     */
    private ScrollType scrollType = ScrollType.IDLE;
    /**
     * 滚动监听间隔
     */
    private int scrollDealy = 50;
    /**
     * 滚动监听runnable
     */
    private Runnable scrollRunnable = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (getScrollX() == currentX) {
                //滚动停止  取消监听线程
                int distance = getScrollX() % childWidth;
                if (distance != 0) {
                    int clum = getScrollX() / childWidth;
                    int sec = getScrollX() % childWidth;
                    if (sec > childWidth / 2) {
                        clum++;
                    }
                    if (getScrollX() % childWidth > 0) {
                        final int num = clum;
                        new Handler().postDelayed(() -> smoothScrollTo(num * childWidth, 0), 0);
                    }
                    mHandler.post(scrollRunnable);
                } else {
                    if (myShadowView != null) {
                        myShadowView.finish();
                    }
                    scrollType = ScrollType.IDLE;
                    ivLeft.setVisibility(getScrollX() >= childWidth / 2 ? VISIBLE : GONE);
                    ivRight.setVisibility(getScrollX() <= ((size - 1 - right) * childWidth + childWidth / 2) ? VISIBLE : GONE);
                }
                return;
            } else {
                //手指离开屏幕    view还在滚动的时候
                scrollType = ScrollType.FLING;
            }
            currentX = getScrollX();
            mHandler.postDelayed(this, scrollDealy);
        }
    };

    public void setWeight(int left, int right) {
        this.left = left;
        this.right = right;
        childWidth = screenWidth / (left + right);
    }

    public void setImageView(ImageView left, ImageView right) {
        this.ivLeft = left;
        this.ivRight = right;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
