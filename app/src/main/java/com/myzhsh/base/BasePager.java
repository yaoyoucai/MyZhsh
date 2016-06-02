package com.myzhsh.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.myzhsh.activity.R;

/**
 * Created by yh on 2016/6/1.
 * 页面布局基类
 */
public abstract class BasePager {
    protected Activity mActivity;

    protected View mView;
    protected TextView mTvTitle;
    protected ImageButton mIbtnBack;
    protected FrameLayout mFlContent;
    public BasePager(Activity activity) {
        this.mActivity = activity;
        initView();
    }

    private void initView() {
        mView = View.inflate(mActivity, R.layout.base_pager, null);
        mTvTitle = (TextView) mView.findViewById(R.id.shbd_tv_title);
        mIbtnBack = (ImageButton) mView.findViewById(R.id.shbd_ibtn_back);
        mFlContent= (FrameLayout) mView.findViewById(R.id.zhsh_fl_basepager_content);
    };

    public View getView(){
        return mView;
    }
    public abstract void initData() ;


}
