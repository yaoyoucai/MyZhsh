package com.myzhsh.pager.newscenter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.myzhsh.activity.MainActivity;
import com.myzhsh.activity.R;
import com.myzhsh.base.BaseNewsCenterPager;
import com.myzhsh.bean.NewsMenuData;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yh on 2016/6/2.
 * 新闻页签模块
 */
public class NewsDetailPager extends BaseNewsCenterPager implements IndicatorViewPager.OnIndicatorPageChangeListener {
    private ViewPager mViewPager;

    private List<TabDetailPager> pagerList;

    //右侧小箭头
    private ImageView mIvArr;

    private List<NewsMenuData.NewsData.NewsTabData> mDataList;

    private ScrollIndicatorView mIndicator;

    private IndicatorViewPager indicatorViewPager;

    private LayoutInflater mInflater;

    private NewsDetailPagerAdapter mAdapter;

    public NewsDetailPager(Activity activity, List<NewsMenuData.NewsData.NewsTabData> dataList) {
        super(activity);
        this.mDataList = dataList;
        this.mInflater = LayoutInflater.from(activity.getApplicationContext());
        initData();
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity.getApplicationContext(), R.layout.pager_newsdetail, null);

        mIvArr = (ImageView) view.findViewById(R.id.shbd_iv_arr);
        //点击右侧小箭头，跳转到下一个页面
        mIvArr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewPager.getCurrentItem() < pagerList.size() - 1) {
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                }
            }
        });

        mViewPager = (ViewPager) view.findViewById(R.id.shbd_vp_newsdetail);
        mViewPager.setOffscreenPageLimit(2);

        mIndicator = (ScrollIndicatorView) view.findViewById(R.id.fragment_tabmain_indicator);
        //线条的颜色
        int selectColor = Color.parseColor("#ec0200");
        mIndicator.setScrollBar(new ColorBar(mActivity.getApplicationContext(), selectColor, 5, ScrollBar.Gravity.BOTTOM));
        return view;
    }

    @Override
    protected void initData() {
        /**
         * 初始化12个页签对象
         */
        pagerList = new ArrayList<>();
        for (NewsMenuData.NewsData.NewsTabData data : mDataList) {
            pagerList.add(new TabDetailPager(mActivity, data));
        }

        indicatorViewPager = new IndicatorViewPager(mIndicator, mViewPager);
        mAdapter = new NewsDetailPagerAdapter();
        indicatorViewPager.setAdapter(mAdapter);
        indicatorViewPager.setOnIndicatorPageChangeListener(this);
    }

    @Override
    public void onIndicatorPageChange(int preItem, int currentItem) {
        if (currentItem != 0) {
            setSlidingMenuEnable(false);
        } else {
            setSlidingMenuEnable(true);
        }
    }


    class NewsDetailPagerAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(mDataList.get(position % pagerList.size()).getTitle());
            int padding = dipToPix(10);
            textView.setPadding(padding, 0, padding, 0);
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            TabDetailPager tabDetailPager = pagerList.get(position);
            tabDetailPager.initData();
            View view = tabDetailPager.getRootView();
            return view;
        }

        @Override
        public int getCount() {
            return pagerList.size();
        }
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

    /**
     * 根据dip值转化成px值
     *
     * @param dip
     * @return
     */
    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, mActivity.getResources().getDisplayMetrics());
        return size;
    }

}
