package com.example.administrator.reparihelper_spacpe.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/1/17.
 */

public class SharedPreferenceUtils {


    //保存布尔数据
    public static void savaBoolean(Context context, String defaultkey, Boolean values){
        SharedPreferences sp = context.getSharedPreferences("config",0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(defaultkey,values);
        edit.commit();
    }

    //获取布尔值
    public static boolean getBoolean(Context context, String defaultkey, Boolean values){
        SharedPreferences sp = context.getSharedPreferences("config",0);
        boolean val = sp.getBoolean(defaultkey, values);
        return val;
    }

    //保存字符串
    public static  void savaString(Context context, String defaultkey, String values){
        SharedPreferences sp = context.getSharedPreferences("config",0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(defaultkey,values);
        edit.commit();
    }


    //获取字符串
    public static String getString(Context context, String defaultkey, String values){
        SharedPreferences sp = context.getSharedPreferences("config",0);
        String val = sp.getString(defaultkey, values);
        return val;
    }


}
