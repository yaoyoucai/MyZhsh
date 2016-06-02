package com.myzhsh.pager.tab;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myzhsh.activity.MainActivity;
import com.myzhsh.base.BaseNewsCenterPager;
import com.myzhsh.base.BasePager;
import com.myzhsh.bean.NewsMenuData;
import com.myzhsh.fragment.main.LeftMenuFragment;
import com.myzhsh.pager.newscenter.GroupIconDetailPager;
import com.myzhsh.pager.newscenter.InteractDetailPager;
import com.myzhsh.pager.newscenter.NewsDetailPager;
import com.myzhsh.pager.newscenter.SubjectDetailPager;
import com.myzhsh.utils.Constants;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yh on 2016/6/1.
 */
public class NewsCenterPager extends BasePager {
    private List<BaseNewsCenterPager> newsCenterPagerList = new ArrayList<>();

    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        mTvTitle.setText("新闻中心");

        getDataFromServer();
    }

    public void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.CATEGORIES_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
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
     *
     * @param result
     */
    private void processResult(String result) {
        Gson gson = new Gson();
        NewsMenuData newsMenuData = gson.fromJson(result, NewsMenuData.class);
        MainActivity activity = (MainActivity) mActivity;
        LeftMenuFragment leftMenuFragment = activity.getLeftMenuFragment();
        leftMenuFragment.setData(newsMenuData.getData());

        newsCenterPagerList.add(new NewsDetailPager(mActivity));
        newsCenterPagerList.add(new SubjectDetailPager(mActivity));
        newsCenterPagerList.add(new GroupIconDetailPager(mActivity));
        newsCenterPagerList.add(new InteractDetailPager(mActivity));
    }

    public void loadDetailPager(int position) {
        mFlContent.removeAllViews();
        TextView te
                = (TextView) newsCenterPagerList.get(position).getRootView();
        System.out.println(te.getText());
        mFlContent.addView(te);
    }
}
