package com.zhe.funny.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhe.funny.R;
import com.zhe.funny.base.adapter.BaseRecyclerAdapter;

/**
 * Created by zhe on 16/5/22.
 */
public class MainFragmentAdapter extends BaseRecyclerAdapter<String> {
    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_main_fragment_item, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, String data) {
        if (viewHolder instanceof ViewHolder) {
            ((ViewHolder) viewHolder).mTvTittle.setText("这些都是测试的数据,不是从服务器拿的拉!");
        }
    }

    class ViewHolder extends BaseRecyclerAdapter.Holder {
        private TextView mTvTittle;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvTittle = (TextView) itemView.findViewById(R.id.main_fragment_item_tittle);
        }
    }
}
