package com.example.three.siqiyan.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.three.siqiyan.R;
import com.example.three.siqiyan.view.RefreshLis;

/**
 * Created by Three on 2016/6/30.
 */
public class ShangpinFragment extends HangqingBaseFragment {
    private com.example.three.siqiyan.view.RefreshLis listview;

    @Override
    public View initViews() {
        View view = View.inflate(getContext(), R.layout.h_shangpin, null);
        listview = (RefreshLis) view.findViewById(R.id.shangpin_listview);
        if (info!=null){
                listview.setAdapter(new MyAdapter());
        }
        return view;

    }

    @Override
    public void initData() {
//        listview.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return info.getShangpin().size();
        }

        @Override
        public Object getItem(int i) {
            return info.getShangpin().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View content = null;
            ViewHolder holder;
            if (view == null) {
                content = View.inflate(getContext(), R.layout.hangqin_listview, null);
                holder = new ViewHolder();
                holder.shangLeft = (TextView) content.findViewById(R.id.shang_left);
                holder.shangCenter = (TextView) content.findViewById(R.id.shang_center);
                holder.shangRight = (TextView) content.findViewById(R.id.shang_right);
                content.setTag(holder);
            } else {
                content = view;
                holder = (ViewHolder) content.getTag();
            }
            holder.shangLeft.setText(info.getShangpin().get(i).getName());
            holder.shangCenter.setText(info.getShangpin().get(i).getPrice());
            holder.shangRight.setText(info.getShangpin().get(i).getUpordown());
            int state = Integer.parseInt(info.getShangpin().get(i).getColorstate());
            if (state != 0) {
                if (state == 1) {
                    holder.shangRight.setBackgroundColor(Color.RED);
                }
                if (state == 2) {
                    holder.shangRight.setBackgroundColor(Color.GREEN);
                }
            }


            return content;
        }
    }

    static class ViewHolder {
        private TextView shangLeft;
        private TextView shangCenter;
        private TextView shangRight;

    }
}
