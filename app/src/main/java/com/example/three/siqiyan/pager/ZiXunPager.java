package com.example.three.siqiyan.pager;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.three.siqiyan.R;
import com.example.three.siqiyan.base.BaseMenuDetailPager;
import com.example.three.siqiyan.base.BasePager;
import com.example.three.siqiyan.bean.NewsInfo;
import com.example.three.siqiyan.http.HService;
import com.example.three.siqiyan.http.ZixunAPI;
import com.example.three.siqiyan.menupager.CommentMenuPager;
import com.example.three.siqiyan.menupager.HomeMenuPager;
import com.example.three.siqiyan.menupager.PushMenuPager;
import com.example.three.siqiyan.menupager.SubscribeMenuPager;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 资讯首页
 * Created by Three on 2016/4/19.
 */
public class ZiXunPager extends BasePager {
    private ArrayList<BaseMenuDetailPager> pagerList;
    private HService service;
    private ZixunAPI zixunAPI;
    private LinearLayout ll;
    private ListView listView;
    private ViewPager viewPager;
    private List<NewsInfo.NewslistBean> newslist;
    private List<NewsInfo.TopicBean> topic;

    public ZiXunPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("华尔街见闻");// 修改标题
        btnMenu.setVisibility(View.VISIBLE);// 隐藏菜单按钮
        setSlidingMenuEnable(true);//关闭侧边栏
        flContent.removeAllViews();//清除先前的绘图
        addMenuPager();
        //获取网络请求接口
        zixunAPI = ZixunAPI.getAPI();
        service = zixunAPI.getService();
        //定义listview
        View v = View.inflate(mActivity, R.layout.zixun_listview, null);//找到listview所在的布局
        View headview = View.inflate(mActivity, R.layout.headview, null);//找到viewpager所在的布局
        viewPager = (ViewPager) headview.findViewById(R.id.zixun_viewpager);
        listView = (ListView) v.findViewById(R.id.zixun_listview);
        listView.addHeaderView(headview);//给listview添加头布局
        flContent.addView(v);//给framelayout添加视图
        getJsonResult();//请求网络数据

    }

    /**
     * 为pagerlist添加MenuPager
     */
    public void addMenuPager() {
        pagerList = new ArrayList<>();
        pagerList.add(new HomeMenuPager(mActivity));
        pagerList.add(new SubscribeMenuPager(mActivity));
        pagerList.add(new CommentMenuPager(mActivity));
        pagerList.add(new PushMenuPager(mActivity));
    }

    /**
     * 在资讯页面设置侧边栏pager
     */
    public void setCurrentMenuPager(int position, String title) {
        BaseMenuDetailPager menuPsger = pagerList.get(position);
        flContent.removeAllViews();//清除baserpager里面framelayout的布局（中间显示的部分）
        flContent.addView(menuPsger.mRootView);
        tvTitle.setText(title);//设置当前menupager的标题
        menuPsger.initData();//初始化数据
    }

    /**
     * 请求网络获取数据
     */
    public void getJsonResult() {
        Observable<NewsInfo> observable = service.getResult();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsInfo>() {
                    @Override
                    public void onCompleted() {
                        Log.v("=======III===", "完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mActivity, "请检查您的网络", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(NewsInfo newsInfo) {
                        newslist = newsInfo.getNewslist();
                        topic = newsInfo.getTopic();
                        viewPager.setAdapter(new TopAdapter());
                        listView.setAdapter(new ZiXunAdapter());//给listview设置适配器

                    }
                });
    }

    /**
     * listview的适配器
     */
    class ZiXunAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return newslist.size();
        }

        @Override
        public Object getItem(int i) {
            return newslist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View vv;
            ViewHolder holder;
            if (view == null) {
                vv = View.inflate(mActivity, R.layout.zixun_list_item, null);
                holder = new ViewHolder();
                holder.title = (TextView) vv.findViewById(R.id.list_title);
                holder.time = (TextView) vv.findViewById(R.id.list_time);
                holder.imageView = (ImageView) vv.findViewById(R.id.list_img);
                vv.setTag(holder);
            } else {
                vv = view;
                holder = (ViewHolder) vv.getTag();
            }
            holder.title.setText(newslist.get(i).getTitle());
            holder.time.setText(newslist.get(i).getPubdate());
            //Glide加载图片
            Glide.with(mActivity)
                    .load(newslist.get(i).getListimage())
                    .override(dip2px(100), dip2px(70))  //重新绘制图片大小
                    .placeholder(R.mipmap.holder_image)  //默认图片和加载错误的图片
                    .error(R.mipmap.holder_image)
                    .into(holder.imageView);
            return vv;
        }
    }

    static class ViewHolder {
        TextView title;
        TextView time;
        ImageView imageView;

    }

    /**
     * viewpage适配器
     */
    class TopAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return topic.size();
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(mActivity,R.layout.zixun_viewpager,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.vp_image);
            TextView title = (TextView) view.findViewById(R.id.vp_title);
            title.setText(topic.get(position).getToptitle());
            //Glide加载图片
            Glide.with(mActivity)
                    .load(topic.get(position).getTopimage())
//                    .override(dip2px(), dip2px(70))  //重新绘制图片大小
                    .placeholder(R.mipmap.holder_special)  //默认图片和加载错误的图片
                    .error(R.mipmap.holder_special)
                    .into(imageView);
            container.addView(view);
            return view;
        }


    }

    /**
     * dp转px
     *
     * @param dip
     * @return
     */
    public int dip2px(int dip) {
        float scale = mActivity.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }
}
