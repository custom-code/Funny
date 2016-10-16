package com.zhe.core.mvpcore.mvp;

/**
 * Created by zhe on 16/5/22.
 */
public interface IPresenter<T extends IView> {
    void attachView(T view);

    void detachView();
}
