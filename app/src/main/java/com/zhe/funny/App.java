package com.zhe.funny;

import android.app.Application;

import com.zhe.common.util.ToastUtil;

/**
 * Created by zhe on 16/5/5.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.register(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
