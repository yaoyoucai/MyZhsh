package com.myzhsh.pager.tab;

import android.app.Activity;

import com.myzhsh.base.BasePager;

/**
 * Created by yh on 2016/6/1.
 */
public class SmartServicePager extends BasePager {
    public SmartServicePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        mTvTitle.setText("智慧服务");
    }
}
