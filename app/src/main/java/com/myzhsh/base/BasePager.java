package com.myzhsh.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.myzhsh.activity.MainActivity;
import com.myzhsh.activity.R;

/**
 * Created by yh on 2016/6/1.
 * 页面布局基类
 */
public abstract class BasePager {
    protected Activity mActivity;

    protected View mView;
    protected TextView mTvTitle;
    protected ImageButton mIbtnMenu;
    protected FrameLayout mFlContent;
    public BasePager(Activity activity) {
        this.mActivity = activity;
        initView();
    }

    private void initView() {
        mView = View.inflate(mActivity, R.layout.base_pager, null);
        mTvTitle = (TextView) mView.findViewById(R.id.shbd_tv_title);
        mIbtnMenu = (ImageButton) mView.findViewById(R.id.shbd_ibtn_back);
        mFlContent= (FrameLayout) mView.findViewById(R.id.zhsh_fl_basepager_content);

        mIbtnMenu .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) mActivity;
                SlidingMenu slidingMenu = activity.getSlidingMenu();
                slidingMenu.toggle();
            }
        });
    };

    public View getView(){
        return mView;
    }
    public abstract void initData() ;


}
