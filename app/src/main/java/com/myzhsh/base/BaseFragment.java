package com.myzhsh.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yh on 2016/6/1.
 * fragment抽象基类
 */
public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mActivity=getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=initView();
        initData();
        return view;
    }

    public abstract View initView() ;

    public void initData(){

    }

}
