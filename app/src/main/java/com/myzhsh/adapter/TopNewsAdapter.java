package com.myzhsh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myzhsh.activity.R;
import com.myzhsh.bean.NewsListData;
import com.shizhefei.view.indicator.IndicatorViewPager;

import org.xutils.x;

import java.util.List;

/**
 * 轮播图片adapter
 */
public class TopNewsAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
    private LayoutInflater mInflater;
    private List<NewsListData.NewsTab.TopnewsBean> mTopNews;
    private Context mContext;

    public TopNewsAdapter(Context context, LayoutInflater inflater, List<NewsListData.NewsTab.TopnewsBean> topNews) {
        this.mContext = context;
        this.mInflater = inflater;
        this.mTopNews = topNews;
    }

    @Override
    public int getCount() {
        return mTopNews.size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.tab_guide, container, false);
        }
        return convertView;
    }

    @Override
    public View getViewForPage(int position, View convertView, ViewGroup container) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        x.image().bind(imageView, mTopNews.get(position).getTopimage());
        return imageView;
    }
}