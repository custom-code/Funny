package com.zhe.funny.main.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhe.funny.R;
import com.zhe.funny.base.fragment.SwipeRefreshFragment;

public class PageItemFragment extends SwipeRefreshFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PageItemFragment() {
    }

    public static PageItemFragment newInstance(int sectionNumber) {
        PageItemFragment fragment = new PageItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_page_item, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setRefreshing(false);
    }
}
