package com.myzhsh.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by yh on 2016/6/2.
 * 新闻中心的五个页签的基类
 */
public abstract class BaseNewsCenterPager {
    protected Activity mActivity;
    private View mView;

    public BaseNewsCenterPager(Activity activity) {
        this.mActivity = activity;
        mView = initView();
    }

    protected abstract View initView();

    protected abstract void initData();

    public View getRootView(){
        return mView;
    }
}
