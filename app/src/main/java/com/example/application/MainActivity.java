package com.example.application;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application.view.stockview.MyStockRecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyStockRecyclerView.ScrollListener, MyStockRecyclerView.OrderListener {


    public static String TABLE0 = "最新/行业板块/涨幅/量比/换手率/振幅/涨速/总手/现手/成交额/总市值/流通市值/市盈率/市盈(动)/市净率";


    private MyStockRecyclerView stockRecyclerView;

    private ArrayList<StockBean> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stockRecyclerView = findViewById(R.id.recycler);
        stockRecyclerView.setWeight(1, 3);

        stockRecyclerView.setScrollListener(this);
        stockRecyclerView.setOrderListener(this);
        for (int i = 0; i < 100; i++) {
            arrayList.add(null);
        }
//        stockRecyclerView.setTable(getTabList(TABLE0));
        stockRecyclerView.setTopTable();
        stockRecyclerView.setData(arrayList);
    }

    private int count;


    public static ArrayList<String> getTabList(String s) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] strings = s.split("/");
        for (int i = 0; i < strings.length; i++) {
            arrayList.add(strings[i]);
        }
        return arrayList;
    }

    @Override
    public void scrolled() {

    }

    private int top;
    private int bottom;

    @Override
    public void order(ShortSelectBean shortSelectBean) {
        if (shortSelectBean == null) return;
        Log.e("order", shortSelectBean.getShortName() + "/" + (shortSelectBean.isDesc() ? "降序" : "升序"));
    }
}