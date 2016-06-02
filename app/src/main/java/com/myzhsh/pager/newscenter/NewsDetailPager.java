package com.myzhsh.pager.newscenter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.myzhsh.base.BaseNewsCenterPager;

/**
 * Created by yh on 2016/6/2.
 * 新闻模块
 */
public class NewsDetailPager extends BaseNewsCenterPager {
    public NewsDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    protected View initView() {
        TextView textView=new TextView(mActivity.getApplicationContext());
        textView.setText("新闻模块");
        return textView;
    }

    @Override
    protected void initData() {

    }
}
