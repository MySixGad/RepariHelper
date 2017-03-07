package com.example.administrator.reparihelper_spacpe.fragement;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.administrator.reparihelper_spacpe.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/8.
 */

public class NewsFragment extends Fragment {
    private ViewPager viewpager,viewpager1;
    private int[] ImageId = new int[]{R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};
    private ArrayList<ImageView> img_list = new ArrayList<ImageView>();
    private String[] TITLE=new String[]{"奥迪","宝马","大众","Jeep","通用","悍马"};
    private Handler handler;
    private int i=0;
    private ListView lv_zixun;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_frament, null);

        viewpager = (ViewPager)view. findViewById(R.id.viewpager);
        viewpager1 = (ViewPager)view. findViewById(R.id.viewpager1);
        lv_zixun = (ListView)view. findViewById(R.id.lv_zixun);


        initView(container);

        MyAdapter1 myadapter1 =new MyAdapter1();
        viewpager1.setAdapter(myadapter1);

        PagerSlidingTabStrip tab= (PagerSlidingTabStrip)view.findViewById(R.id.tabstrip);
        tab.setViewPager(viewpager1);

        lv_zixun.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
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
                View item = View.inflate(container.getContext(), R.layout.listview_item, null);
                return item;
            }
        });


        return view;
    }


    private void initView(final ViewGroup container) {


        for (int i = 0; i < ImageId.length; i++) {
            createImage(i,container);
        }

        viewpager.setAdapter(new MyAdapter());

        //轮播计时器
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                int currentItem = viewpager.getCurrentItem();
                if (currentItem < img_list.size() - 1) {
                    currentItem = currentItem + 1;
                } else {
                    currentItem = 0;
                }
                viewpager.setCurrentItem(currentItem);
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        };
        handler.sendEmptyMessageDelayed(0,3000);





    }


    /**
     * 创建图片
     */
    private void createImage(int i,final ViewGroup container) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setBackgroundResource(ImageId[i]);
        img_list.add(imageView);
    }


    /**
     * 下适配器
     */

    private class MyAdapter1 extends PagerAdapter{


        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position];
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            FrameLayout frameLayout = new FrameLayout(container.getContext());
            TextView textView = new TextView(container.getContext());
            textView.setText("测试数据"+i++);
            frameLayout.addView(textView);
            container.addView(frameLayout);
            return frameLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

    }



    /**
     * 上pager适配器
     */

    private class MyAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return ImageId.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView view = img_list.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

    }

}
