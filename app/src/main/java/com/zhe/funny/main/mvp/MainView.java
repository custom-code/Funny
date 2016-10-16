package com.zhe.funny.main.mvp;

import com.zhe.core.mvp.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhe on 16/5/22.
 */
public interface MainView extends View {
    void setListData(List<String> data);
}
