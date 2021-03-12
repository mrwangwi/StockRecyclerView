package com.example.application.adapter;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.application.R;
import com.example.application.ShortSelectBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class RyTagAdapter extends CommonAdapter<ShortSelectBean> {
    private Click click;
    private ShortSelectBean selectBean;
    private int width;
    private boolean isMatch;

    public RyTagAdapter(Context context, List<ShortSelectBean> datas, Click click) {
        super(context, R.layout.item_ry_tag, datas);
        this.click = click;
    }

    public RyTagAdapter(Context context, int layoutId, List<ShortSelectBean> datas, Click click) {
        super(context, layoutId, datas);
        this.click = click;
    }

    public RyTagAdapter(Context context, int layoutId, List<ShortSelectBean> datas, Click click, ShortSelectBean selectBean) {
        super(context, layoutId, datas);
        this.click = click;
        this.selectBean = selectBean;
    }

    public RyTagAdapter(Context context, int layoutId, List<ShortSelectBean> datas, Click click, ShortSelectBean selectBean, boolean isMatch) {
        super(context, layoutId, datas);
        this.click = click;
        this.selectBean = selectBean;
        this.isMatch = isMatch;
        if (isMatch) {
            width = (context.getResources().getDisplayMetrics().widthPixels / 5);
        }
    }

    @Override
    protected void convert(ViewHolder holder, ShortSelectBean s, int position) {

        if (isMatch) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.getView(R.id.cv).getLayoutParams();
            params.width = width;
            holder.getView(R.id.cv).setLayoutParams(params);
        }

        if (selectBean == null) {
            s.setChecked(false);
        } else if (s.getShortName().equals(selectBean.getShortName())) {
            s.setChecked(true);
        } else if (s.getShortName().equals("涨幅榜")) {
            if (selectBean.getShortTypeString().equals("pxChangeRate")) {
                s.setChecked(selectBean.isDesc());
            } else {
                s.setChecked(false);
            }
        } else if (s.getShortName().equals("跌幅榜")) {
            if (selectBean.getShortTypeString().equals("pxChangeRate")) {
                s.setChecked(!selectBean.isDesc());
            } else {
                s.setChecked(false);
            }
        } else {
            s.setChecked(false);
        }
        if (s.getShortName().equals("量比")) {
            holder.setText(R.id.tv, "量比榜");
        } else {
            holder.setText(R.id.tv, s.getShortName());
        }
        if (s.isChecked()) {
            holder.setBackgroundRes(R.id.tv, R.drawable.border_orange_round);
            holder.setTextColor(R.id.tv, mContext.getResources().getColor(R.color.text_orange));
        } else {
            holder.setBackgroundRes(R.id.tv, R.drawable.solid_gary_round);
            holder.setTextColor(R.id.tv, mContext.getResources().getColor(R.color.black));
        }

        holder.getConvertView().setOnClickListener(v -> {
            if (!s.isChecked()) {
                click.click(s);
                selectBean = s;
                notifyDataSetChanged();
            }
        });
    }

    public interface Click {
        void click(ShortSelectBean shortSelectBean);
    }


    public void setSelectBean(ShortSelectBean selectBean) {
        this.selectBean = selectBean;
        notifyDataSetChanged();
    }
}
