package com.main.hz.okhttp.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.main.hz.okhttp.R;

public class WaitDialog extends Dialog {

    public WaitDialog(Context context) {
        super(context, R.style.DialogFullNo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_wait);
        setCancelable(false);
    }
}
