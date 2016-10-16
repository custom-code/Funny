package com.zhe.funny.test;

import android.app.Activity;

import com.zhe.core.mvpcore.mvp.BasePresenter;

/**
 * Created by zhe on 2016/10/16.
 */
public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {

    public TestPresenter(TestDataManager dataManager, Activity activity) {
        super(dataManager, activity);
    }

    @Override
    public void getTabs() {
        //类型暂时先写死
    }
}
