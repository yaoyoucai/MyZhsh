package com.myzhsh.fragment.main;

import android.view.View;

import com.myzhsh.activity.R;
import com.myzhsh.base.BaseFragment;

/**
 * Created by yh on 2016/6/1.
 */
public class LeftMenuFragment extends BaseFragment {
    @Override
    public View initView() {
        View view=View.inflate(mActivity, R.layout.fragment_leftmenu,null);
        return view;
    }
}
