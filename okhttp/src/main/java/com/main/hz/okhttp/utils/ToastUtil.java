package com.main.hz.okhttp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.main.hz.okhttp.R;

/**
 * Created by Android on 2016/8/1.
 */
public class ToastUtil {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static long time;
    private static long intervalTime = 2000;

    public static void init(Context c) {
        context = c;
        time = System.currentTimeMillis();
    }


    /**
     * Toast工具类
     *
     * @param
     */
    public static void show(int id) {
        show(id + "");
    }

    /**
     * Toast工具类
     *
     * @param
     */
    public static void show(int id, boolean is) {
        if (is) {
            show(context.getString(id));
        } else {
            show(id + "");
        }
    }


    public static void reset() {
        time = System.currentTimeMillis();
    }

    /**
     * Toast工具类
     *
     * @param string
     */
    public static void show(String string) {
        //判断如果在2400毫秒内Toast过则不会Toast
        if (System.currentTimeMillis() - time > intervalTime || System.currentTimeMillis() - time < -intervalTime) {
            if (MyTextUtil.isEmpty(string)) {
                string = " ";
            }
            Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
            View view = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
            LinearLayout ll = view.findViewById(R.id.ll);
            ll.setBackgroundResource(R.drawable.bg_toast);
            TextView tv_msg = view.findViewById(R.id.tv);
            tv_msg.setTextSize(15);
            tv_msg.setText(string);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            time = System.currentTimeMillis();
        }
    }

    /**
     * Toast工具类
     *
     * @param string
     */
    public static void show(String string, boolean isFast) {
        //判断如果在2400毫秒内Toast过则不会Toast
        if (isFast || System.currentTimeMillis() - time > intervalTime || System.currentTimeMillis() - time < -intervalTime) {
            if (MyTextUtil.isEmpty(string)) {
                string = " ";
            }
            Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
            View view = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
            LinearLayout ll = view.findViewById(R.id.ll);
            ll.setBackgroundResource(R.drawable.bg_toast);
            TextView tv_msg = view.findViewById(R.id.tv);
            tv_msg.setText(string);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            time = System.currentTimeMillis();
        }
    }

    /**
     * Toast工具类
     *
     * @param string
     */
    public static void show(String string, int image) {
        //判断如果在2400毫秒内Toast过则不会Toast
        if (System.currentTimeMillis() - time > intervalTime || System.currentTimeMillis() - time < -intervalTime) {
            if (MyTextUtil.isEmpty(string)) {
                string = " ";
            }
            Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
            View view = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
            LinearLayout ll = view.findViewById(R.id.ll);
            ll.setBackgroundResource(R.drawable.bg_toast);
            ImageView iv_img = view.findViewById(R.id.iv);
            iv_img.setImageResource(image);
            iv_img.setVisibility(View.VISIBLE);
            TextView tv_msg = view.findViewById(R.id.tv);
            tv_msg.setText(string);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            time = System.currentTimeMillis();
        }
    }

}
