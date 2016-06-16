package com.indicator.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.indicator.fragment.SimpleFragment;
import com.indicator.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPagerIndicator mIndicator;
    private ViewPager mViewPager;

    private List<String> mDataList= Arrays.asList("短信","收藏","推荐","短信1","收藏1","推荐1");

    private List<SimpleFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        mIndicator.setVisibleCount(3);
        mIndicator.setTabItemTitles(mDataList);
        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        mIndicator.setViewPager(mViewPager);

    }


    private void initView() {
        mIndicator= (ViewPagerIndicator) findViewById(R.id.zhsh_vpi_indicator);
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
    }

    private void initData() {
        fragmentList=new ArrayList<>();
        for (String title:mDataList){
            fragmentList.add(SimpleFragment.newInstance(title));
        }
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
