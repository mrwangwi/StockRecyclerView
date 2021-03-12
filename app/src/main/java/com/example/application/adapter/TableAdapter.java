package com.example.application.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.example.application.R;
import com.example.application.ShortSelectBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class TableAdapter extends CommonAdapter<String> {

    private final int screenWidth;
    private final int left;
    private final int right;
    private View.OnClickListener clickListener;
    private ShortSelectBean shortType;

    public TableAdapter(Context context, List<String> datas, int left, int right, View.OnClickListener clickListener) {
        super(context, R.layout.view_tab, datas);
        this.left = left;
        this.right = right;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        this.clickListener = clickListener;
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv, s);

        holder.getView(R.id.iv).setVisibility(ShortSelectBean.getShortBean(s) == null ? View.GONE : View.VISIBLE);

        if (shortType != null) {
            if (s.equals("涨幅") && (shortType.getShortName().equals("涨幅榜") || shortType.getShortName().equals("跌幅榜"))) {
                holder.setImageResource(R.id.iv, shortType.isDesc() ? R.mipmap.icon_quotation_order_down : R.mipmap.icon_quotation_order_up);
            } else if (s.equals("涨速") && (shortType.getShortName().equals("快速涨幅"))) {
                holder.setImageResource(R.id.iv, shortType.isDesc() ? R.mipmap.icon_quotation_order_down : R.mipmap.icon_quotation_order_up);
            } else if (s.equals(shortType.getShortName())) {
                holder.setImageResource(R.id.iv, shortType.isDesc() ? R.mipmap.icon_quotation_order_down : R.mipmap.icon_quotation_order_up);
            } else {
                holder.setImageResource(R.id.iv, R.mipmap.icon_quotation_order);
            }
        } else {
            holder.setImageResource(R.id.iv, R.mipmap.icon_quotation_order);
        }
        holder.getView(R.id.ll_parent).setOnClickListener(v -> {
            clickListener.onClick(v);
        });
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        if (itemView instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) itemView;
            LinearLayout ll_parent = linearLayout.findViewById(R.id.ll_parent);
            if (ll_parent != null) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_parent.getLayoutParams();
                layoutParams.width = screenWidth / (left + right);
                itemView.setLayoutParams(layoutParams);
            }
        }
        super.onViewHolderCreated(holder, itemView);
    }

    public void setShortType(ShortSelectBean shortType) {
        this.shortType = shortType;
    }

    public ShortSelectBean getShortType() {
        return shortType;
    }
}
