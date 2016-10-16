package com.zhe.funny.main.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhe.funny.R;
import com.zhe.funny.base.fragment.SwipeRefreshFragment;
import com.zhe.funny.main.adapter.MainFragmentAdapter;
import com.zhe.funny.main.mvp.MainPresenter;
import com.zhe.funny.main.mvp.MainView;

import java.util.ArrayList;
import java.util.List;

public class PageItemMainFragment extends SwipeRefreshFragment implements MainView {
    private Activity mActivity;
    private Bundle mBundle;
    private MainPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private CardView mCardView;
    private MainFragmentAdapter mFragmentAdapter;

    private int mPage = 1;

    public PageItemMainFragment() {
    }

    public static PageItemMainFragment newInstance(Bundle bundle) {
        PageItemMainFragment fragment = new PageItemMainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainPresenter(this);
        mActivity = getActivity();
        if (getArguments() != null) {
            mBundle = getArguments();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_page_item_main, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.page_item_recycler);
        mCardView = (CardView) rootView.findViewById(R.id.page_item_head);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setRefreshing(true);
            }
        }, 358);
        mPresenter.onLoadData(mPage);
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        mPage = 1;
        mPresenter.onLoadData(mPage);
    }

    private void setupRecyclerView() {
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        LinearLayoutManager layoutManager1 = new
                LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);

        mFragmentAdapter = new MainFragmentAdapter();
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mFragmentAdapter);
        View headView = LayoutInflater.from(mActivity).inflate(
                R.layout.layout_main_frament_head, mRecyclerView, false);
        mFragmentAdapter.setHeaderView(headView);
    }

    private void loadData(boolean b) {
        if (b) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 40; i++) {
                list.add("position" + i);
            }
            mFragmentAdapter.addDatas(list);
        }
        setRefreshing(false);
    }

    @Override
    public void setListData(List<String> data) {
        mFragmentAdapter.addDatas(data);
        setRefreshing(false);
    }

    @Override
    public void showToast(Bundle bundle) {

    }
}
