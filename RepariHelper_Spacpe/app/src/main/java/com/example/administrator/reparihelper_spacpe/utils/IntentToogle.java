package com.example.administrator.reparihelper_spacpe.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Administrator on 2017/1/17.
 */

public class IntentToogle {

    /**
     * 判断网络是否可用
     * @param context
     * @return
     */
    public static boolean isnetWorkAvilable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager == null) {
            Log.e("FlyleafActivity", "couldn't get connectivity manager");
        } else {
            NetworkInfo [] networkInfos = connectivityManager.getAllNetworkInfo();
            if(networkInfos != null){
                for (int i = 0, count = networkInfos.length; i < count; i++) {
                    if(networkInfos[i].getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
