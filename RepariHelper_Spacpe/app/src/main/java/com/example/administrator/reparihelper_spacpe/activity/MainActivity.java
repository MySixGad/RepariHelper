package com.example.administrator.reparihelper_spacpe.activity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.reparihelper_spacpe.R;
import com.example.administrator.reparihelper_spacpe.bean.user;
import com.example.administrator.reparihelper_spacpe.fragement.NewsFragment;
import com.example.administrator.reparihelper_spacpe.fragement.RepairFragment;
import com.example.administrator.reparihelper_spacpe.fragement.TechologyFragment;
import com.example.administrator.reparihelper_spacpe.utils.Contacts;
import com.example.administrator.reparihelper_spacpe.utils.IntentToogle;
import com.example.administrator.reparihelper_spacpe.utils.SharedPreferenceUtils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.BmobUpdateListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.bmob.v3.update.UpdateResponse;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private RadioButton comm_radio;
    private RadioButton news_radio;
    private RadioButton video_radio;
    private FragmentTransaction transaction;
    private View view;
    private FragmentManager fragmentManager;
    private GoogleApiClient client;
    private user info_bean;
    private TextView name_leftmenu;
    private ActionBarDrawerToggle toggle;
    private AlertDialog.Builder builder;
    private Dialog dialog;
    private EditText et_name;
    private EditText et_pwd;
    private TextView des_leftmenu;
    private com.example.administrator.reparihelper_spacpe.bean.user user;
    private LinearLayout pro_dialog;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("技术中心");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();




        //初始化服务器链接
        initServer();

        //加载控件
        initUIView();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //版本检查更新  延迟三秒 加载更新
                checkVersion();
            }
        }, 2000);

    }


    /**
     * 版本检查更新
     */
    private void checkVersion() {


            //初始化
            BmobUpdateAgent.initAppVersion();

            //检查更新
            BmobUpdateAgent.update(MainActivity.this);

            //流量也更新
            BmobUpdateAgent.setUpdateOnlyWifi(false);



            //是否成功
            BmobUpdateAgent.setUpdateListener(new BmobUpdateListener() {

                @Override
                public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                   /* // TODO Auto-generated method stub
                    //根据updateStatus来判断更新是否成功
                    // TODO Auto-generated method stub
                    if (updateStatus == UpdateStatus.Yes) {//版本有更新

                        Log.e("版本有更新","版本有更新");

                    }else if(updateStatus == UpdateStatus.No){
                        Log.e("版本无更新","版本无更新");
                    }else if(updateStatus==UpdateStatus.EmptyField){//此提示只是提醒开发者关注那些必填项，测试成功后，无需对用户提示
                        // Toast.makeText(MainActivity.this, "请检查你AppVersion表的必填项，1、target_size（文件大小）是否填写；2、path或者android_url两者必填其中一项。", Toast.LENGTH_SHORT).show();
                    }else if(updateStatus==UpdateStatus.IGNORED){
                        Log.e("该版本已被忽略更新","该版本已被忽略更新");
                    }else if(updateStatus==UpdateStatus.ErrorSizeFormat){
                        //Toast.makeText(MainActivity.this, "请检查target_size填写的格式，请使用file.length()方法获取apk大小。", Toast.LENGTH_SHORT).show();
                    }else if(updateStatus==UpdateStatus.TimeOut){

                        Log.e("版本检查出错","出错了");
                    }*/
                }
            });




    }


    /**
     * 初始化服务器链接
     */
    private void initServer() {
        try{
            Bmob.initialize(MainActivity.this, "5fe50f9449979a8b6bca18f01019eeef");
        }catch (Exception e){
           Log.e("初始化服务器链接出错","对");
        }

    }


    private void initUIView() {
        comm_radio = (RadioButton) findViewById(R.id.comm_radio);
        news_radio = (RadioButton) findViewById(R.id.news_radio);
        video_radio = (RadioButton) findViewById(R.id.repair_radio);

        //获取左侧菜单布局id
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        View headerView = nav_view.getHeaderView(0);

        //头像
        ImageView icon_leftmenu = (ImageView) headerView.findViewById(R.id.icon_leftmenu);
        //名字
        name_leftmenu = (TextView) headerView.findViewById(R.id.name_leftmenu);
        //描述
        des_leftmenu = (TextView) headerView.findViewById(R.id.des_leftmenu);

        icon_leftmenu.setOnClickListener(this);
        comm_radio.setOnClickListener(this);
        news_radio.setOnClickListener(this);
        video_radio.setOnClickListener(this);
        comm_radio.callOnClick();
        comm_radio.setChecked(true);

        user = new user();
    }

    @Override
    protected void onResume() {

        super.onResume();
        boolean val = SharedPreferenceUtils.getBoolean(MainActivity.this, Contacts.LANDED, false);
        String val_name = SharedPreferenceUtils.getString(MainActivity.this, Contacts.LANDED_USERNAME, "");
        if (val) {
            //名字回显
            name_leftmenu.setText(val_name);
            des_leftmenu.setText("您好，尊敬的管理员！");
        } else {
            name_leftmenu.setText("管理员未登陆");
            des_leftmenu.setText(" 您可以在该入口发布最新资讯");
        }
    }

    public void onClick(View wv) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (wv.getId()) {
            case R.id.comm_radio:
                transaction.replace(R.id.f_content, new TechologyFragment());
                transaction.commit();
                toolbar.setTitle("技术中心");
                break;

            case R.id.news_radio:
                transaction.replace(R.id.f_content, new NewsFragment());
                transaction.commit();
                toolbar.setTitle("汽车资讯");
                break;

            case R.id.repair_radio:
                transaction.replace(R.id.f_content, new RepairFragment());
                transaction.commit();
                toolbar.setTitle("维修大道");

                break;


            case R.id.icon_leftmenu:
                showLandDialog();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                break;
        }
    }

    //登陆逻辑
    private void showLandDialog() {

        boolean val = SharedPreferenceUtils.getBoolean(MainActivity.this, Contacts.LANDED, false);
        if (val) {
            //不再弹出提示框
            return;
        }
        builder = new AlertDialog.Builder(this);
        View land_dialog = View.inflate(MainActivity.this, R.layout.land_dialog_item, null);
        builder.setView(land_dialog);
        et_name = (EditText) land_dialog.findViewById(R.id.et_name);
        et_pwd = (EditText) land_dialog.findViewById(R.id.et_pwd);
        Button btn_land = (Button) land_dialog.findViewById(R.id.btn_land);
        Button btn_back = (Button) land_dialog.findViewById(R.id.btn_back);
        Button btn_register = (Button) land_dialog.findViewById(R.id.btn_register);
        //进度
        pro_dialog = (LinearLayout) land_dialog.findViewById(R.id.pro_dialog);

        dialog = builder.show();

        //登陆
        btn_land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean avilable = IntentToogle.isnetWorkAvilable(MainActivity.this);
                if (!avilable) {
                    Toast.makeText(MainActivity.this, "网络不可用", Toast.LENGTH_SHORT).show();
                    return;
                }
                pro_dialog.setVisibility(View.VISIBLE);
                BmobQuery<user> bmobQuery = new BmobQuery<user>();
                bmobQuery.getObject("9b65436e3c", new QueryListener<user>() {

                    @Override
                    public void done(user user, BmobException e) {
                        //获取网络数据
                        String name_bmob = user.getUser_name();
                        String pwd_bmob = user.getPws();

                        //获取输入数据
                        String name_keyboard = et_name.getText().toString();
                        String pwd_keyboard = et_pwd.getText().toString();

                        if (TextUtils.equals(name_keyboard, name_bmob) && TextUtils.equals(pwd_keyboard, pwd_bmob)) {
                            pro_dialog.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();

                            dialog.cancel();
                            //回显
                            name_leftmenu.setText(name_keyboard);
                            des_leftmenu.setText("您好，尊敬的管理员！");
                            //保存
                            SharedPreferenceUtils.savaBoolean(MainActivity.this, Contacts.LANDED, true);
                            SharedPreferenceUtils.savaString(MainActivity.this, Contacts.LANDED_USERNAME, name_bmob);
                        } else {
                            pro_dialog.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "登陆失败，密码或账号错误", Toast.LENGTH_SHORT).show();
                            et_name.setText("");
                            et_pwd.setText("");
                        }

                    }


                });

            }
        });


        //返回登陆
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


        //注册
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    class Butterknife{
        @BindView(R.id.toolbar)
        Toolbar toolbar;
        @BindView(R.id.f_content)
        LinearLayout fContent;
        @BindView(R.id.comm_radio)
        RadioButton commRadio;
        @BindView(R.id.repair_radio)
        RadioButton videoRadio;
        @BindView(R.id.news_radio)
        RadioButton newsRadio;
        @BindView(R.id.rg)
        RadioGroup rg;
        @BindView(R.id.content_main)
        RelativeLayout contentMain;
        @BindView(R.id.nav_view)
        NavigationView navView;
        @BindView(R.id.drawer_layout)
        DrawerLayout drawerLayout;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean landed = SharedPreferenceUtils.getBoolean(MainActivity.this, Contacts.LANDED, false);

        if (id == R.id.nav_camera) {     //技术贴
            if(!landed){
                Toast.makeText(MainActivity.this, "请先登陆管理员账号", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(MainActivity.this, Up_Technongy.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_gallery) { //资讯

            if(!landed){
                Toast.makeText(MainActivity.this, "请先登陆管理员账号", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(MainActivity.this, Up_Technongy.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_slideshow) { //维修

            if(!landed){
                Toast.makeText(MainActivity.this, "请先登陆管理员账号", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(MainActivity.this, Up_Technongy.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_manage) {
            Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Setting.class);
            startActivity(intent);


        } else if (id == R.id.nav_share) {  //资料

            if(!landed){
                Toast.makeText(MainActivity.this, "请先登陆管理员账号", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(MainActivity.this, Up_Technongy.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_send) {  //管理

            if(!landed){
                Toast.makeText(MainActivity.this, "请先登陆管理员账号", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(MainActivity.this, Up_Technongy.class);
                startActivity(intent);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
