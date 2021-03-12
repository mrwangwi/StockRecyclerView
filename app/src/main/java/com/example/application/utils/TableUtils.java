package com.example.application.utils;

import java.util.ArrayList;

public class TableUtils {


    public static final String HANDICAP_DEFAULT = "[\n" +
            "    {\n" +
            "      \"prodName\": \"上证指数\",\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"hqTypeCode\": \"XSHG.MRI\",\n" +
            "      \"prodType\": \"MRI\",\n" +
            "      \"laxPx\": \"3271.72\",\n" +
            "      \"pxChange\": \"17.09\",\n" +
            "      \"pxChangeRate\": \"0.53\",\n" +
            "      \"score\": \"60.97\",\n" +
            "      \"tradeStatus\": \"TRADE\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodName\": \"深证成指\",\n" +
            "      \"prodCode\": \"399001\",\n" +
            "      \"hqTypeCode\": \"XSHE.MRI\",\n" +
            "      \"prodType\": \"MRI\",\n" +
            "      \"laxPx\": \"12982.84\",\n" +
            "      \"pxChange\": \"121.10\",\n" +
            "      \"pxChangeRate\": \"0.94\",\n" +
            "      \"score\": \"61.87\",\n" +
            "      \"tradeStatus\": \"TRADE\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodName\": \"创业板指\",\n" +
            "      \"prodCode\": \"399006\",\n" +
            "      \"hqTypeCode\": \"XSHE.MRI\",\n" +
            "      \"prodType\": \"MRI\",\n" +
            "      \"laxPx\": \"2545.36\",\n" +
            "      \"pxChange\": \"21.96\",\n" +
            "      \"pxChangeRate\": \"0.87\",\n" +
            "      \"score\": \"61.73\",\n" +
            "      \"tradeStatus\": \"TRADE\"\n" +
            "    }\n" +
            "  ]";

    public static String TABLE0 = "最新/行业板块/涨幅/量比/换手率/振幅/涨速/总手/现手/成交额/总市值/流通市值/市盈率/市盈(动)/市净率";
    public static String TABLE1 = "最新/涨幅/量比/换手率/振幅/涨速/总手/现手/成交额/总市值/流通市值/市盈率/市盈(动)/市净率";
    public static String TABLE_PLATE = "最新/涨幅/领涨股/涨速/量比/振幅";
    public static String TABLE2 = "营业总收入/营业总收入同比增长率/净利润/净利润同比增长率/扣非净利润/扣非净利润同比增长率/每股收益/每股净资产/销售毛利率/销售净利率/净资产收益率";
    public static String TABLE3 = "主营业务收入/主营业务收入增长率/营业利润/营业利润增长率/归母净利润/归母净利润增长率/主营业务利润率/流通市值/总市值/财报日期";
    public static String TABLE4 = "资产总计/负债合计/所有者权益合计/资产负债率/流通市值/总市值/财报日期";
    public static String TABLE5 = "经营现金流净额/投资现金流净额/筹集现金流净额/流通市值/总市值/财报日期";
    public static String TABLE6 = "最新/涨幅/流通市值";

