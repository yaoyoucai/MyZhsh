package com.myzhsh.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yh on 2016/5/31.
 * 新手引导页面
 */
public class GuideActivity extends AppCompatActivity {
    private ViewPager mVpGuide;

    //小红点
    private ImageView mIvSelected;

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

        mVpGuide= (ViewPager) findViewById(R.id.zhsh_vp_guide);
        mVpGuide.setAdapter(new ViewPagerAdapter());

        mVpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("TAG", "positionOffset:"+positionOffset);
                Log.d("TAG", "positionOffsetPixels:"+positionOffsetPixels);
     /*           RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) mIvSelected.getLayoutParams();
                params.leftMargin=positionOffsetPixels;*/
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
}
