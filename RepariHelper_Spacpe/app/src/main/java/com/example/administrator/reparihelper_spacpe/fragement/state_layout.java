package com.example.administrator.reparihelper_spacpe.fragement;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.reparihelper_spacpe.R;

/**
 * Created by Administrator on 2017/1/17.
 */

public  class state_layout extends FrameLayout{


    private static View view;
    private static LinearLayout load_state;
    private static LinearLayout empty_state;
    private static View state_normal;

    public state_layout(Context context) {
        this(context,null,0);
    }

    public state_layout(Context context, AttributeSet attrs) {
        this(context,null,0);
    }

    public state_layout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        view = View.inflate(context, R.layout.state_layout,null);
        this.addView(view);
        load_state = (LinearLayout) view.findViewById(R.id.load_state);
        empty_state = (LinearLayout) view.findViewById(R.id.empty_state);
        state_normal = (LinearLayout) view.findViewById(R.id.state_normal);
    }


         public static void resetAll(){
             load_state.setVisibility(View.GONE);
             empty_state.setVisibility(View.GONE);
             state_normal.setVisibility(View.GONE);
         }

    public static void showLoad(){
        resetAll();
        load_state.setVisibility(View.VISIBLE);
    }


    public static void showEmpty(){
        resetAll();
        empty_state.setVisibility(View.VISIBLE);
    }

    public static void showNormal(){
        resetAll();
        state_normal.setVisibility(View.VISIBLE);
    }


    //扩充normal 根据页面实际内容再设置
    public void addNormalView(View newNormalView)
    {
        //"删除"
         this.state_normal.setVisibility(View.GONE);
        //保存
        this.state_normal=newNormalView;
        //添加当前容器

        this.addView(newNormalView);
        //添加新视图
    }



}
