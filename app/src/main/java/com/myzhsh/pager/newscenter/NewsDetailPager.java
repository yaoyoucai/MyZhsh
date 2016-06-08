package com.myzhsh.pager.newscenter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.myzhsh.activity.MainActivity;
import com.myzhsh.activity.R;
import com.myzhsh.base.BaseNewsCenterPager;
import com.myzhsh.bean.NewsMenuData;

import java.util.ArrayList;
import java.util.List;

import shbd.viewpagerindicatorlibary.TabPageIndicator;

/**
 * Created by yh on 2016/6/2.
 * 新闻页签模块
 */
public class NewsDetailPager extends BaseNewsCenterPager implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;

    private TabPageIndicator mIndicator;
    private List<TabDetailPager> pagerList;

    private List<NewsMenuData.NewsData.NewsTabData> mDataList;

    public NewsDetailPager(Activity activity, List<NewsMenuData.NewsData.NewsTabData> dataList) {
        super(activity);
        this.mDataList = dataList;
        initData();
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity.getApplicationContext(), R.layout.pager_newsdetail, null);

        mIndicator = (TabPageIndicator) view.findViewById(R.id.zhsh_vpi_indicator);
        mViewPager = (ViewPager) view.findViewById(R.id.shbd_vp_newsdetail);

        return view;
    }

    @Override
    protected void initData() {
        pagerList = new ArrayList<>();
        for (NewsMenuData.NewsData.NewsTabData data : mDataList) {
            pagerList.add(new TabDetailPager(mActivity, data));
        }
        mViewPager.setAdapter(new NewsDetailPagerAdapter());
        mViewPager.addOnPageChangeListener(this);
        mIndicator.setViewPager(mViewPager);
    }


    class NewsDetailPagerAdapter extends PagerAdapter {
        @Override
        public CharSequence getPageTitle(int position) {
            return mDataList.get(position).getTitle();
        }

        @Override
        public int getCount() {
            return pagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager tabDetailPager = pagerList.get(position);
            tabDetailPager.initData();
            View view = tabDetailPager.getRootView();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position != 0) {
            setSlidingMenuEnable(false);
        } else {
            setSlidingMenuEnable(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * 设置是否禁用侧边栏
     *
     * @param enable
     */
    private void setSlidingMenuEnable(boolean enable) {
        MainActivity activity = (MainActivity) mActivity;
        SlidingMenu slidingMenu = activity.getSlidingMenu();

        if (enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
}
