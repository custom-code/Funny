package com.zhe.core.mvpcore.mvp;

import android.app.Activity;

import com.zhe.core.mvpcore.IDataManager;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhe on 2016/10/16.
 */
public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

    protected Activity mActivity;
    protected T mView;
    protected CompositeSubscription mCompositeSubscription;
    protected IDataManager mDataManager;

    public <D extends IDataManager> BasePresenter(D dataManager, Activity activity) {
        this.mDataManager = dataManager;
        this.mActivity = activity;
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    protected void handleError(Throwable throwable) {

    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
