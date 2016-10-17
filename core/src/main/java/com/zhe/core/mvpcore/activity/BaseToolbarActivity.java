package com.zhe.core.mvpcore.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.zhe.core.R;
import com.zhe.core.mvpcore.mvp.IPresenter;

/**
 * Created by zhe on 2016/10/16.
 */

public abstract class BaseToolbarActivity<T extends IPresenter> extends BaseMVPActivity<T> {

    private AppBarLayout mAppBar;
    private CollapsingToolbarLayout mToolbarLayout;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setToolbar(Toolbar toolbar) {
        this.mToolbar = toolbar;
        trySetupToolbar();
    }

    private void trySetupToolbar() {
//        mAppBar = (AppBarLayout) findViewById(R.id.base_bar_appbar_layout);
//        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.base_bar_toolbar_layout);
//        mToolbar = (Toolbar) findViewById(R.id.base_bar_toolbar);
//        mTabLayout = (TabLayout) findViewById(R.id.base_bar_tab_layout);
//        mActionButton = (FloatingActionButton) findViewById(R.id.base_bar_fab);
        setSupportActionBar(mToolbar);
        setDisplayHomeAsUpEnabled(false);
    }

    protected void setTittle(CharSequence tittle) {
        mToolbar.setTitle(tittle);
    }

    protected void setDisplayHomeAsUpEnabled(boolean flag) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(flag);
    }
}
