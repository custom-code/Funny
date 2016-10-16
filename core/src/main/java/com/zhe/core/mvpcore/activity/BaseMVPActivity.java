package com.zhe.core.mvpcore.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhe.core.BaseActivity;
import com.zhe.core.mvpcore.mvp.IPresenter;
import com.zhe.core.mvpcore.mvp.IView;

import butterknife.ButterKnife;

/**
 * Created by zhe on 16/5/5.
 */
public abstract class BaseMVPActivity<T extends IPresenter> extends BaseActivity implements IView {

    protected T mPresenter;
    protected Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        mPresenter = getPresenter();
        mContext = this;
        ButterKnife.bind(this);
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    protected abstract int getContentLayout();

    protected abstract T getPresenter();
}
