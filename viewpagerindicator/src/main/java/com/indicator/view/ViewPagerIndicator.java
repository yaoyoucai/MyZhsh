package com.indicator.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.indicator.activity.R;

import java.util.List;

/**
 * Created by yh on 2016/6/3.
 * 指示器
 */
public class ViewPagerIndicator extends LinearLayout {
    //画笔
    private Paint mPaint;
    //画图类，依靠其画出小三角
    private Path mPath;

    //小三角在x轴的起始位置
    private int mInitTriangleX;
    //移动距离
    private int mTriangleX;

    //小三角宽度
    private int mTriangleWidth;
    //小三角高度
    private int mTriangleHeight;
    //
    private static final float RADIO_TRIANGLE_WIDTH = 1 / 6f;
    //默认显示的indicator条数
    private static final int COUNT_DEFAULT_TAB = 4;

    //当前可见的indicator条数
    private int mVisibleTableCount;

    //页面改变监听
    private OnPageChangeListener mListener;

    private ViewPager mViewPager;


    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        mVisibleTableCount = array.getInt(R.styleable.ViewPagerIndicator_visible_tab_count, COUNT_DEFAULT_TAB);
        array.recycle();

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setPathEffect(new CornerPathEffect(3));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mInitTriangleX + mTriangleX, getHeight() + 2);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mTriangleWidth = (int) (w / mVisibleTableCount * RADIO_TRIANGLE_WIDTH);
        mTriangleHeight = mTriangleWidth / 2;
        mInitTriangleX = w / mVisibleTableCount / 2 - mTriangleWidth / 2;
        initTriangle();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount == 0)
            return;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LinearLayout.LayoutParams params = (LayoutParams) childView.getLayoutParams();
            params.weight = 0;
            params.width = getScreenWidth() / mVisibleTableCount;
            childView.setLayoutParams(params);
        }

        setAllItemsClickWEvent();

        super.onFinishInflate();
    }

    private void initTriangle() {
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();
    }

    /**
     * 设置页面可见的indicator条数
     *
     * @param count
     */
    public void setVisibleCount(int count) {
        mVisibleTableCount = count;
    }

    public void setTabItemTitles(List<String> titleList) {
        if (titleList != null && titleList.size() > 0) {
            removeAllViews();
            for (String title : titleList) {
                addView(generateTextView(title));
            }
        }

        setAllItemsClickWEvent();
    }

    private View generateTextView(String title) {
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.width = getScreenWidth() / mVisibleTableCount;
        textView.setText(title);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.GRAY);
        textView.setLayoutParams(params);

        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return textView;
    }

    /**
     * 让指示器跟随手指滑动
     *
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {
        int tabWidth = getWidth() / mVisibleTableCount;
        mTriangleX = (int) (tabWidth * offset + position * tabWidth);

        /**
         * 移动整个indicator
         */
        if (position >= mVisibleTableCount - 2 && offset > 0 && getChildCount() > mVisibleTableCount && position < getChildCount() - 2) {
            if (mVisibleTableCount != 1) {
                scrollTo((int) ((tabWidth * (position - (mVisibleTableCount - 2))) + tabWidth * offset), 0);
            } else {
                scrollTo((int) (tabWidth * position + tabWidth * offset), 0);
            }
        }
        invalidate();
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public int getScreenWidth() {
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 高亮显示选中的标签页
     */
    private void highLightTextView(int position) {
        resetAllTextViewColor();
        View childView = getChildAt(position);
        if (childView != null) {
            if (childView instanceof TextView) {
                ((TextView) childView).setTextColor(Color.WHITE);
            }
        }
    }

    /**
     * 重置所有标签页的颜色
     */
    private void resetAllTextViewColor() {
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                if (childView instanceof TextView) {
                    TextView textView = (TextView) childView;
                    textView.setTextColor(Color.GRAY);
                }
            }
        }
    }

    private void setAllItemsClickWEvent() {
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                final int j = i;
                View childView = getChildAt(i);
                if (childView instanceof TextView) {
                    TextView textView = (TextView) childView;
                    textView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mViewPager.setCurrentItem(j);
                        }
                    });
                }
            }
        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scroll(position, positionOffset);
                if (mListener != null) {
                    mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (mListener != null) {
                    mListener.onPageSelected(position);
                }
                highLightTextView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mListener != null) {
                    mListener.onPageScrollStateChanged(state);
                }
            }
        });

        highLightTextView(0);
    }

    public void addOnPageChangeListener(OnPageChangeListener listener) {
        this.mListener = listener;
    }

    interface OnPageChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }


}
