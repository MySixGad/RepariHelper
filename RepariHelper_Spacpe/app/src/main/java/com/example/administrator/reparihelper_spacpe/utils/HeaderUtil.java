package com.example.administrator.reparihelper_spacpe.utils;

import android.content.Context;

import com.example.administrator.reparihelper_spacpe.R;

import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGAWaveStyleRefreshViewHolder;

/**
 * Created by Administrator on 2017-1-12.
 */

public class HeaderUtil {
    //获取的是旋转箭头的特效
    public static BGARefreshViewHolder getNormalHeader(Context context) {
        BGARefreshViewHolder headview=new BGANormalRefreshViewHolder(context,true);//1.上下文 2.是否支持加载更多
        return headview;
    }
    //获取的是粘性效果
    public static BGARefreshViewHolder getStickyHeader(Context context) {
        BGAStickinessRefreshViewHolder header=new BGAStickinessRefreshViewHolder(context,true);//1.上下文 2.是否支持加载更多
        //设置旋转图片
        header.setRotateImage(R.mipmap.bga_refresh_stickiness);
        //设置粘性颜色
        header.setStickinessColor(R.color.colorAccent);
        return header;
    }
    //获取的是水波效果
    public static BGARefreshViewHolder getWaveHeader(Context context) {
        BGAWaveStyleRefreshViewHolder header=new BGAWaveStyleRefreshViewHolder(context,true);//1.上下文 2.是否支持加载更多
        //水波颜色
        header.setUltimateColor(R.color.colorAccent);
        //图片
        header.setOriginalImage(R.mipmap.bga_refresh_moooc);
        return header;
    }

    //获取美团效果
    public static BGARefreshViewHolder getMeituanHeader(Context context) {
        BGAMeiTuanRefreshViewHolder header=new BGAMeiTuanRefreshViewHolder(context,true);//1.上下文 2.是否支持加载更多
        //设置小幅度拉动图片
        header.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        //设置 下拉动画
        header.setChangeToReleaseRefreshAnimResId(R.drawable.pulling);
        //设置 刷新动画
        header.setRefreshingAnimResId(R.drawable.refreshing);
        return header;
    }
}
