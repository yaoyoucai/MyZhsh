package com.myzhsh.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.myzhsh.fragment.main.ContentFragment;
import com.myzhsh.fragment.main.LeftMenuFragment;

public class MainActivity extends AppCompatActivity {
    public static final String TAG_FRAGMENT_LEFTMENU = "left_menu";
    public static final String TAG_FRAGMENT_CONTNET = "content";

    private SlidingMenu mSlidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使屏幕全屏
        setContentView(R.layout.activity_main);

        mSlidingMenu = new SlidingMenu(getApplicationContext());
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setBehindOffset(300);
        mSlidingMenu.attachToActivity(MainActivity.this, SlidingMenu.SLIDING_WINDOW);
        mSlidingMenu.setMenu(R.layout.left_menu);

        initFragment();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fTrans = fm.beginTransaction();

        fTrans.replace(R.id.shbd_fl_content, new ContentFragment(), TAG_FRAGMENT_CONTNET);
        fTrans.replace(R.id.shbd_fl_leftmenu, new LeftMenuFragment(), TAG_FRAGMENT_LEFTMENU);

        fTrans.commit();
    }

    public SlidingMenu getSlidingMenu() {
        return mSlidingMenu;
    }

    /**
     * 获取到侧边栏fragment对象
     * @return
     */
    public LeftMenuFragment getLeftMenuFragment() {
        FragmentManager fm = getSupportFragmentManager();
        return (LeftMenuFragment) fm.findFragmentByTag(TAG_FRAGMENT_LEFTMENU);
    }

    /**
     * 获取到主体页fragment对象
     * @return
     */
    public ContentFragment getContentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        return (ContentFragment) fm.findFragmentByTag(TAG_FRAGMENT_CONTNET);
    }
}
