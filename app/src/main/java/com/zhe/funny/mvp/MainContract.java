package com.zhe.funny.mvp;

import com.zhe.core.mvpcore.mvp.IPresenter;
import com.zhe.core.mvpcore.mvp.IView;

import java.util.List;

/**
 * Created by zhe on 2016/10/16.
 */

public class MainContract {
    public interface View extends IView {

        void setListData(List<String> data);
    }

    public interface Presenter extends IPresenter<MainContract.View> {
        void getTabs();
    }
}
