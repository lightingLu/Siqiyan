package com.example.three.siqiyan.pager;

import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.three.siqiyan.R;
import com.example.three.siqiyan.base.BasePager;
import com.example.three.siqiyan.bean.ZhiBoInfo;
import com.example.three.siqiyan.view.RefreshLis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 直播
 * Created by Three on 2016/4/19
 */
public class ZhiboPager extends BasePager {
    RefreshLis zhiboListview;
    private int count = 0;
    OkHttpClient client;
    String path = "http://live.wallstreetcn.com/";
    List<ZhiBoInfo> zhiBoInfoList;
    String pa2 = "\"content\"><p>(.*?)</p>";//直播内容
    private ZhiboAdapter adapter;
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            //更新UI
            adapter.notifyDataSetChanged();
            zhiboListview.completeRefresh();
        };
    };

    public ZhiboPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        btnMenu.setVisibility(View.GONE);// 隐藏菜单按钮
        tvTitle.setText("直播");
        setSlidingMenuEnable(false);// 打开侧边栏
        zhiBoInfoList = new ArrayList<>();
        client = new OkHttpClient();
        View vv = View.inflate(mActivity, R.layout.zhibo_listview, null);
        zhiboListview = (RefreshLis) vv.findViewById(R.id.zhibo_listview);
        // 向FrameLayout中动态添加布局
        flContent.addView(vv);
        try {
            runString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        zhiboListview.setOnRefreshListener(new RefreshLis.OnRefreshListener() {
            @Override
            public void onPullRefresh() {
                requestDataFromServer(false);
            }

            @Override
            public void onLoadingMore() {
                requestDataFromServer(true);
            }
        });
    }
    /**
     * 模拟向服务器请求数据
     */
    private void requestDataFromServer(final boolean isLoadingMore){
        new Thread(){
            public void run() {
                SystemClock.sleep(2000);//模拟请求服务器的一个时间长度
                if(isLoadingMore){//加载下拉刷新的数据

                }else {//加载上拉加载

                }

                //在UI线程更新UI
                handler.sendEmptyMessage(0);
            };
        }.start();
    }
    class ZhiboAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return zhiBoInfoList.size();
        }

        @Override
        public Object getItem(int i) {
            return zhiBoInfoList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View vv;
            ZhiboViewHolder holder;
            if (view == null) {
                vv = View.inflate(mActivity, R.layout.zhibo_list_item, null);
                holder = new ZhiboViewHolder();
                holder.title = (TextView) vv.findViewById(R.id.zhibo_title);
                holder.zhiboShoucang = (ImageView) vv.findViewById(R.id.zhibo_shoucang);
                holder.zhibofenxiang = (ImageView) vv.findViewById(R.id.zhibo_fenxiang);
                vv.setTag(holder);
            } else {
                vv = view;
                holder = (ZhiboViewHolder) vv.getTag();
            }

            holder.title.setText(zhiBoInfoList.get(i).getContent());
            return vv;
        }
    }

    static class ZhiboViewHolder {
        TextView title;
        ImageView zhiboShoucang;
        ImageView zhibofenxiang;
    }

    /**
     * 请求网络数据
     *
     * @param url
     * @throws IOException
     */
    public void runString(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("dd", "dddd");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String ss = response.body().string();
                getZhiboTitle(ss);
                getZhiboTime(ss);

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ZhiboAdapter();
                        zhiboListview.setAdapter(adapter);
                    }
                });

            }
        });
    }

    /**
     * 正则表达式获取数据
     *
     * @param dates
     */

    private void getZhiboTitle(String dates) {

        Pattern p = Pattern.compile(pa2);
        Matcher m = p.matcher(dates);
        ZhiBoInfo info;
        while (m.find()) {
            MatchResult mr = m.toMatchResult();

            if (count < 30) {
                info = new ZhiBoInfo();
                info.setContent(mr.group(1).toString());
                zhiBoInfoList.add(info);
                count++;
                Log.v("ss", info.getContent());
                Log.v("ss", String.valueOf(zhiBoInfoList.size()));

            }
        }



    }
    String p3 = "<span class=\"time\">(.*?)</span></a>";
    private void getZhiboTime(String dates) {

        Pattern p = Pattern.compile(p3);
        Matcher m = p.matcher(dates);
        ZhiBoInfo info;
        List<String > list = new ArrayList<>();
        while (m.find()) {
            MatchResult mr = m.toMatchResult();

            if (count < 30) {
                list.add(mr.group(1).toString());
                count++;
                Log.v("pp", list.get(count));
                Log.v("pp", String.valueOf(list.size()));

            }
        }

    }
}
