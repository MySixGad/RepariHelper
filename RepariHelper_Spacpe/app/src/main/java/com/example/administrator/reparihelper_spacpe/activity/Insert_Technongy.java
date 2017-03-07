package com.example.administrator.reparihelper_spacpe.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.reparihelper_spacpe.R;
import com.example.administrator.reparihelper_spacpe.bean.technology_center;
import com.example.administrator.reparihelper_spacpe.bean.user;
import com.example.administrator.reparihelper_spacpe.utils.Contacts;
import com.example.administrator.reparihelper_spacpe.utils.SharedPreferenceUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Insert_Technongy extends AppCompatActivity {

    private EditText text_put;
    private Button btn_comit;
    private technology_center technology_data;
    private ProgressBar pro_up_technongry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__technongy);

        text_put = (EditText) findViewById(R.id.text_put);
        btn_comit = (Button) findViewById(R.id.btn_comit);
        pro_up_technongry = (ProgressBar) findViewById(R.id.pro_up_technongry);

        technology_data = new technology_center();



        btn_comit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pro_up_technongry.setVisibility(View.VISIBLE);
                String content = text_put.getText().toString();
                String user_name = SharedPreferenceUtils.getString(Insert_Technongy.this, Contacts.LANDED_USERNAME, "");

                technology_data.setContent(content);
                technology_data.setUser_name(user_name);

                technology_data.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        pro_up_technongry.setVisibility(View.GONE);
                        Toast.makeText(Insert_Technongy.this, "上传成功！", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
