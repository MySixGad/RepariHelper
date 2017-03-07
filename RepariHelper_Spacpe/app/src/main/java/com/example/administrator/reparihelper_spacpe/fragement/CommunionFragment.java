package com.example.administrator.reparihelper_spacpe.fragement;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.reparihelper_spacpe.R;
import com.example.administrator.reparihelper_spacpe.bean.Info_Bean;
import com.example.administrator.reparihelper_spacpe.utils.HeaderUtil;

import java.sql.Time;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2017/1/8.
 */

public class CommunionFragment  extends Fragment {

    private BGARefreshLayout bga_lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.communion_frament, null);
        ListView lv =(ListView) view.findViewById(R.id.lv);
        lv.setAdapter(new Myadapte());
        bga_lv = (BGARefreshLayout)view.findViewById(R.id.rgb_lv);

        //添加头部仕途  还要实现业务逻辑
        BGANormalRefreshViewHolder bgaNormalRefreshViewHolder = new BGANormalRefreshViewHolder(getActivity(),true);
        bga_lv.setRefreshViewHolder(HeaderUtil.getStickyHeader(container.getContext()));

        bga_lv.setIsShowLoadingMoreView(true);

        BGARefreshLayout.BGARefreshLayoutDelegate listener=new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

                //handler延时
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       bga_lv.endRefreshing();
                       Toast.makeText(container.getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                   }
               },2000);


            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bga_lv.endLoadingMore();
                        Toast.makeText(container.getContext(), "加载更多", Toast.LENGTH_SHORT).show();
                    }
                },2000);

                return true;
            }
        };

        bga_lv.setDelegate(listener);



        return view;
    }


    class Myadapte extends BaseAdapter{

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view_item = View.inflate(getActivity(), R.layout.commition, null);

            TextView manger = (TextView) view_item.findViewById(R.id.manger);


            return view_item;
        }
    }
}
