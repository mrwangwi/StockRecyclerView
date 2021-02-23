package com.main.hz.okhttp.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

public class MyTextUtil {


    public static boolean isTelephone(String tel) {
        String regex = "^1[3456789]\\d{9}$";
        return Pattern.matches(regex, tel);
    }

    /**
     * 判断空值
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        if (s.equals("[]"))
            return true;
        if (s.equals(""))
            return true;
        if (s.equals("null"))
            return true;
        if (s.equals("Null"))
            return true;
        if (s.equals("NULL"))
            return true;
        if (s.equals("--"))
            return true;
        return false;
    }

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static double parseDouble(String s) {
        if (isEmpty(s)) return 0;
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return 0;
        }
    }

    public static float parseFloat(String s) {
        if (isEmpty(s)) return 0;
        try {
            return Float.parseFloat(s);
        } catch (Exception e) {
            return 0;
        }
    }

    public static Integer parseInt(String s) {
        if (isEmpty(s)) return 0;
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String toIntFloor(String number) {
        double d = parseDouble(number);
        return ((int) d) + "";
    }

    public static String getChangeAmount(String amount) {
        if (isEmpty(amount)) return "";
        double amo;
        try {
            amo = Double.parseDouble(amount);
        } catch (Exception e) {
            return amount;
        }
        if (amo >= 100000000) {
            return doubleFormat(amo / 100000000.0) + "亿";
        } else if (amo > 10000) {
            return doubleFormat(amo / 10000.0) + "万";
        } else if (amo < -100000000) {
            return doubleFormat(amo / 100000000.0) + "亿";
        } else if (amo < -10000) {
            return doubleFormat(amo / 10000.0) + "万";
        } else {
            return doubleFormat(amount);
        }
    }

    public static String getChangeAmountRound(String amount) {
        if (isEmpty(amount)) return "";
        double amo;
        try {
            amo = Double.parseDouble(amount);
        } catch (Exception e) {
            return amount;
        }
        if (amo >= 100000000) {

            return Math.round(amo / 100000000.0) + "亿";
        } else if (amo > 10000) {
            return Math.round(amo / 10000.0) + "万";
        } else if (amo < -100000000) {
            return Math.round(amo / 100000000.0) + "亿";
        } else if (amo < -10000) {
            return Math.round(amo / 10000.0) + "万";
        } else {
            return Math.round(MyTextUtil.parseDouble(amount)) + "";
        }
    }

    public static String getChangeAmountWithAdd(String amount) {
        if (isEmpty(amount)) return "";
        double amo;
        try {
            amo = Double.parseDouble(amount);
        } catch (Exception e) {
            return amount;
        }
        if (amo >= 100000000) {
            return "+" + doubleFormat(amo / 100000000.0) + "亿";
        } else if (amo > 10000) {
            return "+" + doubleFormat(amo / 10000.0) + "万";
        } else if (amo < -100000000) {
            return doubleFormat(amo / 100000000.0) + "亿";
        } else if (amo < -10000) {
            return doubleFormat(amo / 10000.0) + "万";
        } else {
            return doubleFormat(amount);
        }
    }


    public static String getChangeAmountInteger(String amount) {
        if (isEmpty(amount)) return "";
        double amo;
        try {
            amo = Double.parseDouble(amount);
        } catch (Exception e) {
            return amount;
        }
        if (amo >= 100000000) {
            return doubleFormat(amo / 100000000.0) + "亿";
        } else if (amo > 10000) {
            return doubleFormat(amo / 10000.0) + "万";
        } else if (amo < -100000000) {
            return doubleFormat(amo / 100000000.0) + "亿";
        } else if (amo < -10000) {
            return doubleFormat(amo / 10000.0) + "万";
        } else {
            return amount;
        }
    }

    public static String getChangeAmountInt(String min, String max) {
        if (isEmpty(min)) return "";
        if (isEmpty(max)) return "";
        int mi;
        int ma;
        try {
            mi = Integer.parseInt(min);
            ma = Integer.parseInt(max);
        } catch (Exception e) {
            return min + "-" + max;
        }
        if (ma > 10000 && mi > 10000) {
            return mi / 10000 + "-" + ma / 10000;
        } else if (ma < 10000 && mi < 10000) {
            return mi + "-" + ma;
        } else {
            return mi + "-" + ma / 10000;
        }
    }

    /**
     * 判断空值
     *
     * @param
     * @return
     */
    public static boolean isEmpty(TextView editText) {
        String s = editText.getText().toString().trim();
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        if (s.equals("[]"))
            return true;
        if (s.equals(""))
            return true;
        if (s.equals("null"))
            return true;
        if (s.equals("Null"))
            return true;
        if (s.equals("NULL"))
            return true;
        if (s.equals("0"))
            return true;
        return false;
    }

