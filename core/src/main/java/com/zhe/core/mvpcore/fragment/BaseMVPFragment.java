package com.zhe.core.mvpcore.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhe.core.BaseFragment;
import com.zhe.core.mvpcore.activity.BaseMVPActivity;
import com.zhe.core.mvpcore.mvp.IPresenter;
import com.zhe.core.mvpcore.mvp.IView;

import butterknife.ButterKnife;

/**
 * Created by zhe on 16/5/5.
 */
public abstract class BaseMVPFragment<T extends IPresenter> extends BaseFragment implements IView {
    protected T mPresenter;
    protected View mView;
    protected Activity mContext;

    @Override
    public void onAttach(Activity activity) {
        if (activity instanceof BaseMVPActivity) {
            mContext = activity;
        }
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        mPresenter = getPresenter();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null)
            mPresenter.attachView(this);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    protected abstract int getLayoutId();

    protected abstract T getPresenter();
}
