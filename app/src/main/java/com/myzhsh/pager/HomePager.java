package com.myzhsh.pager;

import android.content.Context;
import android.view.View;

import com.myzhsh.base.BasePager;

/**
 * Created by yh on 2016/6/1.
 */
public class HomePager extends BasePager {
    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        mTvTitle.setText("首页");

        //隐藏菜单按钮
        mIbtnBack.setVisibility(View.GONE);
    }
}
