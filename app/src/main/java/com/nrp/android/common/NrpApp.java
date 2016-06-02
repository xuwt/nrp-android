package com.nrp.android.common;

import android.app.Application;

import com.baidu.majia.http.HttpManager;
import com.baidu.majia.utils.FileManager;
import com.baidu.majia.utils.ImageUtil;
import com.baidu.majia.utils.NetworkUtil;
import com.baidu.majia.utils.TextToast;

import org.litepal.LitePalApplication;


public class NrpApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TextToast.init(this);
        // CrashHandler.getInstance().init(this);

        // 初始化数据库
        LitePalApplication.initialize(this);
    }
}
