package com.myzhsh.pager.newscenter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myzhsh.activity.R;
import com.myzhsh.adapter.NewsListAdapter;
import com.myzhsh.adapter.TopNewsAdapter;
import com.myzhsh.base.BaseNewsCenterPager;
import com.myzhsh.bean.NewsListData;
import com.myzhsh.bean.NewsMenuData;
import com.myzhsh.itemdecoration.DividerItemDecoration;
import com.myzhsh.utils.CacheUtils;
import com.myzhsh.utils.Constants;
import com.myzhsh.view.HorizontalScrollViewPager;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by yh on 2016/6/3.
 * 新闻页签
 */
public class TabDetailPager extends BaseNewsCenterPager {
    private NewsMenuData.NewsData.NewsTabData mData;

    private String mUrl;

    private HorizontalScrollViewPager mViewPager;

    private RecyclerView mRecyclerView;

    //标题
    private TextView mTvTitle;
    //图片轮播新闻数据
    private List<NewsListData.NewsTab.TopnewsBean> mTopNews;

    private FixedIndicatorView mIndicator;

    private LayoutInflater mInflater;
    private IndicatorViewPager mIndicatorViewPager;

    public TabDetailPager(Activity activity, NewsMenuData.NewsData.NewsTabData data) {
        super(activity);
        this.mData = data;
        this.mUrl = Constants.BASE_URL + data.getUrl();
        mInflater = LayoutInflater.from(mActivity.getApplicationContext());
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity.getApplicationContext(), R.layout.pager_tabdetail, null);
        mViewPager = (HorizontalScrollViewPager) view.findViewById(R.id.tabdetail_viewpager);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.tabdetail_recycerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        //给recyclerView添加分隔线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity.getBaseContext(), DividerItemDecoration.VERTICAL_LIST));

        mTvTitle = (TextView) view.findViewById(R.id.tabdetail_textview);
        mIndicator = (FixedIndicatorView) view.findViewById(R.id.tabdetail_guideindicator);
        mIndicatorViewPager = new IndicatorViewPager(mIndicator, mViewPager);

        mIndicatorViewPager.setOnIndicatorPageChangeListener(new IndicatorViewPager.OnIndicatorPageChangeListener() {
            @Override
            public void onIndicatorPageChange(int preItem, int currentItem) {
                NewsListData.NewsTab.TopnewsBean topnewsBean = mTopNews.get(currentItem);
                mTvTitle.setText(topnewsBean.getTitle());
            }

        });

        mViewPager.setCurrentItem(0);
        return view;
    }

    @Override
    protected void initData() {
        String cache = getDataFromCache();
        if (cache != null) {
            processResult(cache);
        }
        getDataFromServer();
    }

    /**
     * 从服务器获取数据
     */
    public void getDataFromServer() {
        RequestParams params = new RequestParams(mUrl);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //将数据存入缓存
                CacheUtils.putCache(mActivity.getApplicationContext(), mUrl, result);

                processResult(result);
            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 解析从服务器传过来的数据
     */
    private void processResult(String result) {
        Gson gson = new Gson();
        NewsListData newsListData = gson.fromJson(result, NewsListData.class);
        mTopNews = newsListData.getData().getTopnews();

        if (mTopNews != null && mTopNews.size() > 0) {
            //设置标题
            NewsListData.NewsTab.TopnewsBean topnewsBean = mTopNews.get(0);
            mTvTitle.setText(topnewsBean.getTitle());

            mIndicatorViewPager.setAdapter(new TopNewsAdapter(mActivity.getApplicationContext(), mInflater, mTopNews));

            mRecyclerView.setAdapter(new NewsListAdapter(mActivity.getApplicationContext(), mInflater, newsListData.getData().getNews()));

        }
    }

    public String getDataFromCache() {
        return CacheUtils.getCache(mActivity.getApplicationContext(), mUrl);
    }


}
