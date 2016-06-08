package com.myzhsh.pager.tab;

import android.app.Activity;
import android.view.View;

import com.myzhsh.base.BasePager;

/**
 * Created by yh on 2016/6/1.
 */
public class SettingPager extends BasePager {
    public SettingPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        mTvTitle.setText("设置");

        //隐藏菜单按钮
        mIbtnMenu.setVisibility(View.GONE);
    }
}
