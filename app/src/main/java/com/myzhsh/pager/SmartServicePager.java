package com.myzhsh.pager;

import android.content.Context;

import com.myzhsh.base.BasePager;

/**
 * Created by yh on 2016/6/1.
 */
public class SmartServicePager extends BasePager {
    public SmartServicePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        mTvTitle.setText("智慧服务");
    }
}
