package com.zhe.funny.test;

import com.zhe.core.retrofit.BaseRetrofitClient;
import com.zhe.funny.MainApi;

/**
 * Created by zhe on 16/5/22.
 */
public class API extends BaseRetrofitClient {
    protected static final Object monitor = new Object();
    private MainApi mMainApi;
    static API mAPI;

    public static API getInstance() {
        synchronized (monitor) {
            if (mAPI == null) {
                mAPI = new API();
            }
            return mAPI;
        }
    }

    public MainApi getMainApi() {
        if (mMainApi == null) {
            mMainApi = initRestAdapter("", MainApi.class);
        }
        return mMainApi;
    }
}
