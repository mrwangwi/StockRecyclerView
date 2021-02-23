package com.github.tifezh.kchartlib.common;

import com.github.tifezh.kchartlib.chart.entity.IMinuteLine;
import com.github.tifezh.kchartlib.utils.TextUtil;
import com.github.tifezh.kchartlib.utils.ViewUtil;

import java.util.Date;

/**
 * 分时图实体
 * Created by tifezh on 2017/7/20.
 */

public class MinuteLineEntity implements IMinuteLine {

    /**
     * avgPx : 0
     * lastPx : 2687.23
     * pxChange : -1.94
     * pxChangeRate : -0.07
     * date : 20200828
     * businessTime : 930
     * businessAmount : 207669230
     * businessBalance : 2349488147
     * amount : 0
     * wavgPx : 2672466
     */

    public String avgPx;
    public String lastPx;
    public String pxChange;
    public String pxChangeRate;
    public String date;
    public String businessTime;
    public String businessAmount;
    public String businessAmount1;
    public String businessBalance;
    public String amount;
    public String wavgPx;


    @Override
    public float getAvgPrice() {
        return TextUtil.parseFloat(wavgPx) == 0 ? TextUtil.parseFloat(avgPx) : TextUtil.parseFloat(wavgPx);
    }

    @Override
    public float getPrice() {
        return TextUtil.parseFloat(lastPx);
    }

    @Override
    public Date getDate() {
        return ViewUtil.getKTime(TextUtil.parseInt(businessTime));
    }

    @Override
    public float getVolume() {
        return TextUtil.parseFloat(businessAmount);
    }

    @Override
    public float getVolume1() {
        return TextUtil.parseFloat(businessAmount1);
    }

    @Override
    public float getChangeRate() {
        return TextUtil.parseFloat(pxChangeRate);
    }

    @Override
    public String getChangePrice() {
        return pxChange;
    }

    @Override
    public void setAmount(float amount) {
        businessAmount1 = amount + "";
    }


}
