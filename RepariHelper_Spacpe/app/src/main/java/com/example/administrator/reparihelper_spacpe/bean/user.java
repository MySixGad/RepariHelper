package com.example.administrator.reparihelper_spacpe.bean;

import android.widget.ImageView;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/1/16.
 * 用户表bean类 关键字 user_name  icon pws
 */

public class user extends BmobObject{

    private String user_name;
   // private ImageView icon;
    private String pws;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

/*
    public ImageView getIcon() {
        return icon;
    }
*/

/*    public void setIcon(ImageView icon) {
        this.icon = icon;
    }*/

    public String getPws() {
        return pws;
    }

    public void setPws(String pws) {
        this.pws = pws;
    }
}
