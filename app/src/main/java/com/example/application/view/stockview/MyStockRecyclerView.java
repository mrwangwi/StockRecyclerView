package com.example.application.view.stockview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.ShortSelectBean;
import com.example.application.StockBean;
import com.example.application.adapter.LeftAdapter;
import com.example.application.adapter.RightAdapter;
import com.example.application.adapter.RyTagAdapter;
import com.example.application.adapter.TableAdapter;
import com.example.application.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

public class MyStockRecyclerView extends LinearLayout implements View.OnClickListener, RyTagAdapter.Click {


    private ShortSelectBean shortType = new ShortSelectBean("pxChangeRate", "涨幅榜");
    private MyShadowView llLeft;
    private ConstraintLayout llRight;
    private RecyclerView recyclerTop;
    private RecyclerView recyclerRight;
    private MyRecyclerView recyclerLeft;
    private RecyclerView recyclerTab;
    private ImageView ivLeft;
    private ImageView ivRight;
    private List<StockBean> list = new ArrayList<>();
    private List<String> listTab = new ArrayList<>();
    private RyTagAdapter ryTagAdapter;
    private TableAdapter adapterTab;
    private RightAdapter adapterRight;
    private LeftAdapter adapterLeft;
    private MultiScroll multiScroll;
    private boolean refresh;
    private int left, right;

    private LinearLayout currentClickView;
    private int orderType;

    private ScrollListener scrollListener;
    private OrderListener orderListener;

