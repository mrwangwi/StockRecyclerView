package com.github.tifezh.kchartlib.common;

import com.github.tifezh.kchartlib.chart.BaseKChartAdapter;
import com.github.tifezh.kchartlib.utils.TextUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据适配器
 * Created by tifezh on 2016/6/18.
 */

public class KChartAdapter extends BaseKChartAdapter {

    private List<KLineEntity> datas = new ArrayList<>();

    public KChartAdapter() {

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        if (position < datas.size()) {
            return datas.get(position);
        } else {
            return null;
        }
    }

    @Override
    public Date getDate(int position) {
        try {

            if (TextUtil.isEmpty(datas.get(position).hqTime)) {
                String s = datas.get(position).hqDate;
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                return format.parse(s);
            } else {
                String s = datas.get(position).hqDate + (TextUtil.parseInt(datas.get(position).hqTime) < 1000 ? "0" + datas.get(position).hqTime : datas.get(position).hqTime);
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
                return format.parse(s);
            }


//            String[] split = s.split("/");
//            Date date = new Date();
//            date.setYear(Integer.parseInt(split[0]) - 1900);
//            date.setMonth(Integer.parseInt(split[1]) - 1);
//            date.setDate(Integer.parseInt(split[2]));
//            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<KLineEntity> getAll() {
        return datas;
    }

    @Override
    public int getData() {
        if (datas.size() == 0) {
            return 0;
        } else {
            return datas.get(0).intDate;
        }
    }

    @Override
    public String getTime(int position) {
        String s = datas.get(position).hqTime;
        if (s == null || s.trim().length() == 0) {
            return "";
        } else {
            return " " + s;
        }
    }

    /**
     * 向头部添加数据
     */
    public void addHeaderData(List<KLineEntity> data) {
        if (data != null && !data.isEmpty()) {
            datas.addAll(data);
            DataHelper.calculate(datas);
            notifyDataSetChanged();
        }
    }

    /**
     * 向尾部添加数据
     */
    public void addFooterData(List<KLineEntity> data) {
        if (data != null && !data.isEmpty()) {
            datas.addAll(0, data);
            DataHelper.calculate(datas);
            notifyDataSetChanged();
        }
    }

    /**
     * 改变某个点的值
     *
     * @param position 索引值
     */
    public void changeItem(int position, KLineEntity data) {
        datas.set(position, data);
        notifyDataSetChanged();
    }

}
