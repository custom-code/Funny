package com.zhe.funny.base.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.zhe.core.BaseActivity;
import com.zhe.funny.R;

import java.lang.reflect.Field;

/**
 * Created by zhe on 16/5/5.
 */
public abstract class ToolbarActivity extends BaseActivity
        implements View.OnClickListener {

    protected AppBarLayout mAppBar;
    protected CollapsingToolbarLayout mToolbarLayout;
    protected Toolbar mToolbar;
    protected TabLayout mTabLayout;
    protected FloatingActionButton mActionButton;

    protected boolean mIsHidden = false;

    private final int APPLAYOUT_HEIGHT = 450;

    public static final int ONLY_TOOLBAR_MODE = 0x001001;
    public static final int TOOLBAR_WITH_TAB_MODE = 0x001002;
    public static final int TOOLBAR_LAYOUT_MODE = 0x001003;
    public static final int TOOLBAR_LAYOUT_WITH_TAB_MODE = 0x001004;
    public static final int TOOLBAR_LAYOUT_WITH_FAB_MODE = 0x001005;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());
        setupToolbar();
    }

    private void setupToolbar() {
        mAppBar = (AppBarLayout) findViewById(R.id.bar_appbar_layout);
        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.bar_toolbar_layout);
        mToolbar = (Toolbar) findViewById(R.id.bar_toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.bar_tab_layout);
        mActionButton = (FloatingActionButton) findViewById(R.id.bar_fab);

        if (mAppBar == null || mToolbarLayout == null || mToolbar == null || mTabLayout == null) {
            throw new IllegalStateException("need toolbar which named layout/view_app_bar_layout");
        }

        setSupportActionBar(mToolbar);
        setupToolbarByMode(setToolbarMode());

        if (Build.VERSION.SDK_INT >= 21) {
            mAppBar.setElevation(10.6f);
        }
    }

    private void setupToolbarByMode(int mode) {
        Log.d("ToolbarActivity", "mode:" + mode);
        switch (mode) {
            case TOOLBAR_WITH_TAB_MODE:
                resetToolbarWithTabMode();
                break;
            case TOOLBAR_LAYOUT_MODE:
                resetToolbarLayoutMode();
                break;
            case TOOLBAR_LAYOUT_WITH_TAB_MODE:
                resetToolbarLayoutWithTabMode();
                break;
            case TOOLBAR_LAYOUT_WITH_FAB_MODE:
                resetToolbarLayoutWithFabMode();
                break;
            case ONLY_TOOLBAR_MODE:
            default:
                resetOnlyToolbarMode();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            mAppBar.setElevation(10.6f);
        }
    }

    private void resetOnlyToolbarMode() {
        if (mActionButton != null) {
            mActionButton.setVisibility(View.GONE);
        }
        mTabLayout.setVisibility(View.GONE);
        CoordinatorLayout.LayoutParams mParams =
                (CoordinatorLayout.LayoutParams) mAppBar.getLayoutParams();
        mParams.height = CoordinatorLayout.LayoutParams.WRAP_CONTENT;
        AppBarLayout.LayoutParams mParams1 =
                (AppBarLayout.LayoutParams) mToolbarLayout.getLayoutParams();
        mParams1.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        CollapsingToolbarLayout.LayoutParams mParams2 =
                (CollapsingToolbarLayout.LayoutParams) mToolbar.getLayoutParams();
        mParams2.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN);
    }

    private void resetToolbarWithTabMode() {
        if (mActionButton != null) {
            mActionButton.setVisibility(View.GONE);
        }
        mTabLayout.setVisibility(View.VISIBLE);
        CoordinatorLayout.LayoutParams mParams =
                (CoordinatorLayout.LayoutParams) mAppBar.getLayoutParams();
        mParams.height = CoordinatorLayout.LayoutParams.WRAP_CONTENT;
        AppBarLayout.LayoutParams mParams1 =
                (AppBarLayout.LayoutParams) mToolbarLayout.getLayoutParams();
        mParams1.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        CollapsingToolbarLayout.LayoutParams mParams2 =
                (CollapsingToolbarLayout.LayoutParams) mToolbar.getLayoutParams();
        mParams2.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX);
    }

    private void resetToolbarLayoutMode() {
        if (mActionButton != null) {
            mActionButton.setVisibility(View.GONE);
        }
        mTabLayout.setVisibility(View.GONE);
        CoordinatorLayout.LayoutParams mParams =
                (CoordinatorLayout.LayoutParams) mAppBar.getLayoutParams();
        mParams.height = APPLAYOUT_HEIGHT;
        AppBarLayout.LayoutParams mParams1 =
                (AppBarLayout.LayoutParams) mToolbarLayout.getLayoutParams();
        mParams1.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        CollapsingToolbarLayout.LayoutParams mParams2 =
                (CollapsingToolbarLayout.LayoutParams) mToolbar.getLayoutParams();
        mParams2.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN);
    }

    private void resetToolbarLayoutWithTabMode() {
        if (mActionButton != null) {
            mActionButton.setVisibility(View.GONE);
        }
        mTabLayout.setVisibility(View.VISIBLE);
        CoordinatorLayout.LayoutParams mParams =
                (CoordinatorLayout.LayoutParams) mAppBar.getLayoutParams();
        mParams.height = APPLAYOUT_HEIGHT;
        AppBarLayout.LayoutParams mParams1 =
                (AppBarLayout.LayoutParams) mToolbarLayout.getLayoutParams();
        mParams1.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        CollapsingToolbarLayout.LayoutParams mParams2 =
                (CollapsingToolbarLayout.LayoutParams) mToolbar.getLayoutParams();
        mParams2.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX);
    }

    private void resetToolbarLayoutWithFabMode() {
        if (mActionButton != null) {
            mActionButton.setVisibility(View.VISIBLE);
        }
        mTabLayout.setVisibility(View.GONE);
        CoordinatorLayout.LayoutParams mParams =
                (CoordinatorLayout.LayoutParams) mAppBar.getLayoutParams();
        mParams.height = APPLAYOUT_HEIGHT;
        AppBarLayout.LayoutParams mParams1 =
                (AppBarLayout.LayoutParams) mToolbarLayout.getLayoutParams();
        mParams1.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        CollapsingToolbarLayout.LayoutParams mParams2 =
                (CollapsingToolbarLayout.LayoutParams) mToolbar.getLayoutParams();
        mParams2.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX);
    }

    private int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 75;
        }
    }

    abstract protected int provideContentViewId();

    protected int setToolbarMode() {
        return 0;
    }

    protected void canBack(boolean flag) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(flag);
    }

    protected void resetToolbar(int mode) {
        setupToolbarByMode(mode);
        canBack(false);
    }

    protected void setAppBarAlpha(float alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mAppBar.setAlpha(alpha);
        }
    }

    protected void hideOrShowToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            mAppBar.animate()
                    .translationY(mIsHidden ? 0 : -mAppBar.getHeight())
                    .setInterpolator(new DecelerateInterpolator(2))
                    .start();
        }
        mIsHidden = !mIsHidden;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