    public MyStockRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public MyStockRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyStockRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_stock, this);
        llLeft = findViewById(R.id.ll_left);
        llRight = findViewById(R.id.ll_right);
        recyclerTop = findViewById(R.id.recyclerTop);
        recyclerRight = findViewById(R.id.recyclerRight);
        recyclerLeft = findViewById(R.id.recyclerLeft);
        recyclerTab = findViewById(R.id.recyclerTab);
        multiScroll = findViewById(R.id.multiScroll);
        ivLeft = findViewById(R.id.iv_left);
        ivRight = findViewById(R.id.iv_right);
        multiScroll.setImageView(ivLeft, ivRight);
        multiScroll.setShadowView(llLeft);
        multiScroll.setRecyclerView(recyclerRight);
        LinearLayout llParent = findViewById(R.id.ll_parent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
        llParent.setLayoutParams(params);
        llParent.setPadding((int) DisplayUtil.dpToPx(context, 15), 0, 0, 0);
        llParent.setOnClickListener(this);
        recyclerTab.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerRight.setLayoutManager(new LinearLayoutManager(context));
        recyclerLeft.setLayoutManager(new LinearLayoutManager(context));
        recyclerLeft.addItemDecoration(new MyDecoration(context));
        recyclerRight.addItemDecoration(new MyDecoration(context));
        setWeight(1, 3);
        recyclerRight.setNestedScrollingEnabled(false);
        recyclerLeft.setNestedScrollingEnabled(false);
        recyclerRight.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (RecyclerView.SCROLL_STATE_IDLE != recyclerView.getScrollState()) {
                    recyclerLeft.scrollBy(dx, dy);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == recyclerView.getScrollState()) {
                    if (scrollListener != null) {
                        scrollListener.scrolled();
                    }
                }
            }
        });
        recyclerLeft.setRecyclerView(recyclerRight);
    }

    public void setTopTable() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerTop.setLayoutManager(layoutManager);
        ryTagAdapter = new RyTagAdapter(getContext(), R.layout.item_ry_tag, ShortSelectBean.getShortList(), this, shortType, true);
        recyclerTop.setAdapter(ryTagAdapter);
    }


    //设置左右比例，且right为右侧可视条数
    public void setWeight(int left, int right) {
        this.left = left;
        this.right = right;
        multiScroll.setWeight(left, right);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) llLeft.getLayoutParams();
        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) llRight.getLayoutParams();
        params1.weight = left;
        params4.weight = right;
        llLeft.setLayoutParams(params1);
        llRight.setLayoutParams(params4);
        adapterTab = new TableAdapter(getContext(), listTab, left, right, this);
        recyclerTab.setAdapter(adapterTab);
        adapterLeft = new LeftAdapter(getContext(), list);
        recyclerLeft.setAdapter(adapterLeft);
    }


    //设置表头
    public void setTable(List<String> listTab) {
        this.listTab.clear();
        this.listTab.addAll(listTab);
        adapterTab.notifyDataSetChanged();
        //设置表头的同时，重新设置列表
        adapterRight = new RightAdapter(getContext(), list, left, right, listTab.size());
        recyclerRight.setAdapter(adapterRight);
        adapterRight.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                refresh = false;
                super.onChanged();
            }
        });
        multiScroll.setSize(listTab.size());//这里设置个数，用于滑动监控
        if (listTab.size() > right) {//如果横向内容多，直接显示向右指示
            ivRight.setVisibility(VISIBLE);
        }
    }

    //设置数据
    public void setData(ArrayList<StockBean> arrayList) {
        refresh = true;
        list.clear();
        list.addAll(arrayList);
        if (adapterRight != null) {
            adapterRight.notifyDataSetChanged();
        }
        if (adapterLeft != null) {
            adapterLeft.notifyDataSetChanged();
        }
    }

    //设置垂直滚动监听
    public void setScrollListener(ScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    @Override
    public void onClick(View v) {
        if (orderListener == null) return;
        if (v instanceof LinearLayout) {
            TextView tv = v.findViewById(R.id.tv);
            ImageView iv = v.findViewById(R.id.iv);
            String text = tv.getText().toString().trim();
            if (currentClickView == null || currentClickView != v) {
                if (currentClickView != null) {
                    ImageView ivPre = currentClickView.findViewById(R.id.iv);
                    ivPre.setImageResource(R.mipmap.icon_quotation_order);
                }
                currentClickView = (LinearLayout) v;
                iv.setImageResource(R.mipmap.icon_quotation_order_down);
                ShortSelectBean selectBean = ShortSelectBean.getShortBean(text);
                orderListener.order(selectBean);
                ryTagAdapter.setSelectBean(selectBean);
                orderType = 1;
            } else {
                ShortSelectBean selectBean = ShortSelectBean.getShortBean(text);
                orderType++;
                switch (orderType % 3) {
                    case 0:
                        iv.setImageResource(R.mipmap.icon_quotation_order);
                        ryTagAdapter.setSelectBean(null);
                        break;
                    case 1:
                        if (selectBean != null) {
                            selectBean.setDesc(true);
                            ryTagAdapter.setSelectBean(selectBean);
                        }
                        iv.setImageResource(R.mipmap.icon_quotation_order_down);
                        break;
                    case 2:
                        if (selectBean != null) {
                            selectBean.setDesc(false);
                            ryTagAdapter.setSelectBean(selectBean);
                        }
                        iv.setImageResource(R.mipmap.icon_quotation_order_up);
                        break;
                }
            }
            recyclerRight.smoothScrollToPosition(0);
        }
    }


    //获取列表顶部可视位置
    public int getTopPosition() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerRight.getLayoutManager();
        return layoutManager.findFirstVisibleItemPosition();
    }


    //获取列表底部可视位置
    public int getBottomPosition() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerRight.getLayoutManager();
        return layoutManager.findLastVisibleItemPosition() + 1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (refresh) {//刷新数据时屏蔽触摸
            return true;
        }
        return super.onTouchEvent(event);
    }

    //设置排序点击
    public void setOrderListener(OrderListener orderListener) {
        this.orderListener = orderListener;
    }

    @Override
    public void click(ShortSelectBean shortSelectBean) {
        if (orderListener != null) {
            orderListener.order(shortSelectBean);
        }
    }

    public interface ScrollListener {
        void scrolled();
    }

    public interface OrderListener {
        void order(ShortSelectBean shortSelectBean);
    }
}
