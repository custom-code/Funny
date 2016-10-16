package com.zhe.funny.main.mvp;


import com.zhe.core.mvp.Presenter;
import com.zhe.funny.main.interactor.MainInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhe on 16/5/22.
 */
public class MainPresenter implements Presenter {
    private MainView mView;
    private MainInteractor mInteractor;

    public MainPresenter(MainView view) {
        this.mView = view;
        this.mInteractor = new MainInteractor();
    }

    public void onLoadData(int page) {
        mInteractor.getListData(page, new MainInteractor.Callback() {
            @Override
            public void onSuccess(List<String> cityList) {
                mView.setListData(cityList);
            }

            @Override
            public void onError(Exception e) {
                mView.setListData(new ArrayList<String>());
            }
        });
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }
}
