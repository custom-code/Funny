package com.zhe.funny.mvp;


import android.app.Activity;

import com.zhe.core.mvpcore.mvp.BasePresenter;
import com.zhe.funny.interactor.MainInteractor;
import com.zhe.funny.test.TestDataManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhe on 16/5/22.
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    private MainInteractor mInteractor;

    public MainPresenter(TestDataManager dataManager, Activity activity) {
        super(dataManager, activity);
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
    public void getTabs() {

    }
}