    public static ArrayList<String> getTabList(String s) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] strings = s.split("/");
        for (int i = 0; i < strings.length; i++) {
            arrayList.add(strings[i]);
        }
        return arrayList;
    }

    public static ArrayList<String> getTabList(String s, String title) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] strings = s.split("/");
        for (int i = 0; i < strings.length; i++) {
            if (title.equals("最新") && strings[i].equals("涨幅")) {
                arrayList.add(1, "涨幅");
            } else if (strings[i].equals(title)) {
                if (arrayList.size() > 0) {
                    arrayList.add(1, strings[i]);
                } else {
                    arrayList.add(strings[i]);
                }
            } else if (title.equals("快速涨幅") && strings[i].equals("涨速")) {
                arrayList.add(1, "涨速");
            } else if ((title.equals("涨幅榜") || title.equals("跌幅榜")) && strings[i].equals("涨幅")) {
                if (arrayList.size() > 0) {
                    arrayList.add(1, strings[i]);
                } else {
                    arrayList.add(strings[i]);
                }
            } else {
                arrayList.add(strings[i]);
            }
        }
        return arrayList;
    }


    public static String TEST = "{\n" +
            "  \"status\": 200,\n" +
            "  \"msg\": null,\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0930\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0931\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0932\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0933\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0934\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0935\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0936\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0937\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" + "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0938\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0939\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0940\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0941\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0942\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0943\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0944\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0945\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0946\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0947\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0948\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0949\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0950\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0951\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0952\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0953\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0954\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0955\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0956\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0957\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0958\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"0959\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1000\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1001\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +

            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.04,\n" +
            "      \"lastPx\": 3297.08,\n" +
            "      \"pxChange\": -19.43,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1002\",\n" +
            "      \"businessAmount\": 1150928,\n" +
            "      \"businessBalance\": 1537753216,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.49,\n" +
            "      \"lastPx\": 3296.85,\n" +
            "      \"pxChange\": -18.85,\n" +
            "      \"pxChangeRate\": -0.57,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1003\",\n" +
            "      \"businessAmount\": 301762,\n" +
            "      \"businessBalance\": 423681428,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.29\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1314.53,\n" +
            "      \"lastPx\": 3298.09,\n" +
            "      \"pxChange\": -16.54,\n" +
            "      \"pxChangeRate\": -0.5,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1004\",\n" +
            "      \"businessAmount\": 1459290,\n" +
            "      \"businessBalance\": 1982003725,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3306.34\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1314.66,\n" +
            "      \"lastPx\": 3300.4,\n" +
            "      \"pxChange\": -14.87,\n" +
            "      \"pxChangeRate\": -0.45,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1005\",\n" +
            "      \"businessAmount\": 1342764,\n" +
            "      \"businessBalance\": 1773290868,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3308.22\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.01,\n" +
            "      \"lastPx\": 3302.07,\n" +
            "      \"pxChange\": -12.68,\n" +
            "      \"pxChangeRate\": -0.38,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1006\",\n" +
            "      \"businessAmount\": 1207856,\n" +
            "      \"businessBalance\": 1480394270,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3310.21\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1312.2,\n" +
            "      \"lastPx\": 3304.26,\n" +
            "      \"pxChange\": -13.33,\n" +
            "      \"pxChangeRate\": -0.4,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1007\",\n" +
            "      \"businessAmount\": 1106288,\n" +
            "      \"businessBalance\": 1399186075,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3312.02\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1311.96,\n" +
            "      \"lastPx\": 3303.61,\n" +
            "      \"pxChange\": -15.02,\n" +
            "      \"pxChangeRate\": -0.45,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1008\",\n" +
            "      \"businessAmount\": 1125712,\n" +
            "      \"businessBalance\": 1461468056,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3311.19\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.05,\n" +
            "      \"lastPx\": 3301.91,\n" +
            "      \"pxChange\": -14.21,\n" +
            "      \"pxChangeRate\": -0.43,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1009\",\n" +
            "      \"businessAmount\": 950394,\n" +
            "      \"businessBalance\": 1320658487,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.53\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.5,\n" +
            "      \"lastPx\": 3302.73,\n" +
            "      \"pxChange\": -14.37,\n" +
            "      \"pxChangeRate\": -0.43,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1010\",\n" +
            "      \"businessAmount\": 860579,\n" +
            "      \"businessBalance\": 1161562475,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.56\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.86,\n" +
            "      \"lastPx\": 3302.57,\n" +
            "      \"pxChange\": -15.27,\n" +
            "      \"pxChangeRate\": -0.46,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1011\",\n" +
            "      \"businessAmount\": 796995,\n" +
            "      \"businessBalance\": 1071675117,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3310.64\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.78,\n" +
            "      \"lastPx\": 3301.66,\n" +
            "      \"pxChange\": -17.11,\n" +
            "      \"pxChangeRate\": -0.52,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1012\",\n" +
            "      \"businessAmount\": 1111230,\n" +
            "      \"businessBalance\": 1454506772,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3308.82\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1313.13,\n" +
            "      \"lastPx\": 3299.82,\n" +
            "      \"pxChange\": -18.75,\n" +
            "      \"pxChangeRate\": -0.57,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1013\",\n" +
            "      \"businessAmount\": 851008,\n" +
            "      \"businessBalance\": 1071465202,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3308.06\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1311.07,\n" +
            "      \"lastPx\": 3298.18,\n" +
            "      \"pxChange\": -18.31,\n" +
            "      \"pxChangeRate\": -0.55,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1014\",\n" +
            "      \"businessAmount\": 898472,\n" +
            "      \"businessBalance\": 1030020107,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3306.92\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1310.8,\n" +
            "      \"lastPx\": 3298.63,\n" +
            "      \"pxChange\": -18.3,\n" +
            "      \"pxChangeRate\": -0.55,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1015\",\n" +
            "      \"businessAmount\": 756384,\n" +
            "      \"businessBalance\": 971690524,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3306.63\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1310.75,\n" +
            "      \"lastPx\": 3298.63,\n" +
            "      \"pxChange\": -17.73,\n" +
            "      \"pxChangeRate\": -0.53,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1016\",\n" +
            "      \"businessAmount\": 720635,\n" +
            "      \"businessBalance\": 941265585,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.09\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1311.1,\n" +
            "      \"lastPx\": 3299.2,\n" +
            "      \"pxChange\": -20.03,\n" +
            "      \"pxChangeRate\": -0.6,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1017\",\n" +
            "      \"businessAmount\": 690528,\n" +
            "      \"businessBalance\": 930788997,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.51\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1311.62,\n" +
            "      \"lastPx\": 3296.91,\n" +
            "      \"pxChange\": -21.63,\n" +
            "      \"pxChangeRate\": -0.65,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1018\",\n" +
            "      \"businessAmount\": 787000,\n" +
            "      \"businessBalance\": 1071692299,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.16\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1311.45,\n" +
            "      \"lastPx\": 3295.31,\n" +
            "      \"pxChange\": -21.19,\n" +
            "      \"pxChangeRate\": -0.64,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1019\",\n" +
            "      \"businessAmount\": 782062,\n" +
            "      \"businessBalance\": 1012567875,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3303.68\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1311.42,\n" +
            "      \"lastPx\": 3295.74,\n" +
            "      \"pxChange\": -21.69,\n" +
            "      \"pxChangeRate\": -0.65,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1020\",\n" +
            "      \"businessAmount\": 709816,\n" +
            "      \"businessBalance\": 928454125,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.3\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1310.73,\n" +
            "      \"lastPx\": 3295.24,\n" +
            "      \"pxChange\": -22.44,\n" +
            "      \"pxChangeRate\": -0.68,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1021\",\n" +
            "      \"businessAmount\": 771969,\n" +
            "      \"businessBalance\": 958916152,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3304.54\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1310.44,\n" +
            "      \"lastPx\": 3294.49,\n" +
            "      \"pxChange\": -20.39,\n" +
            "      \"pxChangeRate\": -0.61,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1022\",\n" +
            "      \"businessAmount\": 724994,\n" +
            "      \"businessBalance\": 927085226,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3304.15\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1311.69,\n" +
            "      \"lastPx\": 3296.55,\n" +
            "      \"pxChange\": -18.68,\n" +
            "      \"pxChangeRate\": -0.56,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1023\",\n" +
            "      \"businessAmount\": 715260,\n" +
            "      \"businessBalance\": 1036510415,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3306.8\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1311.45,\n" +
            "      \"lastPx\": 3298.25,\n" +
            "      \"pxChange\": -19.27,\n" +
            "      \"pxChangeRate\": -0.58,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1024\",\n" +
            "      \"businessAmount\": 671273,\n" +
            "      \"businessBalance\": 861025015,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.78\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1309.38,\n" +
            "      \"lastPx\": 3297.67,\n" +
            "      \"pxChange\": -18.93,\n" +
            "      \"pxChangeRate\": -0.57,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1025\",\n" +
            "      \"businessAmount\": 766904,\n" +
            "      \"businessBalance\": 839189892,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.42\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1308.92,\n" +
            "      \"lastPx\": 3298.01,\n" +
            "      \"pxChange\": -18.25,\n" +
            "      \"pxChangeRate\": -0.55,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1026\",\n" +
            "      \"businessAmount\": 451622,\n" +
            "      \"businessBalance\": 553658530,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3308.37\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1306.22,\n" +
            "      \"lastPx\": 3298.68,\n" +
            "      \"pxChange\": -18.72,\n" +
            "      \"pxChangeRate\": -0.56,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1027\",\n" +
            "      \"businessAmount\": 1003786,\n" +
            "      \"businessBalance\": 1092647301,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.19\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1305.21,\n" +
            "      \"lastPx\": 3298.21,\n" +
            "      \"pxChange\": -20.78,\n" +
            "      \"pxChangeRate\": -0.63,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1028\",\n" +
            "      \"businessAmount\": 660316,\n" +
            "      \"businessBalance\": 778370112,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3308.82\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1305.37,\n" +
            "      \"lastPx\": 3296.15,\n" +
            "      \"pxChange\": -22.98,\n" +
            "      \"pxChangeRate\": -0.69,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1029\",\n" +
            "      \"businessAmount\": 686484,\n" +
            "      \"businessBalance\": 909491280,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.57\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1304.61,\n" +
            "      \"lastPx\": 3293.95,\n" +
            "      \"pxChange\": -24.83,\n" +
            "      \"pxChangeRate\": -0.75,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1030\",\n" +
            "      \"businessAmount\": 825055,\n" +
            "      \"businessBalance\": 1013287916,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.3\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1303.38,\n" +
            "      \"lastPx\": 3292.11,\n" +
            "      \"pxChange\": -23.97,\n" +
            "      \"pxChangeRate\": -0.72,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1031\",\n" +
            "      \"businessAmount\": 889164,\n" +
            "      \"businessBalance\": 1054966720,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3303.55\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1302.1,\n" +
            "      \"lastPx\": 3292.96,\n" +
            "      \"pxChange\": -22.86,\n" +
            "      \"pxChangeRate\": -0.69,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1032\",\n" +
            "      \"businessAmount\": 797407,\n" +
            "      \"businessBalance\": 929255730,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3304.16\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1301.98,\n" +
            "      \"lastPx\": 3294.07,\n" +
            "      \"pxChange\": -24.27,\n" +
            "      \"pxChangeRate\": -0.73,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1033\",\n" +
            "      \"businessAmount\": 631131,\n" +
            "      \"businessBalance\": 810915581,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3304.02\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1301.66,\n" +
            "      \"lastPx\": 3292.67,\n" +
            "      \"pxChange\": -24.04,\n" +
            "      \"pxChangeRate\": -0.72,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1034\",\n" +
            "      \"businessAmount\": 592345,\n" +
            "      \"businessBalance\": 743665959,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3303.89\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1301.22,\n" +
            "      \"lastPx\": 3292.9,\n" +
            "      \"pxChange\": -21.09,\n" +
            "      \"pxChangeRate\": -0.64,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1035\",\n" +
            "      \"businessAmount\": 247812,\n" +
            "      \"businessBalance\": 284186397,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3306.12\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1296.91,\n" +
            "      \"lastPx\": 3295.84,\n" +
            "      \"pxChange\": -18.39,\n" +
            "      \"pxChangeRate\": -0.55,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1036\",\n" +
            "      \"businessAmount\": 1808901,\n" +
            "      \"businessBalance\": 1968823605,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.44\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.9,\n" +
            "      \"lastPx\": 3298.55,\n" +
            "      \"pxChange\": -19.55,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1037\",\n" +
            "      \"businessAmount\": 486712,\n" +
            "      \"businessBalance\": 540747504,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3310.15\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.65,\n" +
            "      \"lastPx\": 3297.38,\n" +
            "      \"pxChange\": -19.3,\n" +
            "      \"pxChangeRate\": -0.58,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1038\",\n" +
            "      \"businessAmount\": 1127464,\n" +
            "      \"businessBalance\": 1437880951,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.56\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.81,\n" +
            "      \"lastPx\": 3297.64,\n" +
            "      \"pxChange\": -22.6,\n" +
            "      \"pxChangeRate\": -0.68,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1039\",\n" +
            "      \"businessAmount\": 503980,\n" +
            "      \"businessBalance\": 668099998,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3308.98\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.7,\n" +
            "      \"lastPx\": 3294.33,\n" +
            "      \"pxChange\": -23.3,\n" +
            "      \"pxChangeRate\": -0.7,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1040\",\n" +
            "      \"businessAmount\": 796352,\n" +
            "      \"businessBalance\": 1021394952,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3304.98\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.6,\n" +
            "      \"lastPx\": 3293.63,\n" +
            "      \"pxChange\": -22.21,\n" +
            "      \"pxChangeRate\": -0.67,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1041\",\n" +
            "      \"businessAmount\": 583595,\n" +
            "      \"businessBalance\": 746905556,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.55\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.07,\n" +
            "      \"lastPx\": 3294.72,\n" +
            "      \"pxChange\": -23.03,\n" +
            "      \"pxChangeRate\": -0.69,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1042\",\n" +
            "      \"businessAmount\": 583257,\n" +
            "      \"businessBalance\": 706282793,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3306.74\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.54,\n" +
            "      \"lastPx\": 3293.91,\n" +
            "      \"pxChange\": -21.73,\n" +
            "      \"pxChangeRate\": -0.66,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1043\",\n" +
            "      \"businessAmount\": 471150,\n" +
            "      \"businessBalance\": 654128817,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3306.13\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.25,\n" +
            "      \"lastPx\": 3295.2,\n" +
            "      \"pxChange\": -20.89,\n" +
            "      \"pxChangeRate\": -0.63,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1044\",\n" +
            "      \"businessAmount\": 532309,\n" +
            "      \"businessBalance\": 662129652,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.39\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.39,\n" +
            "      \"lastPx\": 3296.04,\n" +
            "      \"pxChange\": -20.71,\n" +
            "      \"pxChangeRate\": -0.62,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1045\",\n" +
            "      \"businessAmount\": 491752,\n" +
            "      \"businessBalance\": 650092401,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.03\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.56,\n" +
            "      \"lastPx\": 3296.22,\n" +
            "      \"pxChange\": -20.83,\n" +
            "      \"pxChangeRate\": -0.63,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1046\",\n" +
            "      \"businessAmount\": 516323,\n" +
            "      \"businessBalance\": 685396874,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3308.34\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.62,\n" +
            "      \"lastPx\": 3296.1,\n" +
            "      \"pxChange\": -22.07,\n" +
            "      \"pxChangeRate\": -0.67,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1047\",\n" +
            "      \"businessAmount\": 182545,\n" +
            "      \"businessBalance\": 242462646,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.44\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.53,\n" +
            "      \"lastPx\": 3294.87,\n" +
            "      \"pxChange\": -21.71,\n" +
            "      \"pxChangeRate\": -0.65,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1048\",\n" +
            "      \"businessAmount\": 781883,\n" +
            "      \"businessBalance\": 1004198269,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3306.79\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.81,\n" +
            "      \"lastPx\": 3295.22,\n" +
            "      \"pxChange\": -21.4,\n" +
            "      \"pxChangeRate\": -0.65,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1049\",\n" +
            "      \"businessAmount\": 447770,\n" +
            "      \"businessBalance\": 606983898,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.67\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1296,\n" +
            "      \"lastPx\": 3295.54,\n" +
            "      \"pxChange\": -16.11,\n" +
            "      \"pxChangeRate\": -0.49,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1050\",\n" +
            "      \"businessAmount\": 398000,\n" +
            "      \"businessBalance\": 534669699,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.68\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.8,\n" +
            "      \"lastPx\": 3300.83,\n" +
            "      \"pxChange\": -17.7,\n" +
            "      \"pxChangeRate\": -0.53,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1051\",\n" +
            "      \"businessAmount\": 1117761,\n" +
            "      \"businessBalance\": 1428620411,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.05\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1296.28,\n" +
            "      \"lastPx\": 3299.24,\n" +
            "      \"pxChange\": -17.96,\n" +
            "      \"pxChangeRate\": -0.54,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1052\",\n" +
            "      \"businessAmount\": 670754,\n" +
            "      \"businessBalance\": 916691349,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3311.29\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1296.25,\n" +
            "      \"lastPx\": 3298.98,\n" +
            "      \"pxChange\": -17.45,\n" +
            "      \"pxChangeRate\": -0.53,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1053\",\n" +
            "      \"businessAmount\": 464107,\n" +
            "      \"businessBalance\": 598805719,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3311.23\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1296.48,\n" +
            "      \"lastPx\": 3299.48,\n" +
            "      \"pxChange\": -17.33,\n" +
            "      \"pxChangeRate\": -0.52,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1054\",\n" +
            "      \"businessAmount\": 431328,\n" +
            "      \"businessBalance\": 582027518,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3311.78\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1296.72,\n" +
            "      \"lastPx\": 3299.6,\n" +
            "      \"pxChange\": -18.02,\n" +
            "      \"pxChangeRate\": -0.54,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1055\",\n" +
            "      \"businessAmount\": 486143,\n" +
            "      \"businessBalance\": 653913691,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3311.58\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1297.09,\n" +
            "      \"lastPx\": 3298.92,\n" +
            "      \"pxChange\": -18.3,\n" +
            "      \"pxChangeRate\": -0.55,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1056\",\n" +
            "      \"businessAmount\": 449758,\n" +
            "      \"businessBalance\": 621115189,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3311.23\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1297.15,\n" +
            "      \"lastPx\": 3298.63,\n" +
            "      \"pxChange\": -19.55,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1057\",\n" +
            "      \"businessAmount\": 114976,\n" +
            "      \"businessBalance\": 155410289,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3311.26\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1297.28,\n" +
            "      \"lastPx\": 3297.38,\n" +
            "      \"pxChange\": -19.25,\n" +
            "      \"pxChangeRate\": -0.58,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1058\",\n" +
            "      \"businessAmount\": 765902,\n" +
            "      \"businessBalance\": 1006492311,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.51\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1297.34,\n" +
            "      \"lastPx\": 3297.69,\n" +
            "      \"pxChange\": -18.92,\n" +
            "      \"pxChangeRate\": -0.57,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1059\",\n" +
            "      \"businessAmount\": 383098,\n" +
            "      \"businessBalance\": 502856379,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.87\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1297.58,\n" +
            "      \"lastPx\": 3298.02,\n" +
            "      \"pxChange\": -16.64,\n" +
            "      \"pxChangeRate\": -0.5,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1100\",\n" +
            "      \"businessAmount\": 386060,\n" +
            "      \"businessBalance\": 525534036,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.83\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1297.54,\n" +
            "      \"lastPx\": 3300.3,\n" +
            "      \"pxChange\": -14.36,\n" +
            "      \"pxChangeRate\": -0.43,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1101\",\n" +
            "      \"businessAmount\": 617073,\n" +
            "      \"businessBalance\": 796519051,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3312.48\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1296.08,\n" +
            "      \"lastPx\": 3302.58,\n" +
            "      \"pxChange\": -14.6,\n" +
            "      \"pxChangeRate\": -0.44,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1102\",\n" +
            "      \"businessAmount\": 1095756,\n" +
            "      \"businessBalance\": 1270173830,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.03\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.25,\n" +
            "      \"lastPx\": 3302.34,\n" +
            "      \"pxChange\": -13.75,\n" +
            "      \"pxChangeRate\": -0.41,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1103\",\n" +
            "      \"businessAmount\": 834615,\n" +
            "      \"businessBalance\": 994339550,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.18\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.52,\n" +
            "      \"lastPx\": 3303.18,\n" +
            "      \"pxChange\": -13.96,\n" +
            "      \"pxChangeRate\": -0.42,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1104\",\n" +
            "      \"businessAmount\": 603517,\n" +
            "      \"businessBalance\": 810416574,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.44\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.27,\n" +
            "      \"lastPx\": 3302.97,\n" +
            "      \"pxChange\": -12.35,\n" +
            "      \"pxChangeRate\": -0.37,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1105\",\n" +
            "      \"businessAmount\": 649765,\n" +
            "      \"businessBalance\": 815101277,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3312.76\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1293.72,\n" +
            "      \"lastPx\": 3304.58,\n" +
            "      \"pxChange\": -11.96,\n" +
            "      \"pxChangeRate\": -0.36,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1106\",\n" +
            "      \"businessAmount\": 886089,\n" +
            "      \"businessBalance\": 981823296,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.87\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1293.46,\n" +
            "      \"lastPx\": 3304.97,\n" +
            "      \"pxChange\": -13.31,\n" +
            "      \"pxChangeRate\": -0.4,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1107\",\n" +
            "      \"businessAmount\": 717074,\n" +
            "      \"businessBalance\": 899497249,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.99\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1293.41,\n" +
            "      \"lastPx\": 3303.62,\n" +
            "      \"pxChange\": -13.09,\n" +
            "      \"pxChangeRate\": -0.39,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1108\",\n" +
            "      \"businessAmount\": 659851,\n" +
            "      \"businessBalance\": 847526349,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3312.65\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1293.72,\n" +
            "      \"lastPx\": 3303.84,\n" +
            "      \"pxChange\": -13.79,\n" +
            "      \"pxChangeRate\": -0.42,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1109\",\n" +
            "      \"businessAmount\": 511692,\n" +
            "      \"businessBalance\": 696147777,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3312.54\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1294.32,\n" +
            "      \"lastPx\": 3303.15,\n" +
            "      \"pxChange\": -13.34,\n" +
            "      \"pxChangeRate\": -0.4,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1110\",\n" +
            "      \"businessAmount\": 456264,\n" +
            "      \"businessBalance\": 656008369,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3311.88\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1294.65,\n" +
            "      \"lastPx\": 3303.59,\n" +
            "      \"pxChange\": -13.37,\n" +
            "      \"pxChangeRate\": -0.4,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1111\",\n" +
            "      \"businessAmount\": 484756,\n" +
            "      \"businessBalance\": 663054403,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.07\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.12,\n" +
            "      \"lastPx\": 3303.57,\n" +
            "      \"pxChange\": -12.55,\n" +
            "      \"pxChangeRate\": -0.38,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1112\",\n" +
            "      \"businessAmount\": 440375,\n" +
            "      \"businessBalance\": 622115111,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3312.26\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.36,\n" +
            "      \"lastPx\": 3304.39,\n" +
            "      \"pxChange\": -12.42,\n" +
            "      \"pxChangeRate\": -0.37,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1113\",\n" +
            "      \"businessAmount\": 454710,\n" +
            "      \"businessBalance\": 616151914,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3312.89\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.75,\n" +
            "      \"lastPx\": 3304.51,\n" +
            "      \"pxChange\": -10.19,\n" +
            "      \"pxChangeRate\": -0.31,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1114\",\n" +
            "      \"businessAmount\": 544948,\n" +
            "      \"businessBalance\": 748887306,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.33\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.8,\n" +
            "      \"lastPx\": 3306.74,\n" +
            "      \"pxChange\": -7.55,\n" +
            "      \"pxChangeRate\": -0.23,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1115\",\n" +
            "      \"businessAmount\": 693132,\n" +
            "      \"businessBalance\": 903206496,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3315.68\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.33,\n" +
            "      \"lastPx\": 3309.39,\n" +
            "      \"pxChange\": -6.54,\n" +
            "      \"pxChangeRate\": -0.2,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1116\",\n" +
            "      \"businessAmount\": 966419,\n" +
            "      \"businessBalance\": 1199619515,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3318\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295.34,\n" +
            "      \"lastPx\": 3310.39,\n" +
            "      \"pxChange\": -6.05,\n" +
            "      \"pxChangeRate\": -0.18,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1117\",\n" +
            "      \"businessAmount\": 869165,\n" +
            "      \"businessBalance\": 1126948617,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3318.4\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1295,\n" +
            "      \"lastPx\": 3310.88,\n" +
            "      \"pxChange\": -5.48,\n" +
            "      \"pxChangeRate\": -0.17,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1118\",\n" +
            "      \"businessAmount\": 292422,\n" +
            "      \"businessBalance\": 340362110,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3318.28\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1293.06,\n" +
            "      \"lastPx\": 3311.46,\n" +
            "      \"pxChange\": -4.61,\n" +
            "      \"pxChangeRate\": -0.14,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1119\",\n" +
            "      \"businessAmount\": 1415419,\n" +
            "      \"businessBalance\": 1608158406,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3318.63\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1292.94,\n" +
            "      \"lastPx\": 3312.32,\n" +
            "      \"pxChange\": -1.34,\n" +
            "      \"pxChangeRate\": -0.04,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1120\",\n" +
            "      \"businessAmount\": 776719,\n" +
            "      \"businessBalance\": 990034110,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3318.46\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.04,\n" +
            "      \"lastPx\": 3315.59,\n" +
            "      \"pxChange\": 2.14,\n" +
            "      \"pxChangeRate\": 0.06,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1121\",\n" +
            "      \"businessAmount\": 1325867,\n" +
            "      \"businessBalance\": 1372771396,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3321.68\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1288.16,\n" +
            "      \"lastPx\": 3319.07,\n" +
            "      \"pxChange\": 0.4,\n" +
            "      \"pxChangeRate\": 0.01,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1122\",\n" +
            "      \"businessAmount\": 1353400,\n" +
            "      \"businessBalance\": 1522147627,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3323.87\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1287.06,\n" +
            "      \"lastPx\": 3317.34,\n" +
            "      \"pxChange\": -0.01,\n" +
            "      \"pxChangeRate\": 0,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1123\",\n" +
            "      \"businessAmount\": 1146726,\n" +
            "      \"businessBalance\": 1345178716,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3322.14\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1286.37,\n" +
            "      \"lastPx\": 3316.93,\n" +
            "      \"pxChange\": -0.18,\n" +
            "      \"pxChangeRate\": -0.01,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1124\",\n" +
            "      \"businessAmount\": 775250,\n" +
            "      \"businessBalance\": 913887593,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3322.8\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1285.94,\n" +
            "      \"lastPx\": 3316.75,\n" +
            "      \"pxChange\": -1.4,\n" +
            "      \"pxChangeRate\": -0.04,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1125\",\n" +
            "      \"businessAmount\": 515372,\n" +
            "      \"businessBalance\": 611536931,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3321.88\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1285.79,\n" +
            "      \"lastPx\": 3315.53,\n" +
            "      \"pxChange\": -1.71,\n" +
            "      \"pxChangeRate\": -0.05,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1126\",\n" +
            "      \"businessAmount\": 736607,\n" +
            "      \"businessBalance\": 928398927,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3321.2\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1285.82,\n" +
            "      \"lastPx\": 3315.22,\n" +
            "      \"pxChange\": -4.16,\n" +
            "      \"pxChangeRate\": -0.13,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1127\",\n" +
            "      \"businessAmount\": 200237,\n" +
            "      \"businessBalance\": 260718164,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3319.79\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1285.71,\n" +
            "      \"lastPx\": 3312.64,\n" +
            "      \"pxChange\": -3.97,\n" +
            "      \"pxChangeRate\": -0.12,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1128\",\n" +
            "      \"businessAmount\": 848326,\n" +
            "      \"businessBalance\": 1077436520,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3319.51\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1285.77,\n" +
            "      \"lastPx\": 3312.97,\n" +
            "      \"pxChange\": -4.05,\n" +
            "      \"pxChangeRate\": -0.12,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1129\",\n" +
            "      \"businessAmount\": 531273,\n" +
            "      \"businessBalance\": 690496667,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3319.47\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1285.58,\n" +
            "      \"lastPx\": 3312.88,\n" +
            "      \"pxChange\": -4.05,\n" +
            "      \"pxChangeRate\": -0.12,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1130\",\n" +
            "      \"businessAmount\": 391566,\n" +
            "      \"businessBalance\": 480688223,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3319.46\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1284.18,\n" +
            "      \"lastPx\": 3307.38,\n" +
            "      \"pxChange\": -8.81,\n" +
            "      \"pxChangeRate\": -0.27,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1301\",\n" +
            "      \"businessAmount\": 1715099,\n" +
            "      \"businessBalance\": 2027926695,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3315.5\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1284.39,\n" +
            "      \"lastPx\": 3308.13,\n" +
            "      \"pxChange\": -8.41,\n" +
            "      \"pxChangeRate\": -0.25,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1302\",\n" +
            "      \"businessAmount\": 399962,\n" +
            "      \"businessBalance\": 540095099,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3316.88\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1284.92,\n" +
            "      \"lastPx\": 3308.52,\n" +
            "      \"pxChange\": -8.96,\n" +
            "      \"pxChangeRate\": -0.27,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1303\",\n" +
            "      \"businessAmount\": 340763,\n" +
            "      \"businessBalance\": 505083856,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3316.95\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1285.15,\n" +
            "      \"lastPx\": 3307.97,\n" +
            "      \"pxChange\": -8.97,\n" +
            "      \"pxChangeRate\": -0.27,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1304\",\n" +
            "      \"businessAmount\": 349008,\n" +
            "      \"businessBalance\": 476961926,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3316.29\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1285.56,\n" +
            "      \"lastPx\": 3307.75,\n" +
            "      \"pxChange\": -11.11,\n" +
            "      \"pxChangeRate\": -0.33,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1305\",\n" +
            "      \"businessAmount\": 334856,\n" +
            "      \"businessBalance\": 483511478,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3316.19\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1286.13,\n" +
            "      \"lastPx\": 3305.83,\n" +
            "      \"pxChange\": -10.33,\n" +
            "      \"pxChangeRate\": -0.31,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1306\",\n" +
            "      \"businessAmount\": 434758,\n" +
            "      \"businessBalance\": 631163391,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.27\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1286.47,\n" +
            "      \"lastPx\": 3306.6,\n" +
            "      \"pxChange\": -10.62,\n" +
            "      \"pxChangeRate\": -0.32,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1307\",\n" +
            "      \"businessAmount\": 412045,\n" +
            "      \"businessBalance\": 573432003,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.71\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1286.58,\n" +
            "      \"lastPx\": 3306.32,\n" +
            "      \"pxChange\": -10.81,\n" +
            "      \"pxChangeRate\": -0.33,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1308\",\n" +
            "      \"businessAmount\": 396710,\n" +
            "      \"businessBalance\": 524447329,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.24\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1286.63,\n" +
            "      \"lastPx\": 3306.12,\n" +
            "      \"pxChange\": -11.29,\n" +
            "      \"pxChangeRate\": -0.34,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1309\",\n" +
            "      \"businessAmount\": 377712,\n" +
            "      \"businessBalance\": 492542108,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.26\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1286.66,\n" +
            "      \"lastPx\": 3305.65,\n" +
            "      \"pxChange\": -12.55,\n" +
            "      \"pxChangeRate\": -0.38,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1310\",\n" +
            "      \"businessAmount\": 379743,\n" +
            "      \"businessBalance\": 492655652,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.29\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1286.63,\n" +
            "      \"lastPx\": 3304.39,\n" +
            "      \"pxChange\": -11.46,\n" +
            "      \"pxChangeRate\": -0.35,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1311\",\n" +
            "      \"businessAmount\": 598953,\n" +
            "      \"businessBalance\": 767133098,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.26\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1286.89,\n" +
            "      \"lastPx\": 3305.48,\n" +
            "      \"pxChange\": -12.22,\n" +
            "      \"pxChangeRate\": -0.37,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1312\",\n" +
            "      \"businessAmount\": 467814,\n" +
            "      \"businessBalance\": 635413132,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.9\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1287.05,\n" +
            "      \"lastPx\": 3304.72,\n" +
            "      \"pxChange\": -12.74,\n" +
            "      \"pxChangeRate\": -0.38,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1313\",\n" +
            "      \"businessAmount\": 438711,\n" +
            "      \"businessBalance\": 585857808,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.48\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1286.88,\n" +
            "      \"lastPx\": 3304.19,\n" +
            "      \"pxChange\": -12.61,\n" +
            "      \"pxChangeRate\": -0.38,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1314\",\n" +
            "      \"businessAmount\": 441119,\n" +
            "      \"businessBalance\": 544894670,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.77\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1287.03,\n" +
            "      \"lastPx\": 3304.33,\n" +
            "      \"pxChange\": -12.69,\n" +
            "      \"pxChangeRate\": -0.38,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1315\",\n" +
            "      \"businessAmount\": 410526,\n" +
            "      \"businessBalance\": 548190984,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.57\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1287.44,\n" +
            "      \"lastPx\": 3304.24,\n" +
            "      \"pxChange\": -12.68,\n" +
            "      \"pxChangeRate\": -0.38,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1316\",\n" +
            "      \"businessAmount\": 384657,\n" +
            "      \"businessBalance\": 549262320,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.49\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1287.89,\n" +
            "      \"lastPx\": 3304.35,\n" +
            "      \"pxChange\": -12.55,\n" +
            "      \"pxChangeRate\": -0.38,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1317\",\n" +
            "      \"businessAmount\": 378009,\n" +
            "      \"businessBalance\": 546420676,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.11\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1287.95,\n" +
            "      \"lastPx\": 3304.81,\n" +
            "      \"pxChange\": -12.42,\n" +
            "      \"pxChangeRate\": -0.37,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1318\",\n" +
            "      \"businessAmount\": 403827,\n" +
            "      \"businessBalance\": 528117042,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.84\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1288.51,\n" +
            "      \"lastPx\": 3306.05,\n" +
            "      \"pxChange\": -12.42,\n" +
            "      \"pxChangeRate\": -0.37,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1319\",\n" +
            "      \"businessAmount\": 382010,\n" +
            "      \"businessBalance\": 566978110,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3315.46\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1288.95,\n" +
            "      \"lastPx\": 3304.52,\n" +
            "      \"pxChange\": -11.86,\n" +
            "      \"pxChangeRate\": -0.36,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1320\",\n" +
            "      \"businessAmount\": 490760,\n" +
            "      \"businessBalance\": 691119991,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.83\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.14,\n" +
            "      \"lastPx\": 3305.08,\n" +
            "      \"pxChange\": -12.87,\n" +
            "      \"pxChangeRate\": -0.39,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1321\",\n" +
            "      \"businessAmount\": 442122,\n" +
            "      \"businessBalance\": 595020780,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.18\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.2,\n" +
            "      \"lastPx\": 3304.06,\n" +
            "      \"pxChange\": -14.49,\n" +
            "      \"pxChangeRate\": -0.44,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1322\",\n" +
            "      \"businessAmount\": 451937,\n" +
            "      \"businessBalance\": 590559398,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.46\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.05,\n" +
            "      \"lastPx\": 3302.44,\n" +
            "      \"pxChange\": -13.03,\n" +
            "      \"pxChangeRate\": -0.39,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1323\",\n" +
            "      \"businessAmount\": 532661,\n" +
            "      \"businessBalance\": 666848195,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3312.58\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.17,\n" +
            "      \"lastPx\": 3303.9,\n" +
            "      \"pxChange\": -13.5,\n" +
            "      \"pxChangeRate\": -0.41,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1324\",\n" +
            "      \"businessAmount\": 481289,\n" +
            "      \"businessBalance\": 635838997,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.62\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.4,\n" +
            "      \"lastPx\": 3303.44,\n" +
            "      \"pxChange\": -11.71,\n" +
            "      \"pxChangeRate\": -0.35,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1325\",\n" +
            "      \"businessAmount\": 400169,\n" +
            "      \"businessBalance\": 548160900,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.57\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.04,\n" +
            "      \"lastPx\": 3305.23,\n" +
            "      \"pxChange\": -12.31,\n" +
            "      \"pxChangeRate\": -0.37,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1326\",\n" +
            "      \"businessAmount\": 434464,\n" +
            "      \"businessBalance\": 647380986,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.88\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.35,\n" +
            "      \"lastPx\": 3304.63,\n" +
            "      \"pxChange\": -11.74,\n" +
            "      \"pxChangeRate\": -0.35,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1327\",\n" +
            "      \"businessAmount\": 448765,\n" +
            "      \"businessBalance\": 621611726,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.96\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.64,\n" +
            "      \"lastPx\": 3305.19,\n" +
            "      \"pxChange\": -11.92,\n" +
            "      \"pxChangeRate\": -0.36,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1328\",\n" +
            "      \"businessAmount\": 377269,\n" +
            "      \"businessBalance\": 525834542,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.51\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.84,\n" +
            "      \"lastPx\": 3305.01,\n" +
            "      \"pxChange\": -12.66,\n" +
            "      \"pxChangeRate\": -0.38,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1329\",\n" +
            "      \"businessAmount\": 365460,\n" +
            "      \"businessBalance\": 499724690,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.35\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1291.18,\n" +
            "      \"lastPx\": 3304.28,\n" +
            "      \"pxChange\": -13.02,\n" +
            "      \"pxChangeRate\": -0.39,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1330\",\n" +
            "      \"businessAmount\": 336591,\n" +
            "      \"businessBalance\": 481701067,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.24\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1291.59,\n" +
            "      \"lastPx\": 3303.92,\n" +
            "      \"pxChange\": -13.71,\n" +
            "      \"pxChangeRate\": -0.41,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1331\",\n" +
            "      \"businessAmount\": 438177,\n" +
            "      \"businessBalance\": 622140045,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3314.29\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1291.77,\n" +
            "      \"lastPx\": 3303.23,\n" +
            "      \"pxChange\": -16.41,\n" +
            "      \"pxChangeRate\": -0.49,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1332\",\n" +
            "      \"businessAmount\": 447129,\n" +
            "      \"businessBalance\": 602862282,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3313.93\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1291.59,\n" +
            "      \"lastPx\": 3300.53,\n" +
            "      \"pxChange\": -16.35,\n" +
            "      \"pxChangeRate\": -0.49,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1333\",\n" +
            "      \"businessAmount\": 676687,\n" +
            "      \"businessBalance\": 848409286,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3311.15\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1291.68,\n" +
            "      \"lastPx\": 3300.59,\n" +
            "      \"pxChange\": -17.89,\n" +
            "      \"pxChangeRate\": -0.54,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1334\",\n" +
            "      \"businessAmount\": 580412,\n" +
            "      \"businessBalance\": 762121142,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3310.37\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1291.28,\n" +
            "      \"lastPx\": 3299.05,\n" +
            "      \"pxChange\": -17.6,\n" +
            "      \"pxChangeRate\": -0.53,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1335\",\n" +
            "      \"businessAmount\": 678625,\n" +
            "      \"businessBalance\": 821213360,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.51\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.88,\n" +
            "      \"lastPx\": 3299.33,\n" +
            "      \"pxChange\": -18.24,\n" +
            "      \"pxChangeRate\": -0.55,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1336\",\n" +
            "      \"businessAmount\": 589777,\n" +
            "      \"businessBalance\": 704489253,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.68\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.99,\n" +
            "      \"lastPx\": 3298.69,\n" +
            "      \"pxChange\": -18.32,\n" +
            "      \"pxChangeRate\": -0.55,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1337\",\n" +
            "      \"businessAmount\": 444222,\n" +
            "      \"businessBalance\": 589497146,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.36\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.82,\n" +
            "      \"lastPx\": 3298.61,\n" +
            "      \"pxChange\": -18.98,\n" +
            "      \"pxChangeRate\": -0.57,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1338\",\n" +
            "      \"businessAmount\": 109165,\n" +
            "      \"businessBalance\": 116417960,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.25\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.9,\n" +
            "      \"lastPx\": 3297.95,\n" +
            "      \"pxChange\": -18.22,\n" +
            "      \"pxChangeRate\": -0.55,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1339\",\n" +
            "      \"businessAmount\": 955476,\n" +
            "      \"businessBalance\": 1102203193,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3308.68\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.82,\n" +
            "      \"lastPx\": 3298.71,\n" +
            "      \"pxChange\": -16.89,\n" +
            "      \"pxChangeRate\": -0.51,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1340\",\n" +
            "      \"businessAmount\": 495101,\n" +
            "      \"businessBalance\": 627121383,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3308.32\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.41,\n" +
            "      \"lastPx\": 3300.05,\n" +
            "      \"pxChange\": -18.99,\n" +
            "      \"pxChangeRate\": -0.57,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1341\",\n" +
            "      \"businessAmount\": 634810,\n" +
            "      \"businessBalance\": 759002427,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3310.37\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.29,\n" +
            "      \"lastPx\": 3297.95,\n" +
            "      \"pxChange\": -18.02,\n" +
            "      \"pxChangeRate\": -0.54,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1342\",\n" +
            "      \"businessAmount\": 484811,\n" +
            "      \"businessBalance\": 608088441,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3308.64\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.41,\n" +
            "      \"lastPx\": 3298.91,\n" +
            "      \"pxChange\": -19.12,\n" +
            "      \"pxChangeRate\": -0.58,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1343\",\n" +
            "      \"businessAmount\": 411897,\n" +
            "      \"businessBalance\": 548959381,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3308.07\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.42,\n" +
            "      \"lastPx\": 3297.81,\n" +
            "      \"pxChange\": -20.02,\n" +
            "      \"pxChangeRate\": -0.6,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1344\",\n" +
            "      \"businessAmount\": 251882,\n" +
            "      \"businessBalance\": 325620078,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.71\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.31,\n" +
            "      \"lastPx\": 3296.91,\n" +
            "      \"pxChange\": -19.97,\n" +
            "      \"pxChangeRate\": -0.6,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1345\",\n" +
            "      \"businessAmount\": 651430,\n" +
            "      \"businessBalance\": 823582268,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.03\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.4,\n" +
            "      \"lastPx\": 3296.96,\n" +
            "      \"pxChange\": -19.58,\n" +
            "      \"pxChangeRate\": -0.59,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1346\",\n" +
            "      \"businessAmount\": 476047,\n" +
            "      \"businessBalance\": 626788502,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3306.31\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.37,\n" +
            "      \"lastPx\": 3297.36,\n" +
            "      \"pxChange\": -17.47,\n" +
            "      \"pxChangeRate\": -0.53,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1347\",\n" +
            "      \"businessAmount\": 423520,\n" +
            "      \"businessBalance\": 542187300,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3306.64\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.68,\n" +
            "      \"lastPx\": 3299.47,\n" +
            "      \"pxChange\": -16.63,\n" +
            "      \"pxChangeRate\": -0.5,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1348\",\n" +
            "      \"businessAmount\": 441721,\n" +
            "      \"businessBalance\": 615874694,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.62\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.97,\n" +
            "      \"lastPx\": 3300.3,\n" +
            "      \"pxChange\": -17,\n" +
            "      \"pxChangeRate\": -0.51,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1349\",\n" +
            "      \"businessAmount\": 377488,\n" +
            "      \"businessBalance\": 528295793,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3308\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.88,\n" +
            "      \"lastPx\": 3299.93,\n" +
            "      \"pxChange\": -16.35,\n" +
            "      \"pxChangeRate\": -0.49,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1350\",\n" +
            "      \"businessAmount\": 421621,\n" +
            "      \"businessBalance\": 531694843,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.87\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.05,\n" +
            "      \"lastPx\": 3300.59,\n" +
            "      \"pxChange\": -15.93,\n" +
            "      \"pxChangeRate\": -0.48,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1351\",\n" +
            "      \"businessAmount\": 409961,\n" +
            "      \"businessBalance\": 553468860,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.14\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.16,\n" +
            "      \"lastPx\": 3301,\n" +
            "      \"pxChange\": -15.93,\n" +
            "      \"pxChangeRate\": -0.48,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1352\",\n" +
            "      \"businessAmount\": 426369,\n" +
            "      \"businessBalance\": 566471430,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.71\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.31,\n" +
            "      \"lastPx\": 3301,\n" +
            "      \"pxChange\": -15.38,\n" +
            "      \"pxChangeRate\": -0.46,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1353\",\n" +
            "      \"businessAmount\": 365866,\n" +
            "      \"businessBalance\": 494417831,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3309.51\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.51,\n" +
            "      \"lastPx\": 3301.55,\n" +
            "      \"pxChange\": -15.79,\n" +
            "      \"pxChangeRate\": -0.48,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1354\",\n" +
            "      \"businessAmount\": 371196,\n" +
            "      \"businessBalance\": 508198491,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3310.16\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.24,\n" +
            "      \"lastPx\": 3301.15,\n" +
            "      \"pxChange\": -17.6,\n" +
            "      \"pxChangeRate\": -0.53,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1355\",\n" +
            "      \"businessAmount\": 360377,\n" +
            "      \"businessBalance\": 424940026,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.99\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.2,\n" +
            "      \"lastPx\": 3299.33,\n" +
            "      \"pxChange\": -17.76,\n" +
            "      \"pxChangeRate\": -0.54,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1356\",\n" +
            "      \"businessAmount\": 703273,\n" +
            "      \"businessBalance\": 901779777,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3307.04\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1290.18,\n" +
            "      \"lastPx\": 3299.18,\n" +
            "      \"pxChange\": -18.47,\n" +
            "      \"pxChangeRate\": -0.56,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1357\",\n" +
            "      \"businessAmount\": 447808,\n" +
            "      \"businessBalance\": 574945339,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3306.97\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.96,\n" +
            "      \"lastPx\": 3298.46,\n" +
            "      \"pxChange\": -20.73,\n" +
            "      \"pxChangeRate\": -0.62,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1358\",\n" +
            "      \"businessAmount\": 425144,\n" +
            "      \"businessBalance\": 514850328,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3305.96\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1289.11,\n" +
            "      \"lastPx\": 3296.2,\n" +
            "      \"pxChange\": -20.73,\n" +
            "      \"pxChangeRate\": -0.63,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1359\",\n" +
            "      \"businessAmount\": 731402,\n" +
            "      \"businessBalance\": 813377601,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3304.07\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1288.62,\n" +
            "      \"lastPx\": 3296.2,\n" +
            "      \"pxChange\": -20.9,\n" +
            "      \"pxChangeRate\": -0.63,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1400\",\n" +
            "      \"businessAmount\": 449223,\n" +
            "      \"businessBalance\": 505207608,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3304.02\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1288.1,\n" +
            "      \"lastPx\": 3296.03,\n" +
            "      \"pxChange\": -21.11,\n" +
            "      \"pxChangeRate\": -0.64,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1401\",\n" +
            "      \"businessAmount\": 857938,\n" +
            "      \"businessBalance\": 1025847157,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3303.74\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1287.78,\n" +
            "      \"lastPx\": 3295.83,\n" +
            "      \"pxChange\": -21.43,\n" +
            "      \"pxChangeRate\": -0.65,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1402\",\n" +
            "      \"businessAmount\": 571568,\n" +
            "      \"businessBalance\": 686410901,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3303.26\n" +
            "    },\n" +
            "    {\n" +
            "      \"prodCode\": \"000001\",\n" +
            "      \"avgPx\": 1287.66,\n" +
            "      \"lastPx\": 3295.51,\n" +
            "      \"pxChange\": -21.11,\n" +
            "      \"pxChangeRate\": -0.64,\n" +
            "      \"date\": 20200922,\n" +
            "      \"businessTime\": \"1403\",\n" +
            "      \"businessAmount\": 142467,\n" +
            "      \"businessBalance\": 164658235,\n" +
            "      \"amount\": 0,\n" +
            "      \"wavgPx\": 3303.02\n" +
            "    }\n" +
            "  ],\n" +
            "  \"count\": null\n" +
            "}";
}
