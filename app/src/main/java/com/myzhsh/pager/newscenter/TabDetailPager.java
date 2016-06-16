package com.myzhsh.pager.newscenter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.myzhsh.base.BaseNewsCenterPager;
import com.myzhsh.bean.NewsMenuData;

/**
 * Created by yh on 2016/6/3.
 * 新闻页签
 */
public class TabDetailPager extends BaseNewsCenterPager {
    private NewsMenuData.NewsData.NewsTabData mData;

    private TextView mTextView;
    public TabDetailPager(Activity activity,NewsMenuData.NewsData.NewsTabData data) {
        super(activity);
        this.mData=data;
    }

    @Override
    protected View initView() {
        mTextView=new TextView(mActivity.getApplicationContext());
        mTextView.setTextColor(Color.RED);
        return mTextView;
    }

    @Override
    protected void initData() {
        mTextView.setText(mData.getTitle());
    }
}
