package com.example.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.R;
import com.example.application.StockBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class RightAdapter extends CommonAdapter<StockBean> {

    private final int screenWidth;
    private final int left, right, size;

    public RightAdapter(Context context, List<StockBean> datas, int left, int right, int size) {
        super(context, R.layout.item_right, datas);
        this.left = left;
        this.right = right;
        this.size = size;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void convert(ViewHolder holder, StockBean s, int position) {
        holder.getConvertView().setOnClickListener(v -> {
            Toast.makeText(mContext, "第 " + position + " 条", Toast.LENGTH_SHORT).show();
        });
        LinearLayout view = holder.getView(R.id.ll_parent);
        for (int i = 0; i < size; i++) {
            TextView tv = view.getChildAt(i).findViewById(R.id.tv);
            tv.setText(s == null ? "--" : s.getLastPx() + "");
        }
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        if (itemView instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) itemView;
            LinearLayout ll_parent = linearLayout.findViewById(R.id.ll_parent);
            if (ll_parent != null) {
                int width = screenWidth / (left + right);
                for (int i = 0; i < size; i++) {
                    View child = LayoutInflater.from(mContext).inflate(R.layout.item_right_child, null);
                    LinearLayout ll_child = child.findViewById(R.id.ll_child);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_child.getLayoutParams();
                    layoutParams.width = width;
                    ll_child.setLayoutParams(layoutParams);
                    ll_parent.addView(child);
                }
            }
        }
        super.onViewHolderCreated(holder, itemView);
    }
}
