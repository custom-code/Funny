package com.zhe.funny.test;

import com.zhe.core.mvpcore.mvp.IPresenter;
import com.zhe.core.mvpcore.mvp.IView;

import java.util.List;

/**
 * Created by zhe on 2016/10/16.
 */

public interface TestContract {
    interface View extends IView {
        void addTabs(List<String> tabs);

    }

    interface Presenter extends IPresenter<View> {
        void getTabs();
    }
}
