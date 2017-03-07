package com.example.administrator.reparihelper_spacpe.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.reparihelper_spacpe.R;
import com.example.administrator.reparihelper_spacpe.bean.technology_center;
import com.example.administrator.reparihelper_spacpe.utils.Contacts;
import com.example.administrator.reparihelper_spacpe.utils.SharedPreferenceUtils;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class Up_Technongy extends AppCompatActivity {

    private EditText text_put;
    private Button btn_comit;
    private technology_center technology_data;
    private LinearLayout pro_up_technongry;
    private int errorCode;
    private BmobFile bomfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__technongy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("帖子上传");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);


        text_put = (EditText) findViewById(R.id.text_put);
        btn_comit = (Button) findViewById(R.id.btn_comit);
        pro_up_technongry = (LinearLayout) findViewById(R.id.pro_up_technongry);
        technology_data = new technology_center();


        //提交
    btn_comit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pro_up_technongry.setVisibility(View.VISIBLE);
            // UpImage();
            Updata("假地址");
        }
    });

}


    /**
     *  //数据上传
     * @param url
     */

    public  void Updata(String url){
        String content = text_put.getText().toString();
        String user_name = SharedPreferenceUtils.getString(Up_Technongy.this, Contacts.LANDED_USERNAME, "");
        technology_data.setContent(content);
        technology_data.setUser_name(user_name);
        technology_data.setImageid(url);
        technology_data.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                pro_up_technongry.setVisibility(View.GONE);
                errorCode = e.getErrorCode();
                if (errorCode == 9015) {
                    Toast.makeText(Up_Technongy.this, "上传成功！", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (errorCode == 9016) {
                    Toast.makeText(Up_Technongy.this, "上传失败，请检查网络", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Up_Technongy.this, "上传失败", Toast.LENGTH_SHORT).show();
                }
                Log.e("错误码：", "" + errorCode);
            }

        });

    }






    /**
     *    //图片的上传  由于时间问题 没有实现
     */
    public void UpImage(){
        File absoluteFile = Environment.getExternalStorageDirectory().getAbsoluteFile();
        File file = new File(absoluteFile, "/aa.png");
        bomfile = new BmobFile(file);
        bomfile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    bomfile.getFileUrl(); //返回的上传文件的完整地址
                    Toast.makeText(Up_Technongy.this, "上传文件成功:" + bomfile.getFileUrl(),
                            Toast.LENGTH_SHORT).show();
                    BmobObject object=new BmobObject();
                    object.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Log.d("bmob", "成功");
                                String url = bomfile.getUrl();
                                Updata(url);
                                Log.e("地址",""+url);
                            } else {
                                Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                            }
                        }
                    });

                } else {
                    Log.e("错误", e.getMessage());
                }
            }

            //上传进度
            @Override
            public void onProgress(Integer value) {
                super.onProgress(value);
            }

        });
    }
}
