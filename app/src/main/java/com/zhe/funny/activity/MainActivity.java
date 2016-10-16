package com.zhe.funny.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zhe.core.base.activity.SwipeRefreshBaseActivity;
import com.zhe.funny.R;
import com.zhe.funny.fragment.MainFragment;
import com.zhe.funny.fragment.PageItemFragment;

public class MainActivity extends SwipeRefreshBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainFragment.OnFragmentInteractionListener {

    private NavigationView navigationView;
    private MainFragment mMainFragment;
    private PageItemFragment mItemFragment;

    private Fragment mCurrentFragment;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initFragment(savedInstanceState);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (mCurrentFragment != mMainFragment) {
            resetToolbar(TOOLBAR_WITH_TAB_MODE);
            selectMainMenu();
            navigationView.getMenu().getItem(0).setChecked(true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected int setToolbarMode() {
//        return ONLY_TOOLBAR_MODE;
        return TOOLBAR_WITH_TAB_MODE;
//        return TOOLBAR_LAYOUT_MODE;
//        return TOOLBAR_LAYOUT_WITH_TAB_MODE;
//        return TOOLBAR_LAYOUT_WITH_FAB_MODE;
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setRequestDataRefresh(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_group_one:
                resetToolbar(TOOLBAR_WITH_TAB_MODE);
                selectMainMenu();
                break;
            case R.id.nav_group_two:
                resetToolbar(ONLY_TOOLBAR_MODE);
                if (mItemFragment == null) {
                    mItemFragment = PageItemFragment.newInstance(2);
                }
                switchContent(mCurrentFragment, mItemFragment);
                break;
            case R.id.nav_group_three:
                resetToolbar(TOOLBAR_LAYOUT_MODE);
                break;
            case R.id.nav_group_four:
                resetToolbar(TOOLBAR_LAYOUT_WITH_TAB_MODE);
                break;
            case R.id.nav_item_one:
                resetToolbar(TOOLBAR_LAYOUT_WITH_FAB_MODE);
                break;
            case R.id.nav_item_two:

                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        invalidateOptionsMenu();
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * 跳转到首页的fragment
     */
    private void selectMainMenu() {
        if (mMainFragment == null) {
            mMainFragment = MainFragment.newInstance(new Bundle());
        }
        switchContent(mCurrentFragment, mMainFragment);
    }

    /**
     * 为页面加载初始状态的fragment
     */
    private void initFragment(Bundle savedInstanceState) {
        //判断activity是否重建，如果不是，则不需要重新建立fragment.
        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (mMainFragment == null) {
                mMainFragment = MainFragment.newInstance(new Bundle());
            }
            mCurrentFragment = mMainFragment;
            ft.replace(R.id.main_content, mMainFragment).commit();
        }
    }

    /**
     * 当fragment进行切换时，采用隐藏与显示的方法加载fragment以防止数据的重复加载
     *
     * @param from
     * @param to
     */
    private void switchContent(Fragment from, Fragment to) {
        if (mCurrentFragment != to) {
            mCurrentFragment = to;
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                ft.hide(from).add(R.id.main_content, to); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                ft.hide(from).show(to); // 隐藏当前的fragment，显示下一个
            }
            ft.commit();
        }
    }
}