    /**
     * @param
     * @return
     */
    public static int getIntegerFromView(TextView editText) {
        if (isEmpty(editText)) {
            return 0;
        } else {
            return Integer.parseInt(editText.getText().toString().trim());
        }
    }

    /**
     * @param
     * @return
     */
    public static double getDoubleFromView(TextView editText) {
        if (isEmpty(editText)) {
            return 0.00;
        } else {
            return Double.parseDouble(editText.getText().toString().trim());
        }
    }

    /**
     * 转百分比
     */
    public static String getPercentage(double d) {
        int num = (int) (d * 100);
        return num + "%";
    }

    /**
     * double类型保留两位小数
     *
     * @param db
     * @return
     */
    public static String doubleFormat(Double db) {
        DecimalFormat df = new DecimalFormat("#0.00");
        try {
            return getStringOutE(df.format(db));
        } catch (Exception e) {
            return "--";
        }
    }

    /**
     * double类型保留两位小数
     *
     * @param db
     * @return
     */
    public static String doubleFormatWithAdd(String db) {
        if (isEmpty(db)) return "0.00";
        DecimalFormat df = new DecimalFormat("#0.00");

        try {
            if (MyTextUtil.parseDouble(db) > 0) {
                return "+" + getStringOutE(df.format(new BigDecimal(db)));
            } else {
                return getStringOutE(df.format(new BigDecimal(db)));
            }
        } catch (Exception e) {
            return "--";
        }
    }

    /**
     * double类型保留两位小数
     *
     * @param db
     * @return
     */
    public static String doubleFormat(String db) {
        if (isEmpty(db)) return "0.00";
        DecimalFormat df = new DecimalFormat("#0.00");

        try {
            return getStringOutE(df.format(new BigDecimal(db)));
        } catch (Exception e) {
            return "--";
        }
    }

    /**
     * double类型保留两位小数
     *
     * @param db
     * @return
     */
    public static String doubleFormat(String db, int chuyi) {
        if (isEmpty(db)) return "0.00";
        DecimalFormat df = new DecimalFormat("#0.00");
        if (chuyi != 0) {
            double d = parseDouble(db);
            return getStringOutE(df.format(new BigDecimal(d / chuyi)));
        }
        try {
            return getStringOutE(df.format(new BigDecimal(db)));
        } catch (Exception e) {
            return "--";
        }
    }


    public static String changeSdkTime(int time) {
        if (time < 10000) {
            return "";
        }
        String t = time + "";

        if (t.length() < 9) {
            t = "0" + t;
        }
        return t.substring(0, 2) + ":" + t.substring(2, 4);
    }

    public static String changeTimeSimple(String t) {
        if (t.length() < 4) {
            t = "0" + t;
        }
        return t.substring(0, 2) + ":" + t.substring(2, 4);
    }

    /**
     * double类型保留一位小数
     *
     * @param db
     * @return
     */
    public static String singleFormat(String db) {
        if (isEmpty(db)) return "0.0";
        DecimalFormat df = new DecimalFormat("#0.0");
        return getStringOutE(df.format(Double.parseDouble(db)));
    }

