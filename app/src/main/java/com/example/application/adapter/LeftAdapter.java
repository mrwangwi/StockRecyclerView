package com.example.application.adapter;

import android.content.Context;

import com.example.application.R;
import com.example.application.StockBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LeftAdapter extends CommonAdapter<StockBean> {

    public LeftAdapter(Context context, List<StockBean> datas) {
        super(context, R.layout.item_left, datas);
    }

    @Override
    protected void convert(ViewHolder holder, StockBean s, int position) {
        holder.setText(R.id.tv1, s == null ? "--" : s.getProdName());
        holder.setText(R.id.tv2, s == null ? "--" : s.getProdCode());
    }
}
