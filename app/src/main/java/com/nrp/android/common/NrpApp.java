package com.nrp.android.common;

import android.app.Application;

import com.nrp.android.utils.ToastUtils;

import org.litepal.LitePalApplication;


public class NrpApp extends Application {

    private static NrpApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ToastUtils.init(this);
        // CrashHandler.getInstance().init(this);

        // 初始化数据库
        LitePalApplication.initialize(this);
    }

    public static NrpApp getInstance() {
        return mInstance;
    }
}
