package com.zhe.funny.repository;

import com.zhe.core.retrofit.BaseRetrofitClient;
import com.zhe.funny.interactor.model.MeizhiData;

import rx.Observable;

/**
 * Created by zhe on 16/5/22.
 */
public class MainRetrofitClient extends BaseRetrofitClient {

    private MainService mMainService;

    public MainRetrofitClient(String baseUrl) {
        mMainService = initRestAdapter(baseUrl, MainService.class);
    }

    public Observable<MeizhiData> getListData(int page) {
        return mMainService.getMeizhiData(page);
    }

}
