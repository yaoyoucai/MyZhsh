package com.myzhsh.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myzhsh.activity.R;
import com.myzhsh.adapter.NewsListAdapter;

/**
 * Created by yh on 2016/6/17.
 * 新闻列表viewHolder
 */
public class NewsListViewHolder extends RecyclerView.ViewHolder {
    public ImageView mIvIcon;
    public TextView mTvTitle;
    public TextView mTvTime;

    public NewsListViewHolder(View itemView, int viewType) {
        super(itemView);
        if (viewType == NewsListAdapter.TYPE_BODY) {
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_item_tabdetaill_icon);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_item_tabdetail_title);
            mTvTime = (TextView) itemView.findViewById(R.id.tv_item_tabdetail_time);
        }
    }
}
