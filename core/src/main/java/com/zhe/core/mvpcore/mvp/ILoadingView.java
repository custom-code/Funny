package com.zhe.core.mvpcore.mvp;

/**
 * Created by zhe on 2016/10/16.
 */
public interface ILoadingView extends IView {
    void showLoading();

    void showContent();

    void showNoData();

    void showError(String msg);
}
