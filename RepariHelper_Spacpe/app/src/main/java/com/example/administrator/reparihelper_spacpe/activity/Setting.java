package com.example.administrator.reparihelper_spacpe.activity;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.reparihelper_spacpe.R;
import com.example.administrator.reparihelper_spacpe.utils.Contacts;
import com.example.administrator.reparihelper_spacpe.utils.SharedPreferenceUtils;

public class Setting extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle("系统设置");
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        Button destroy = (Button) findViewById(R.id.destroy);

             destroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferenceUtils.savaBoolean(Setting.this,Contacts.LANDED,false);
                finish();
                Toast.makeText(Setting.this, "注销成功", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
