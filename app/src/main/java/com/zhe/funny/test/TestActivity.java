package com.zhe.funny.test;

import android.os.Bundle;

import com.zhe.core.mvpcore.activity.BaseToolbarActivity;
import com.zhe.funny.R;

public class TestActivity extends BaseToolbarActivity<TestPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected TestPresenter getPresenter() {
        return new TestPresenter(new TestDataManager(), mContext);
    }
}
