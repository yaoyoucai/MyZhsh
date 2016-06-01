package com.myzhsh.base;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.myzhsh.activity.R;

/**
 * Created by yh on 2016/6/1.
 * 页面布局基类
 */
public abstract class BasePager {
    protected Context mContext;

    protected View mView;
    protected TextView mTvTitle;
    protected ImageButton mIbtnBack;

    public BasePager(Context context) {
        this.mContext = context;
        initView();
    }

    private void initView() {
        mView = View.inflate(mContext, R.layout.base_pager, null);
        mTvTitle = (TextView) mView.findViewById(R.id.shbd_tv_title);
        mIbtnBack = (ImageButton) mView.findViewById(R.id.shbd_ibtn_back);
    }

    public View getView(){
        return mView;
    }
    public abstract void initData() ;


}
