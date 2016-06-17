package com.myzhsh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myzhsh.activity.R;
import com.myzhsh.bean.NewsListData;
import com.myzhsh.viewholder.NewsListViewHolder;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by yh on 2016/6/17.
 * 新闻列表adapter
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListViewHolder> {
    private List<NewsListData.NewsTab.NewsBean> mNews;
    private LayoutInflater mInflater;
    private Context mContext;

    public final static int TYPE_HEADER = 0;
    public final static int TYPE_BODY = 1;

    public NewsListAdapter(Context context, LayoutInflater inflater, List<NewsListData.NewsTab.NewsBean> news) {
        this.mContext = context;
        this.mInflater = inflater;
        this.mNews = news;
    }

    @Override
    public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_tabdetail, parent, false);
        NewsListViewHolder viewHolder = new NewsListViewHolder(view,viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {
        ImageOptions.Builder builder = new ImageOptions.Builder();
        ImageOptions options = builder.build();
        builder.setLoadingDrawableId(R.drawable.news_pic_default);
        holder.mIvIcon.setScaleType(ImageView.ScaleType.FIT_XY);
        x.image().bind(holder.mIvIcon, mNews.get(position).getListimage(), options);

        holder.mTvTitle.setText(mNews.get(position).getTitle());
        holder.mTvTime.setText(mNews.get(position).getPubdate());
    }

    @Override
    public int getItemCount() {
        return mNews.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_BODY;
        }
    }


}