    public static String timeYMDHmsFormat(String time) {
        if (isEmpty(time)) return "";
        long l = Long.parseLong(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return format.format(l);
    }

    public static String timeYMDFormat(String time) {
        if (isEmpty(time)) return "";
        long l = Long.parseLong(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(l);
    }

    public static String timeFormat(String time, String format, String format1) {
        if (isEmpty(time)) return "";
        SimpleDateFormat s = new SimpleDateFormat(format);
        SimpleDateFormat s1 = new SimpleDateFormat(format1);

        try {
            return s1.format(s.parse(time));
        } catch (ParseException e) {
            return time;
        }
    }

    /**
     * 金额添加逗号
     *
     * @param
     * @return
     */
    public static String getStringWithComma(String money) {
        if (isEmpty(money)) return "0.00";
        DecimalFormat df = new DecimalFormat("##,##0.00");
        return df.format(Double.parseDouble(money));
    }

    /**
     * 科学计数法转长字符串
     *
     * @param
     * @return
     */
    public static String getStringOutE(String str) {
        BigDecimal bd = new BigDecimal(str);
        return bd.toPlainString();
    }

    /**
     * @param
     * @return
     */
    public static boolean isZeroStart(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text.startsWith("0") && !text.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String getVolUnit(String vol) {

        double num = parseDouble(vol);

        int e = (int) Math.floor(Math.log10(num));

        if (e >= 8) {
            return doubleFormat(num / 100000000.0) + "亿";
        } else if (e >= 4) {
            return doubleFormat(num / 10000.0) + "万";
        } else {
            return (int) num + "";
        }
    }

    public static String getSysTimeString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String subtractionInteger(String number, int value) {
        if (isEmpty(number)) return "0";
        try {
            int num = Integer.parseInt(number) + value;
            return num < 0 ? "0" : num + "";
        } catch (Exception e) {
            return "0";
        }
    }


    public static float doubleRound(float ft) {
        int scale = 2;
        int roundingMode = 4;//表示四舍五入
        BigDecimal bd = new BigDecimal((double) ft);
        bd = bd.setScale(scale, roundingMode);
        return bd.floatValue();
    }


    public static String getImageUrlWithSplit(String urls) {
        if (urls == null) return null;
        String[] strings = urls.split(",");
        if (strings.length > 0) {
            return strings[0];
        } else {
            return urls;
        }
    }

    public static String getBankNoPassword(String number) {
        if (isEmpty(number)) return "";
        if (number.length() > 10) {
            if (number.length() % 2 == 0) {
                String str = "";
                for (int i = 0; i < number.length() / 4 - 2; i++) {
                    str += "  ****";
                }
                return number.substring(0, 4) + str + "  " + number.substring(number.length() - 4);
            } else {
                String str = "";
                for (int i = 0; i < (number.length() - 3) / 4 - 1; i++) {
                    str += "  ****";
                }
                return number.substring(0, 4) + str + "  " + number.substring(number.length() - 3);
            }
        } else {
            return number;
        }
    }


    public static String getPhoneNoPassword(String number) {
        if (isEmpty(number) || !isTelephone(number)) return "";
        return number.substring(0, 3) + "****" + number.substring(number.length() - 4);
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            StringBuffer hexString1 = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (i != 0)
                    hexString.append(":");
                if (appendString.length() == 1) {
                    hexString.append("0");
                    hexString1.append("0");
                }
                hexString.append(appendString);
                hexString1.append(appendString);
            }
            Log.e("sHA1: ", hexString.toString());
            Log.e("sHA1: ", hexString1.toString());
            return hexString.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getStringList(String s, boolean ems) {
        if (ems) {
            if (isEmpty(s)) return new ArrayList<>();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                list.add(s.substring(i, i + 1));
            }
            return list;
        } else {
            return getStringList(s);
        }
    }

    public static List<String> getStringList(String s) {
        if (isEmpty(s)) return new ArrayList<>();

        List<String> list = new ArrayList<>();
        String[] strings = s.split(",");

        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }
        return list;
    }

    public static List<String> getStringList(int size) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add("");
        }
        return list;
    }

    public static List<String> getStringList(int size, int random) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(getRandomString(random));
        }
        return list;
    }

    public static String getStringForList(List<String> list) {
        if (list == null || list.size() == 0) return "";
        String sr = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                sr = list.get(i);
            } else {
                sr = sr + "," + list.get(i);
            }
        }
        return sr;
    }

    public static List<Integer> getIntList(int size, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((int) (Math.random() * max));
        }
        return list;
    }

    public static Date getKTime(int time) {
        String t;
        if (time < 1000) {
            t = "0" + time;
        } else {
            t = "" + time;
        }
        SimpleDateFormat format = new SimpleDateFormat("HHmm");
        try {
            return format.parse(t);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String getKDate(int date) {
        String t = date + "";
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return format2.format(format1.parse(t));
        } catch (ParseException e) {
            return null;
        }
    }


    public static String getStringDeleteStr(String oldStr, String deletStr) {
        if (oldStr == null) return "0";
        if (oldStr.contains("--")) {
            oldStr = oldStr.replaceAll("--", "");
        }
        if (isEmpty(oldStr.replaceAll(deletStr, ""))) {
            return "0";
        } else {
            return oldStr.replaceAll(deletStr, "");
        }
    }

    public static String getSize(int size) {
        return doubleFormat(size / 1024100.0) + "M";
    }

    public static String getStockNumberWithType(int number, int type, boolean isPurchase) {
        if (isPurchase) {
            return number / type / 100 * 100 + "";
        } else {
            if (type == 1) {
                return number + "";
            } else {
                return number / type / 100 * 100 + "";
            }
        }
    }

    public static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getState(int entrustStatus) {
        switch (entrustStatus) {
            case 0:
                return "未报";
            case 1:
                return "待报";
            case 2:
                return "已报";
            case 3:
                return "未成交";
            case 4:
                return "部成待撤";
            case 5:
                return "部分撤单";
            case 6:
                return "全部撤单";
            case 7:
                return "部分成交";
            case 8:
                return "全部成交";
            case 9:
                return "废单";
        }
        return "";
    }


    public static int getColorByRate(String rate) {
        double newRate = parseDouble(rate);
        if (newRate > 0) {
            return Color.parseColor("#E73146");
        } else if (newRate < 0) {
            return Color.parseColor("#07A167");
        } else {
            return Color.parseColor("#333333");
        }
    }


    public static String getTextDec(String rate) {
        double newRate = parseDouble(rate);
        if (newRate >= 5) {
            return "极强";
        } else if (newRate >= 2) {
            return "强势";
        } else if (newRate >= 0) {
            return "较强";
        } else if (newRate >= -2) {
            return "较弱";
        } else {
            return "弱势";
        }
    }

    public static String getPateDec(String plate) {

        if (isEmpty(plate)) return "";

        if (plate.equals("二板")) {
            return "未涨停的昨日一板股";
        } else if (plate.equals("三板")) {
            return "未涨停的昨日二板股";
        } else if (plate.equals("四板")) {
            return "未涨停的昨日三板股";
        } else if (plate.equals("更高")) {
            return "未涨停的昨日四板及以上股";
        }
        return "";
    }


    public static String getNextDay(String data, boolean isNext, boolean isYear) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        try {
            Date date = sdf.parse(data);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(isYear ? calendar.YEAR : calendar.MONTH, isNext ? 1 : -1);//把日期往后增加一天.整数往后推,负数往前移动
            date = calendar.getTime();   //这个时间就是日期往后推一天的结果
            String putDate = sdf1.format(date); //增加一天后的日期
            return putDate;
        } catch (ParseException e) {
            return "";
        }
    }
}
