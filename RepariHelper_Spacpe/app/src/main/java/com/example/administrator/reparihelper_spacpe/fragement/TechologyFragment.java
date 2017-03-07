package com.example.administrator.reparihelper_spacpe.fragement;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.reparihelper_spacpe.R;
import com.example.administrator.reparihelper_spacpe.bean.technology_center;
import com.example.administrator.reparihelper_spacpe.utils.HeaderUtil;
import com.example.administrator.reparihelper_spacpe.utils.IntentToogle;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by Administrator on 2017/1/8.
 */

public class TechologyFragment extends Fragment {

    private BGARefreshLayout bga_lv;
    private TextView intent_info;
    public List<technology_center> results_intent;
    public List<technology_center> results;
    private ListView lv;
    private com.example.administrator.reparihelper_spacpe.fragement.state_layout state_layout;
    private LinearLayout llll;
    private View view_main;
    private BmobFile bmobFile;

    private int ACTION_PULL=1;
    private int ACTION=0;
    private Myadapte myadapte;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        state_layout = new state_layout(container.getContext());

        view_main = inflater.inflate(R.layout.communion_frament, null);

        lv = (ListView) view_main.findViewById(R.id.lv);
        bga_lv = (BGARefreshLayout) view_main.findViewById(R.id.rgb_lv);
        intent_info = (TextView) view_main.findViewById(R.id.intent_info);
        ACTION_PULL=0;
        state_layout.addNormalView(view_main);

        //访问服务器  抓取数据
        getServerData();

        //刷新UI 上拉刷新
        initRefreshUi(container);

        return state_layout;
    }



    private void initRefreshUi(final ViewGroup container ) {

        //添加头部  还要实现业务逻辑 刷新和加载
        BGANormalRefreshViewHolder bgaNormalRefreshViewHolder = new BGANormalRefreshViewHolder(getActivity(),true);
        bga_lv.setRefreshViewHolder(HeaderUtil.getStickyHeader(container.getContext()));

        bga_lv.setIsShowLoadingMoreView(true);

        BGARefreshLayout.BGARefreshLayoutDelegate listener=new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                ACTION_PULL=1;
                getServerData();

                Toast.makeText(container.getContext(), "刷新成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bga_lv.endLoadingMore();
                        Toast.makeText(container.getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }
                },2000);

                return true;
            }
        };

        bga_lv.setDelegate(listener);
    }


    /**
     *  访问服务器  抓取数据
     */
      private void getServerData(){
        if(!IntentToogle.isnetWorkAvilable(bga_lv.getContext())){
            state_layout.resetAll();
            state_layout.showNormal();
            return;
        }else{
            intent_info.setVisibility(View.GONE);
        }


          if(ACTION_PULL==0){  //菲下拉刷新
              state_layout.showLoad();
          }

          if(myadapte==null){
              myadapte = new Myadapte();
          }
              technology_center technology_center = new technology_center();
              BmobQuery<technology_center> bmobQuery = new  BmobQuery<technology_center>();

              bmobQuery.doSQLQuery("select * from technology_center ",new SQLQueryListener<com.example.administrator.reparihelper_spacpe.bean.technology_center>() {
                  @Override
                  public void done(BmobQueryResult<technology_center> bmobQueryResult, BmobException e) {
                      results_intent = bmobQueryResult.getResults();
                      results =new ArrayList<technology_center>();
                      results.addAll(results_intent);
                      Log.e("长度",""+results.size());

                      //设置列表适配器  准备展示内容
                      lv.setAdapter(myadapte);
                      bga_lv.endRefreshing();
                      if(results.size()==0 ||results==null){
                          state_layout.showEmpty();
                      }else{
                          state_layout.showNormal();
                      }
                  }
              });



    }
    @Override
    public void onResume() {
        super.onResume();
        if(!IntentToogle.isnetWorkAvilable(bga_lv.getContext())){
            intent_info.setVisibility(View.VISIBLE);
            state_layout.showNormal();
        }else{
            intent_info.setVisibility(View.GONE);
        }

    }


    /**
     * 适配器
     */
    class Myadapte extends BaseAdapter{

        private String url_image;
        private ImageView img_down;

        public Myadapte() {
            super();
        }

        @Override
        public int getCount() {
            return results.size();
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

            //初始化控件
            View view_item = View.inflate(getActivity(), R.layout.commition, null);

            //上传人
            TextView manger = (TextView) view_item.findViewById(R.id.manger);
            //内容
            TextView tv_content = (TextView) view_item.findViewById(R.id.tv_content);
            //上传时间
            TextView tv_time = (TextView) view_item.findViewById(R.id.tv_time);

            //修改时间
            TextView tv_time_update = (TextView) view_item.findViewById(R.id.tv_time_update);

            img_down = (ImageView) view_item.findViewById(R.id.img_down);

            //设置数据
            technology_center technology_data = results.get(results.size()-1-i);
            tv_content.setText(technology_data.getContent());
            manger.setText(technology_data.getUser_name());
            url_image = technology_data.getImageid();
            tv_time.setText("上传时间："+technology_data.getCreatedAt());
            tv_time_update.setText("修改时间："+technology_data.getUpdatedAt());
            return view_item;
        }


    }


}
