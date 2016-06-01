package com.myzhsh.pager;

import android.content.Context;

import com.myzhsh.base.BasePager;

/**
 * Created by yh on 2016/6/1.
 */
public class NewsCenterPager extends BasePager {
    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        mTvTitle.setText("新闻中心");
    }
}
