package com.myzhsh.fragment.main;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.myzhsh.activity.MainActivity;
import com.myzhsh.activity.R;
import com.myzhsh.base.BaseFragment;
import com.myzhsh.base.BasePager;
import com.myzhsh.pager.GovaffairsPager;
import com.myzhsh.pager.HomePager;
import com.myzhsh.pager.NewsCenterPager;
import com.myzhsh.pager.SettingPager;
import com.myzhsh.pager.SmartServicePager;
import com.myzhsh.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yh on 2016/6/1.
 * 主体fragment
 */
public class ContentFragment extends BaseFragment {
    private NoScrollViewPager mVpContent;

    private RadioGroup mRgContent;
    private List<BasePager> mPagerList;


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        mVpContent = (NoScrollViewPager) view.findViewById(R.id.shbd_vp_content);
        mRgContent = (RadioGroup) view.findViewById(R.id.shbd_rg_content);
        return view;
    }


    public void initData() {
        mPagerList = new ArrayList<>();
        mPagerList.add(new HomePager(getContext()));
        mPagerList.add(new NewsCenterPager(getContext()));
        mPagerList.add(new SmartServicePager(getContext()));
        mPagerList.add(new GovaffairsPager(getContext()));
        mPagerList.add(new SettingPager(getContext()));

        mVpContent.setAdapter(new ContentAdapter());

        //点击底部页签切换页面
        mRgContent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.shbd_rb_home:
                        changePager(0,false);
                        break;
                    case R.id.shbd_rb_newscenter:
                        changePager(1,true);
                        break;
                    case R.id.shbd_rb_smartservice:
                        changePager(2,true);
                        break;
                    case R.id.shbd_rb_govaffairs:
                        changePager(3,true);
                        break;
                    case R.id.shbd_rb_setting:
                        changePager(4,false);
                        break;
                }
            }
        });

        //首次进入时，默认选中首页
        mRgContent.check(R.id.shbd_rb_home);
    }


    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mPagerList.get(position).getView();
            container.addView(view);
            return view;
        }

    }


    /**
     * 设置是否禁用侧边栏
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
     *切换页面
     */
    private void changePager(int currentItem, boolean slidingMenuEnable) {
        mVpContent.setCurrentItem(currentItem, false);
        mPagerList.get(currentItem).initData();
        setSlidingMenuEnable(slidingMenuEnable);
    }

}
