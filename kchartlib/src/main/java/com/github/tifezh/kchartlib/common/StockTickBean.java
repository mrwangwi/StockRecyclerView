package com.github.tifezh.kchartlib.common;

public class StockTickBean {

    private long id;
    private String proCode;
    private String hpPx;
    private String businessAmount;
    private String businessCount;
    private String date;
    private String businessTime;
    private String businessBalance;
    private String businessDirection;
    private String amount;
    private String amount1;

    private boolean isAfterHours;

    public boolean isAfterHours() {
        return isAfterHours;
    }

    public void setAfterHours(boolean afterHours) {
        isAfterHours = afterHours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getHpPx() {
        return hpPx;
    }

    public void setHpPx(String hpPx) {
        this.hpPx = hpPx;
    }

    public String getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(String businessAmount) {
        this.businessAmount = businessAmount;
    }

    public String getBusinessCount() {
        return businessCount;
    }

    public void setBusinessCount(String businessCount) {
        this.businessCount = businessCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getBusinessBalance() {
        return businessBalance;
    }

    public void setBusinessBalance(String businessBalance) {
        this.businessBalance = businessBalance;
    }

    public String getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(String businessDirection) {
        this.businessDirection = businessDirection;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount1() {
        return amount1;
    }

    public void setAmount1(String amount1) {
        this.amount1 = amount1;
    }
}
