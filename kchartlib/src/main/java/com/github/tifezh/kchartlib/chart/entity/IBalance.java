package com.github.tifezh.kchartlib.chart.entity;

/**
 * 成交量接口
 * Created by hjm on 2017/11/14 17:46.
 */

public interface IBalance {

    /**
     * 开盘价
     */
    float getOpenPrice();

    /**
     * 收盘价
     */
    float getClosePrice();

    /**
     * 成交量
     */
    float getBalance();

    /**
     * 五(月，日，时，分，5分等)均量
     */
    float getMA5Balance();

    /**
     * 十(月，日，时，分，5分等)均量
     */
    float getMA10Balance();
}
