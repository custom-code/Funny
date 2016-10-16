package com.zhe.funny.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhe.funny.R;
import com.zhe.core.base.fragment.SwipeRefreshFragment;
import com.zhe.funny.adapter.MainFragmentViewPageAdapter;

import butterknife.ButterKnife;

public class MainFragment extends SwipeRefreshFragment {

    //    @BindView(R.id.fragment_main_tab)
    TabLayout mFragmentMainTab;
    //    @BindView(R.id.fragment_main_viewpager)
    ViewPager mFragmentMainViewPager;
    private Bundle mBundle;
    private Activity mActivity;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
    }

    public static MainFragment newInstance(Bundle bundle) {
        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        if (getArguments() != null) {
            mBundle = getArguments();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        mFragmentMainTab = (TabLayout) mActivity.findViewById(R.id.base_bar_tab_layout);
        mFragmentMainViewPager = (ViewPager) view.findViewById(R.id.fragment_main_viewpager);
        mFragmentMainViewPager.setAdapter(new MainFragmentViewPageAdapter(getChildFragmentManager()));
        mFragmentMainTab.setupWithViewPager(mFragmentMainViewPager);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
}
