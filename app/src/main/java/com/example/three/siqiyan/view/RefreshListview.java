package com.example.three.siqiyan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.three.siqiyan.R;

/**
 * Created by Three on 2016/4/24.
 * 自定义下拉刷新Listview
 */
public class RefreshListview extends ListView implements AbsListView.OnScrollListener {

    private ImageView imageView;
    private int headViewHight;//头布局的高度
    private int startY;//初始的位置
    private int endY;//按下的位置
    private int moveLength; //滑动的长度
    private View headView;
    private final int PULL_REFRESH = 0;// 下拉刷新的状态
    private final int RELEASE_REFRESH = 1;// 松开刷新的状态
    private final int REFRESHING = 2;// 正在刷新的状态
    private int currentState = PULL_REFRESH;
    private AlphaAnimation animation;
    private int footerViewHeight;
    private View footerView;
    private boolean isLoadingMore = false;// 当前是否正在处于加载更多

    public RefreshListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public RefreshListview(Context context) {
        super(context);
        init();
    }

    public RefreshListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        setOnScrollListener(this);
        initHeadView();
        initAlphaAnimation();
        initFooterView();
    }

    /**
     * 初始化alpha动画
     */
    private void initAlphaAnimation() {
        animation = new AlphaAnimation(1, 0);
        animation.setDuration(1000);
        animation.setRepeatCount(10);
        animation.setFillAfter(true);
    }

    /**
     * 初始化头布局
     */
    private void initHeadView() {
        headView = View.inflate(getContext(), R.layout.layout_refresh_header, null);
        imageView = (ImageView) headView.findViewById(R.id.refresh_header);
        headView.measure(0, 0);
        headViewHight = headView.getMeasuredHeight();
        Log.v("头布局的高度", String.valueOf(headViewHight));
        headView.setPadding(0, -headViewHight, 0, 0);
        addHeaderView(headView);
    }

    /**
     * 初始化脚布局
     */
    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.layout_refresh_footer, null);
        footerView.measure(0, 0);// 主动通知系统去测量该view;
        footerViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        addFooterView(footerView);
    }

    /**
     * listview 的触摸事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) ev.getY();//获取按下点的Y坐标
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentState == REFRESHING) {//如果当前的状态为freshing,不能刷新
                    break;
                }
                endY = (int) ev.getY();//获取滑动点的坐标
                moveLength = endY - startY;//padding值

                int padding = moveLength - headViewHight;
                if (padding > -headViewHight && getFirstVisiblePosition() == 0) {

                    headView.setPadding(0, padding, 0, 0);
                    if (padding >= 0 && currentState == PULL_REFRESH) {
                        currentState = PULL_REFRESH;
                        refreshHeadView();
                    } else if (padding < 0 && currentState == RELEASE_REFRESH) {
                        currentState = PULL_REFRESH;
                        refreshHeadView();
                    }
                    return true;// 拦截TouchMove，不让listview处理该次move事件,会造成listview无法滑动
                }

                break;
            case MotionEvent.ACTION_UP:
                if (currentState == PULL_REFRESH) {
                    // 隐藏headerView
                    headView.setPadding(0, -headViewHight, 0, 0);
                } else if (currentState == RELEASE_REFRESH) {
                    headView.setPadding(0, 0, 0, 0);
                    currentState = REFRESHING;
                    refreshHeadView();// 根据currentState来更新headerView
                    if (listener != null) {
                        listener.onPullRefresh();
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (currentState == REFRESHING) {
            return true;
        }
        return false;
    }

    /**
     * 更新的头布局
     */
    private void refreshHeadView() {
        switch (currentState) {
            case PULL_REFRESH:
                imageView.startAnimation(animation);
                break;
            case RELEASE_REFRESH:
                imageView.startAnimation(animation);
                break;
            case REFRESHING:
                imageView.startAnimation(animation);
                break;
        }
    }

    private OnFreshListener listener;

    public void setOnRefreshListener(OnFreshListener listener) {
        this.listener = listener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
                && getLastVisiblePosition() == (getCount() - 1)
                && !isLoadingMore) {
            isLoadingMore = true;
            footerView.setPadding(0, 0, 0, 0);// 显示出footerView
            setSelection(getCount());// 让listview最后一条显示出来
            // 同上下拉刷新一样
            if (listener != null) {
                listener.onLoadingMore();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    public interface OnFreshListener {
        void onPullRefresh();

        void onLoadingMore();
    }

    /**
     * 完成刷新操作，重置状态,在你获取完数据并更新完adater之后，去在UI线程中调用该方法
     */
    public void completeRefresh() {
        if (isLoadingMore) {
            // 重置footerView状态
            footerView.setPadding(0, -footerViewHeight, 0, 0);
            isLoadingMore = false;
        } else {
            //  重置headerView状态
            headView.setPadding(0, -headViewHight, 0, 0);
            currentState = PULL_REFRESH;

//        }
        }
    }


}
