package com.main.hz.okhttp.utils;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：姚健
 * 创建时间：2016/12/1 10:08
 */

public class JsonUtil {

    public static boolean getJsontoBoolean(String string, String key) {
        boolean b = false;
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            if (json.has(key)) {
                b = json.getBoolean(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static String getJsontoString(String string, String key) {
        String s = "";
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            if (json.has(key)) {
                s = json.getString(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            s = "";
        }
        return s;
    }

    public static int getJsontoInt(String string, String key) {
        int i = 0;
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            if (json.has(key)) {
                i = json.getInt(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static double getJsontoDouble(String string, String key) {
        double i = 0;
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            if (json.has(key)) {
                i = json.getDouble(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static String getJsonArrayFirst(String string, String key) {
        String s = null;
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            if (json.has(key)) {
                s = json.getJSONArray(key).getString(0);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static long getJsontoLong(String string, String key) {
        long i = 0;
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            if (json.has(key)) {
                i = json.getLong(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return i;
    }


    public static int getCode(String string) {
        int b = -1;
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            if (json.has("status")) {
                b = json.getInt("status");
            }
        } catch (JSONException e) {
            return b;
        }
        return b;
    }


    public static boolean isSuccess(String string) {
        int b = -1;
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            if (json.has("status")) {
                b = json.getInt("status");
            }
        } catch (JSONException e) {
            return b == 200;
        }
        return b == 200;
    }

    public static String getData(String string) {
        String s = "";
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            if (json.has("data")) {
                s = json.getString("data");
            }
        } catch (JSONException e) {
            return string;
        }
        return s;
    }

    public static String getInfo(String string) {
        String s = "";
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            if (json.has("info")) {
                s = json.getString("info");
            }
        } catch (JSONException e) {
            return string;
        }
        return s;
    }

    public static String getMsg(String string) {
        String b = "";
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            if (json.has("msg")) {
                b = json.getString("msg");
            }
        } catch (JSONException e) {
            return "";
        }
        return b;
    }

    /**
     * 解析实体
     *
     * @param response
     * @return
     */
    public static <T> T fastBean(String response, Class<T> classOfT) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(response, classOfT);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解析实体
     *
     * @param response
     * @return
     */
    public static <T> T fastDataBean(String response, Class<T> classOfT) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(getData(response), classOfT);
        } catch (Exception e) {
            return null;
        }
    }

    public static String addDataString(String old) {
        return "{ data:" + old + "}";
    }


    public static <T> List<T> fastListBean(String json, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(clazz);
        try {
            return new Gson().fromJson(json, type);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
