package com.zhe.funny.base;

import com.zhe.funny.main.repository.MainRetrofitClient;

/**
 * Created by zhe on 16/5/22.
 */
public class Api {
    protected static final Object monitor = new Object();
    private static Api mApi;
    private MainRetrofitClient mMainRetrofitClient;
    private final String baseUrl = "http://gank.io/api/";

    public static Api getInstance() {
        synchronized (monitor) {
            if (mApi == null) {
                mApi = new Api();
            }
            return mApi;
        }
    }

    public MainRetrofitClient getMainRetrofitClient() {
        if (mMainRetrofitClient == null) {
            mMainRetrofitClient = new MainRetrofitClient(baseUrl);
        }
        return mMainRetrofitClient;
    }
}
