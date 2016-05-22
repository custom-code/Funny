package com.zhe.funny.main.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhe.funny.main.fragment.PageItemFragment;
import com.zhe.funny.main.fragment.PageItemMainFragment;

/**
 * Created by zhe on 16/5/21.
 */
public class MainFragmentViewPageAdapter extends FragmentPagerAdapter {

    public MainFragmentViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return PageItemMainFragment.newInstance(new Bundle());
        } else {
            return PageItemFragment.newInstance(position + 1);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "首页";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }
}
