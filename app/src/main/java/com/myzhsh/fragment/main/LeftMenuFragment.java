package com.myzhsh.fragment.main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.myzhsh.activity.MainActivity;
import com.myzhsh.activity.R;
import com.myzhsh.base.BaseFragment;
import com.myzhsh.bean.NewsMenuData;
import com.myzhsh.pager.tab.NewsCenterPager;

import java.util.List;

/**
 * Created by yh on 2016/6/1.
 */
public class LeftMenuFragment extends BaseFragment {
    private ListView mLvMenuContent;

    private List<NewsMenuData.NewsData> mDataList;

    private LeftMenuAdapter mAdapter;

    //当前菜单项
    private int mCurrentPosition;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_leftmenu, null);
        mLvMenuContent = (ListView) view.findViewById(R.id.shbd_lv_menucontent);
        return view;
    }

    public void setData(List<NewsMenuData.NewsData> data) {
        this.mDataList = data;

        mAdapter = new LeftMenuAdapter();
        mLvMenuContent.setAdapter(mAdapter);
        mLvMenuContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPosition = position;
                loadDetailPager(mCurrentPosition);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    class LeftMenuAdapter extends BaseAdapter {
        private LeftMenuViewHolder viewHolder;

        @Override
        public int getCount() {
            return mDataList.size();
        }

        @Override
        public NewsMenuData.NewsData getItem(int position) {
            return mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mActivity.getApplicationContext(), R.layout.list_item_left_menu, null);
                viewHolder = new LeftMenuViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.shbd_tv_leftmenu_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (LeftMenuViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(mDataList.get(position).getTitle());

            //判断当前页签是否应当被显示
            if (mCurrentPosition == position) {
                viewHolder.textView.setEnabled(true);
            } else {
                viewHolder.textView.setEnabled(false);
            }

            loadDetailPager(mCurrentPosition);
            return convertView;
        }
    }


    class LeftMenuViewHolder {
        TextView textView;
    }

    /**
     * 加载侧边栏详情页
     *
     * @param mCurrentPosition
     */
    private void loadDetailPager(int mCurrentPosition) {
        MainActivity activity = (MainActivity) mActivity;
        ContentFragment contentFragment = activity.getContentFragment();
        NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager();
        newsCenterPager.loadDetailPager(mCurrentPosition);

    }
}
