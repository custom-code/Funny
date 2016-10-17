package com.zhe.funny.test;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.zhe.core.mvpcore.activity.BaseToolbarActivity;
import com.zhe.funny.R;

import butterknife.BindView;

public class TestActivity extends BaseToolbarActivity<TestPresenter> {

    @BindView(R.id.test_tab_layout)
    TableLayout testTabLayout;
    @BindView(R.id.test_content)
    FrameLayout testContent;
    @BindView(R.id.activity_test)
    CoordinatorLayout activityTest;
    @BindView(R.id.base_bar_toolbar)
    Toolbar baseBarToolbar;
    @BindView(R.id.base_bar_appbar_layout)
    AppBarLayout baseBarAppbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(baseBarToolbar);
        setTittle("fdsdfsd");
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected TestPresenter getPresenter() {
        return new TestPresenter(new TestDataManager(), mContext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
