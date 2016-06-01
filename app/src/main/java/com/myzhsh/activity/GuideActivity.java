package com.myzhsh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.myzhsh.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yh on 2016/5/31.
 * 新手引导页面
 */
public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mVpGuide;

    //小红点
    private ImageView mIvSelected;

    //开始体验的按钮
    private Button mBtnStart;
    //两个小圆点之间的距离
    private int mPointDistance;

    private LinearLayout mLlGuide;
    private List<ImageView> imageViewList=new ArrayList<>();
    private int[] imageList=new int[]{
            R.drawable.guide_1,
            R.drawable.guide_2,
            R.drawable.guide_3
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initData();

        mIvSelected= (ImageView) findViewById(R.id.zhsh_iv_dot_selected);

        mLlGuide= (LinearLayout) findViewById(R.id.zhsh_ll_guide);

        mBtnStart= (Button) findViewById(R.id.shbd_btn_start);
        mBtnStart.setOnClickListener(this);

        mVpGuide= (ViewPager) findViewById(R.id.zhsh_vp_guide);
        mVpGuide.setAdapter(new ViewPagerAdapter());

        mVpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int moveDistance= (int) (mPointDistance*positionOffset+position*mPointDistance);
                RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) mIvSelected.getLayoutParams();
                params.leftMargin=moveDistance;
                mIvSelected.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                //当到达最后一页时，显示“开始体验”按钮，其他页则隐藏
                if (position==imageViewList.size()-1){
                    mBtnStart.setVisibility(View.VISIBLE);
                }else {
                    mBtnStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //当页面绘制结束后，计算两个小圆点之间的距离
        mLlGuide.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //移除监听
                mLlGuide.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                View firstChild = mLlGuide.getChildAt(0);
                View secondChild = mLlGuide.getChildAt(1);
                mPointDistance=secondChild.getLeft()-firstChild.getLeft();

            }
        });
    }

    private void initData() {
        //初始化三张图片
        for (int i=0;i<imageList.length;i++){
            ImageView imageView=new ImageView(getApplicationContext());
            imageView.setBackgroundResource(imageList[i]);
            imageViewList.add(imageView);
        }
    }

    class ViewPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViewList.get(position));


            return imageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView)object);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shbd_btn_start:
                Intent intent=new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

                PrefUtils.putBoolean("firstCome",false,getApplicationContext());
                break;
        }
    }

}

