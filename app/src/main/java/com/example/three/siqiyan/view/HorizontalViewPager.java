package com.example.three.siqiyan.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Three on 2016/4/22.
 * 资讯页面，为了让viewpager正常滑动，右划不显示slidingmenu所以为它添加了一个事件分发
 *
 */
public class HorizontalViewPager extends ViewPager {
    public HorizontalViewPager(Context context) {
        super(context);
    }

    public HorizontalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 事件分发请求父控件及祖宗控件是否拦截
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (getCurrentItem() != 0) {//不是第一个页面，不拦截
            getParent().requestDisallowInterceptTouchEvent(true);// 用getParent去请求,
            //父控件 不拦截子控件的动作
//        } else {// 如果是第一个页面,需要显示侧边栏, 请求父控件拦截
//            getParent().requestDisallowInterceptTouchEvent(false);// 拦截
//        }
        return super.dispatchTouchEvent(ev);
    }

}
