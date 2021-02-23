package com.example.application;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class StockBean implements Parcelable {

    private Long id;
    private String amount;
    private Double amplitude;
    private String avgPx;
    private Double businessAmount;
    private Double businessBalance;
    private String businessCount;
    private String chiSpelling;
    private Double circulationValue;
    private Double currentAmount;
    private String dataTimestamp;
    private String downPx;
    private Double dynPbRate;
    private String entrustDiff;
    private String entrustRatio;
    private String fallCount;
    private String highPx;
    private String hqTypeCode;
    private Double lastPx;
    private String lowPx;
    private Double marketValue;
    private Double min5Chgpct;
    private String openPx;
    private Double peRate;
    private String preclosePx;
    private String prodCode;
    private String prodName;
    private String prodType;
    private String pxChange;
    private Double pxChangeRate;
    private String riseCount;
    private String specialMarker;
    private Double staticPeRate;
    private String tradeStatus;
    private Double turnoverRatio;
    private String upPx;
    private Double volRatio;
    private String hyBlcokName;
    private String mainNet;
    private String updateTime;
    private String yearPrice;
    private String optTime;
    private String optPrice;
    private String score;

    private String yearPxChangeRate;
    private String optPxChangeRate;


    private RiseFistBean riseFist;
    private ArrayList<String> marker = new ArrayList<>();
    private List<String> bidAmount;
    private List<String> bidPrice;
    private List<String> offerAmount;
    private List<String> offerPrice;
    private String userId;
    private Long createTime;
    private Long topTime;
    private boolean isCheck;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getAmount() {
        return this.amount;
    }


    public void setAmount(String amount) {
        this.amount = amount;
    }


    public Double getAmplitude() {
        return this.amplitude;
    }


    public void setAmplitude(Double amplitude) {
        this.amplitude = amplitude;
    }


    public String getAvgPx() {
        return this.avgPx;
    }


    public void setAvgPx(String avgPx) {
        this.avgPx = avgPx;
    }


    public Double getBusinessAmount() {
        return this.businessAmount;
    }


    public void setBusinessAmount(Double businessAmount) {
        this.businessAmount = businessAmount;
    }


    public Double getBusinessBalance() {
        return this.businessBalance;
    }


    public void setBusinessBalance(Double businessBalance) {
        this.businessBalance = businessBalance;
    }


    public String getBusinessCount() {
        return this.businessCount;
    }


    public void setBusinessCount(String businessCount) {
        this.businessCount = businessCount;
    }


    public String getChiSpelling() {
        return this.chiSpelling;
    }


    public void setChiSpelling(String chiSpelling) {
        this.chiSpelling = chiSpelling;
    }


    public Double getCirculationValue() {
        return this.circulationValue;
    }


    public void setCirculationValue(Double circulationValue) {
        this.circulationValue = circulationValue;
    }


    public Double getCurrentAmount() {
        return this.currentAmount;
    }


    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }


    public String getDataTimestamp() {
        return this.dataTimestamp;
    }


    public void setDataTimestamp(String dataTimestamp) {
        this.dataTimestamp = dataTimestamp;
    }


    public String getDownPx() {
        return this.downPx;
    }


    public void setDownPx(String downPx) {
        this.downPx = downPx;
    }


    public Double getDynPbRate() {
        return this.dynPbRate;
    }


    public void setDynPbRate(Double dynPbRate) {
        this.dynPbRate = dynPbRate;
    }


    public String getEntrustDiff() {
        return this.entrustDiff;
    }


    public void setEntrustDiff(String entrustDiff) {
        this.entrustDiff = entrustDiff;
    }


    public String getEntrustRatio() {
        return this.entrustRatio;
    }


    public void setEntrustRatio(String entrustRatio) {
        this.entrustRatio = entrustRatio;
    }


    public String getFallCount() {
        return this.fallCount;
    }


    public void setFallCount(String fallCount) {
        this.fallCount = fallCount;
    }


    public String getHighPx() {
        return this.highPx;
    }


    public void setHighPx(String highPx) {
        this.highPx = highPx;
    }


    public String getHqTypeCode() {
        return this.hqTypeCode;
    }


    public void setHqTypeCode(String hqTypeCode) {
        this.hqTypeCode = hqTypeCode;
    }


    public Double getLastPx() {
        return this.lastPx;
    }


    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
    }


    public String getLowPx() {
        return this.lowPx;
    }


    public void setLowPx(String lowPx) {
        this.lowPx = lowPx;
    }


    public Double getMarketValue() {
        return this.marketValue;
    }


    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }


    public Double getMin5Chgpct() {
        return this.min5Chgpct;
    }


    public void setMin5Chgpct(Double min5Chgpct) {
        this.min5Chgpct = min5Chgpct;
    }


    public String getOpenPx() {
        return this.openPx;
    }


    public void setOpenPx(String openPx) {
        this.openPx = openPx;
    }


    public Double getPeRate() {
        return this.peRate;
    }


    public void setPeRate(Double peRate) {
        this.peRate = peRate;
    }


    public String getPreclosePx() {
        return this.preclosePx;
    }


    public void setPreclosePx(String preclosePx) {
        this.preclosePx = preclosePx;
    }


    public String getProdCode() {
        return this.prodCode;
    }


    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }


    public String getProdName() {
        return this.prodName;
    }


    public void setProdName(String prodName) {
        this.prodName = prodName;
    }


    public String getProdType() {
        return this.prodType;
    }


    public void setProdType(String prodType) {
        this.prodType = prodType;
    }


    public String getPxChange() {
        return this.pxChange;
    }


    public void setPxChange(String pxChange) {
        this.pxChange = pxChange;
    }


    public Double getPxChangeRate() {
        return this.pxChangeRate;
    }


    public void setPxChangeRate(Double pxChangeRate) {
        this.pxChangeRate = pxChangeRate;
    }


    public String getRiseCount() {
        return this.riseCount;
    }


    public void setRiseCount(String riseCount) {
        this.riseCount = riseCount;
    }


    public String getSpecialMarker() {
        return this.specialMarker;
    }


    public void setSpecialMarker(String specialMarker) {
        this.specialMarker = specialMarker;
    }


    public Double getStaticPeRate() {
        return this.staticPeRate;
    }


    public void setStaticPeRate(Double staticPeRate) {
        this.staticPeRate = staticPeRate;
    }


    public String getTradeStatus() {
        return this.tradeStatus;
    }


    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }


    public Double getTurnoverRatio() {
        return this.turnoverRatio;
    }


    public void setTurnoverRatio(Double turnoverRatio) {
        this.turnoverRatio = turnoverRatio;
    }


    public String getUpPx() {
        return this.upPx;
    }


    public void setUpPx(String upPx) {
        this.upPx = upPx;
    }


    public Double getVolRatio() {
        return this.volRatio;
    }


    public void setVolRatio(Double volRatio) {
        this.volRatio = volRatio;
    }


    public String getHyBlcokName() {
        return this.hyBlcokName;
    }


    public void setHyBlcokName(String hyBlcokName) {
        this.hyBlcokName = hyBlcokName;
    }


    public RiseFistBean getRiseFist() {
        return riseFist;
    }

    public void setRiseFist(RiseFistBean riseFist) {
        this.riseFist = riseFist;
    }

    public ArrayList<String> getMarker() {
        return marker;
    }

    public void setMarker(ArrayList<String> marker) {
        this.marker = marker;
    }

    public List<String> getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(List<String> bidAmount) {
        this.bidAmount = bidAmount;
    }

    public List<String> getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(List<String> bidPrice) {
        this.bidPrice = bidPrice;
    }

    public List<String> getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(List<String> offerAmount) {
        this.offerAmount = offerAmount;
    }

    public List<String> getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(List<String> offerPrice) {
        this.offerPrice = offerPrice;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Long getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


    public Long getTopTime() {
        return this.topTime;
    }


    public void setTopTime(Long topTime) {
        this.topTime = topTime;
    }


    public boolean getIsCheck() {
        return this.isCheck;
    }


    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }


    public static class RiseFistBean {
        /**
         * prodName : 君正集团
         * prodCode : 601216
         * hqTypeCode : XSHG.ESA.M
         * lastPx : 10.41
         * pxChangeRate : 10.04
         */

        private String prodName;
        private String prodCode;
        private String hqTypeCode;
        private String lastPx;
        private String pxChangeRate;

        public String getProdName() {
            return prodName;
        }

        public void setProdName(String prodName) {
            this.prodName = prodName;
        }

        public String getProdCode() {
            return prodCode;
        }

        public void setProdCode(String prodCode) {
            this.prodCode = prodCode;
        }

        public String getHqTypeCode() {
            return hqTypeCode;
        }

        public void setHqTypeCode(String hqTypeCode) {
            this.hqTypeCode = hqTypeCode;
        }

        public String getLastPx() {
            return lastPx;
        }

        public void setLastPx(String lastPx) {
            this.lastPx = lastPx;
        }

        public String getPxChangeRate() {
            return pxChangeRate;
        }

        public void setPxChangeRate(String pxChangeRate) {
            this.pxChangeRate = pxChangeRate;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.prodCode);
        dest.writeString(this.prodName);
        dest.writeString(this.prodType);
        dest.writeString(this.hqTypeCode);
    }


    public String getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


    public String getYearPrice() {
        return this.yearPrice;
    }


    public void setYearPrice(String yearPrice) {
        this.yearPrice = yearPrice;
    }


    public String getOptTime() {
        return this.optTime;
    }


    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }


    public String getOptPrice() {
        return this.optPrice;
    }


    public void setOptPrice(String optPrice) {
        this.optPrice = optPrice;
    }


    public String getYearPxChangeRate() {
        return this.yearPxChangeRate;
    }


    public void setYearPxChangeRate(String yearPxChangeRate) {
        this.yearPxChangeRate = yearPxChangeRate;
    }


    public String getOptPxChangeRate() {
        return this.optPxChangeRate;
    }


    public void setOptPxChangeRate(String optPxChangeRate) {
        this.optPxChangeRate = optPxChangeRate;
    }


    public String getMainNet() {
        return this.mainNet;
    }


    public void setMainNet(String mainNet) {
        this.mainNet = mainNet;
    }

    protected StockBean(Parcel in) {
        this.prodCode = in.readString();
        this.prodName = in.readString();
        this.prodType = in.readString();
        this.hqTypeCode = in.readString();
    }


    public static final Creator<StockBean> CREATOR = new Creator<StockBean>() {
        @Override
        public StockBean createFromParcel(Parcel source) {
            return new StockBean(source);
        }

        @Override
        public StockBean[] newArray(int size) {
            return new StockBean[size];
        }
    };


    @Override
    public String toString() {
        return "StockBean{" +
                "hqTypeCode='" + hqTypeCode + '\'' +
                ", prodCode='" + prodCode + '\'' +
                ", prodName='" + prodName + '\'' +
                ", prodType='" + prodType + '\'' +
                '}';
    }
}
