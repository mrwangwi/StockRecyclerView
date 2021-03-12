package com.example.application;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ShortSelectBean implements Parcelable {

    private boolean isChecked = false;
    private boolean isDesc = true;
    private int shortTypeInt;
    private String shortTypeString;
    private String shortName;

    private String blockCode;

    private boolean isBlock;

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isDesc() {
        return isDesc;
    }

    public void setDesc(boolean desc) {
        isDesc = desc;
    }

    public int getShortTypeInt() {
        return shortTypeInt;
    }

    public void setShortTypeInt(int shortTypeInt) {
        this.shortTypeInt = shortTypeInt;
    }

    public String getShortTypeString() {
        return shortTypeString;
    }

    public void setShortTypeString(String shortTypeString) {
        this.shortTypeString = shortTypeString;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public ShortSelectBean(String shortTypeString, String shortName) {
        this.shortTypeString = shortTypeString;
        this.shortName = shortName;
    }

    public ShortSelectBean(boolean isChecked, boolean isDesc, String shortTypeString, String shortName) {
        this.isChecked = isChecked;
        this.isDesc = isDesc;
        this.shortTypeString = shortTypeString;
        this.shortName = shortName;
    }

    public ShortSelectBean(int shortTypeInt, String shortName) {
        this.shortTypeInt = shortTypeInt;
        this.shortName = shortName;
    }


    public ShortSelectBean() {
    }

    public static List<ShortSelectBean> getShortList() {
        List<ShortSelectBean> list = new ArrayList<>();
        ShortSelectBean selectBean = new ShortSelectBean("pxChangeRate", "涨幅榜");
        selectBean.isChecked = true;
        list.add(selectBean);
        ShortSelectBean selectBean1 = new ShortSelectBean("pxChangeRate", "跌幅榜");
        selectBean1.isDesc = false;
        list.add(selectBean1);
        list.add(new ShortSelectBean("min5Chgpct", "快速涨幅"));
        list.add(new ShortSelectBean("businessBalance", "成交额"));
        list.add(new ShortSelectBean("volRatio", "量比"));
        list.add(new ShortSelectBean("turnoverRatio", "换手率"));
        return list;
    }


    public static ShortSelectBean getShortBean(String text) {
        if (text.equals("涨幅")) {
            return new ShortSelectBean("pxChangeRate", "涨幅");
        }
        if (text.equals("涨速") || text.equals("快速涨幅")) {
            return new ShortSelectBean("min5Chgpct", "快速涨幅");
        }
        if (text.equals("量比")) {
            return new ShortSelectBean("volRatio", "量比");
        }
        if (text.equals("振幅")) {
            return new ShortSelectBean("amplitude", "振幅");
        }
        if (text.equals("换手率")) {
            return new ShortSelectBean("turnoverRatio", "换手率");
        }
        if (text.equals("最新")) {
            return new ShortSelectBean("lastPx", "最新");
        }
        if (text.equals("总手")) {
            return new ShortSelectBean("businessAmount", "总手");
        }
        if (text.equals("现手")) {
            return new ShortSelectBean("currentAmount", "现手");
        }
        if (text.equals("成交额")) {
            return new ShortSelectBean("businessBalance", "成交额");
        }
        if (text.equals("总市值")) {
            return new ShortSelectBean("marketValue", "总市值");
        }
        if (text.equals("流通市值")) {
            return new ShortSelectBean("circulationValue", "流通市值");
        }
        if (text.equals("市盈率")) {
            return new ShortSelectBean("staticPeRate", "市盈率");
        }
        if (text.equals("市盈(动)")) {
            return new ShortSelectBean("peRate", "市盈(动)");
        }
        if (text.equals("市净率")) {
            return new ShortSelectBean("dynPbRate", "市净率");
        }
        return null;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDesc ? (byte) 1 : (byte) 0);
        dest.writeInt(this.shortTypeInt);
        dest.writeString(this.shortTypeString);
        dest.writeString(this.shortName);
        dest.writeString(this.blockCode);
        dest.writeByte(this.isBlock ? (byte) 1 : (byte) 0);
    }

    protected ShortSelectBean(Parcel in) {
        this.isChecked = in.readByte() != 0;
        this.isDesc = in.readByte() != 0;
        this.shortTypeInt = in.readInt();
        this.shortTypeString = in.readString();
        this.shortName = in.readString();
        this.blockCode = in.readString();
        this.isBlock = in.readByte() != 0;
    }

    public static final Creator<ShortSelectBean> CREATOR = new Creator<ShortSelectBean>() {
        @Override
        public ShortSelectBean createFromParcel(Parcel source) {
            return new ShortSelectBean(source);
        }

        @Override
        public ShortSelectBean[] newArray(int size) {
            return new ShortSelectBean[size];
        }
    };
}
