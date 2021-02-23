package com.github.tifezh.kchartlib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

public class TextUtil {


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
        return false;
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

    public static String getChangeAmountIntegerReturnInt(String amount) {
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
            return (int) amo + "";
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
    public static String doubleFormatInt(Double db) {
        DecimalFormat df = new DecimalFormat("#0");
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


    public static String changeSdkMoney(long l) {
        return doubleFormat(l / 1000.0);
    }

    public static String changeSdkRate(long l) {
        return doubleFormat(l / 100.0) + "%";
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

    public static String changeSdkTimeSimple(int time) {
        if (time < 900) {
            return "";
        }
        String t = time + "";

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

        long num = Long.parseLong(vol) / 100;

        int e = (int) Math.floor(Math.log10(num));

        if (e >= 8) {
            return doubleFormat(num / 100000000.0) + "亿";
        } else if (e >= 4) {
            return doubleFormat(num / 10000.0) + "万";
        } else {
            return num + "";
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
}
