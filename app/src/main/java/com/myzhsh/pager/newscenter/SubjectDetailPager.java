package com.myzhsh.pager.newscenter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.myzhsh.base.BaseNewsCenterPager;

/**
 * Created by yh on 2016/6/2.
 * 专题模块
 */
public class SubjectDetailPager extends BaseNewsCenterPager {
    public SubjectDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    protected View initView() {
        TextView textView=new TextView(mActivity.getApplicationContext());
        textView.setTextColor(Color.RED);
        textView.setText("专题模块");
        return textView;
    }

    @Override
    protected void initData() {

    }
}
