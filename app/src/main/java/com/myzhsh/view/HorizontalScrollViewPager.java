package com.myzhsh.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by yh on 2016/6/17.
 */
public class HorizontalScrollViewPager extends ViewPager {

    private int startX;
    private int startY;

    public HorizontalScrollViewPager(Context context) {
        super(context);
    }

    public HorizontalScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 第一个页面，当前页为首页时，请求父控件不要拦截
     * 当前页为最后当前页签最后一页时，请求父控件不要拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                //请求父控件不要拦截
                requestDisallowInterceptTouchEvent(true);
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();

                int dx=endX-startX;
                int dy=endY-startY;

                //左右滑
                if (Math.abs(dx)>Math.abs(dy)){
                    //向右滑
                    if (dx>0){
                        if (getCurrentItem()==0){
                            requestDisallowInterceptTouchEvent(false);
                        }
                    }
                    else {
                      if (getCurrentItem()==getAdapter().getCount()-1){
                          requestDisallowInterceptTouchEvent(false);
                      }
                    }
                }
                else {
                    requestDisallowInterceptTouchEvent(false);
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
