package com.myzhsh.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.myzhsh.utils.PrefUtils;

/**
 * Created by yh on 2016/5/31.
 * 闪屏页面
 */
public class SplashActivity extends Activity {
    private RelativeLayout mRlSplash;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mRlSplash= (RelativeLayout) findViewById(R.id.zhsh_rl_splash);

        //动画集合
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.guide_anim);

        //启动动画
        mRlSplash.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean firstCome =  PrefUtils.getBoolean("firstCome",true,getApplicationContext());
                Intent intent=new Intent();
                if (firstCome){
                    //跳转到新手引导页面
                    intent.setClass(SplashActivity.this,GuideActivity.class);
                }
                else {
                    //跳转到主页面
                    intent.setClass(SplashActivity.this,MainActivity.class);
                }

                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
