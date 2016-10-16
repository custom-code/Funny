package com.zhe.funny.interactor;

import android.util.Log;

import com.zhe.funny.Api;
import com.zhe.funny.interactor.model.MeizhiData;
import com.zhe.funny.repository.MainRetrofitClient;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhe on 16/5/22.
 */
public class MainInteractor {

    private Callback callback;
    private MainRetrofitClient mClient;

    public MainInteractor() {
        mClient = Api.getInstance().getMainRetrofitClient();
    }

    public void getListData(final int page, final Callback callback) {
        this.callback = callback;
//        getListData(page);
        getListData(page, new Observer<MeizhiData>() {

            @Override
            public void onCompleted() {
                getListData(page);
                Log.d("MainInteractor", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("MainInteractor", "onError");
            }

            @Override
            public void onNext(MeizhiData meizhiData) {
                Log.d("MainInteractor", "onNext");
            }
        });
    }

    public void getListData(int page, Observer<MeizhiData> observer) {
        mClient.getListData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private void getListData(int page) {
        if (page > 0) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 40; i++) {
                list.add("position" + i);
            }
            callback.onSuccess(list);
        }
    }

    public interface Callback {
        void onSuccess(List<String> cityList);

        void onError(Exception e);
    }

}
