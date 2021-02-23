package com.github.tifezh.kchartlib.common;

import com.github.tifezh.kchartlib.chart.entity.IKLine;


/**
 * K线实体
 * Created by tifezh on 2016/5/16.
 */

public class KLineEntity implements IKLine {


    public int position;

    public String getDatetime() {
        return hqDate;
    }

    @Override
    public float getOpenPrice() {
        return openPrice;
    }

    @Override
    public float getHighPrice() {
        return highPrice;
    }

    @Override
    public float getLowPrice() {
        return lowPrice;
    }

    @Override
    public float getClosePrice() {
        return closePrice;
    }

    @Override
    public float getBalance() {
        return money;
    }

    @Override
    public float getMA5Balance() {
        return MA5Balance;
    }

    @Override
    public float getMA10Balance() {
        return MA10Balance;
    }

    @Override
    public float getMA5Price() {
        return MA5Price;
    }

    @Override
    public float getMA10Price() {
        return MA10Price;
    }

    @Override
    public float getMA20Price() {
        return MA20Price;
    }

    @Override
    public float getMA30Price() {
        return MA30Price;
    }

    @Override
    public String getChangePrice() {
        return changePrice;
    }

    @Override
    public String getChangeRatio() {
        return changeRatio;
    }

    @Override
    public int getPoint() {
        return point;
    }

    @Override
    public int getBackground() {
        if (point == 1) return 0xFFFCEAEA;
        if (point == -1) return 0xFFE6F5E6;
        if (background == 1) return 0xFFFCEAEA;
        if (background == -1) return 0xFFE6F5E6;
        return 0x00000000;
    }

    @Override
    public float getDea() {
        return dea;
    }

    @Override
    public float getDif() {
        return dif;
    }

    @Override
    public float getMacd() {
        return macd;
    }

    @Override
    public float getK() {
        return k;
    }

    @Override
    public float getD() {
        return d;
    }

    @Override
    public float getJ() {
        return j;
    }

    @Override
    public float getRsi1() {
        return rsi1;
    }

    @Override
    public float getRsi2() {
        return rsi2;
    }

    @Override
    public float getRsi3() {
        return rsi3;
    }

    @Override
    public float getUp() {
        return up;
    }

    @Override
    public float getMb() {
        return mb;
    }

    @Override
    public float getDn() {
        return dn;
    }

    @Override
    public float getVolume() {
        return volumn;
    }

    @Override
    public float getMA5Volume() {
        return MA5Volume;
    }

    @Override
    public float getMA10Volume() {
        return MA10Volume;
    }

    public String hqDate;
    public float openPrice;
    public float highPrice;
    public float lowPrice;
    public float closePrice;
    public float prClosePrice;
    public float volumn;
    public float money;
    public String turnoverRatio;

    public int point;
    public int background;

    public String hqTime;

    public int intDate;

    public float MA5Price;

    public float MA10Price;

    public float MA20Price;

    public float MA30Price;

    public float dea;

    public float dif;

    public float macd;

    public float k;

    public float d;

    public float j;

    public float rsi1;

    public float rsi2;

    public float rsi3;

    public float up;

    public float mb;

    public float dn;

    public float MA5Volume;

    public float MA10Volume;

    public float MA5Balance;

    public float MA10Balance;

    public String changePrice;

    public String changeRatio;

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }
}
