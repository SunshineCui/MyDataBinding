package com.example.f.myapplication.view;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.f.myapplication.R;
import com.example.f.myapplication.utils.AppUtil;
import com.example.f.myapplication.utils.DownLoadUtils;
import com.example.f.myapplication.utils.SDCardUtils;


/**
 * 1.网络获取最新版本号 比较是否下载
 * 2.多线程下载
 * 3.进度显示
 * 4.安装apk
 * 参考:https://blog.csdn.net/imshuyuan/article/details/62886741(安装权限问题,7.0权限)
 * Created by Billy_Cui on 2018/5/22
 * Describe:  测试 新版本 下载安装
 */
public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DownloadActivity";

    //    private String loadUrl = "http://gdown.baidu.com/data/wisegame/d2fbbc8e64990454/wangyiyunyinle_87.apk";
    private String filePath = Environment.getExternalStorageDirectory() + "/" + "111.apk";

    public Button mB1;
    public Button mB2;
    public Button mB3;
    public TextView mTextView;
    private DownLoadUtils mLoadUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_download);
        mTextView = findViewById(R.id.textView);
//        mB1 = findViewById(R.id.b1);
//        mB2 = findViewById(R.id.b2);
//        mB3
        //应该网络获取
        int newVersionCode = 26;
        int versionCode = AppUtil.getVersionCode();
//        String loadUrl = "http://openbox.mobilem.360.cn/index/d/sid/3429345";//安装包下载地址 11.5M
//        String loadUrl = "http://imtt.dd.qq.com/16891/47D8294B06AD9F31AC31BBC5574A1897.apk?fsname=com.syezon.wifi_3.6.5_248.apk&csr=1bbd";//6.6M
        String loadUrl = "http://imtt.dd.qq.com/16891/7AB0BD682763263FE543D4CCE6A66433.apk?fsname=com.qq.reader_6.6.2.689_110.apk&csr=1bbd"; //22M
        Log.d(TAG, "onCreate ");
        if (newVersionCode > versionCode && SDCardUtils.isSDCardExist()) {
            //需要更新
            mLoadUtils = new DownLoadUtils.Builder().mContext(this).loadUrl(loadUrl).filePath(filePath).mDownLoadListener(new DownLoadUtils.DownLoadListener() {
                @Override
                public void getProgress(int progress) {
                    Log.d(TAG, "progress :" + progress);
                    mTextView.setText("progress : " + progress);
                }

                @Override
                public void onComplete() {
                    Log.d(TAG, "onComplete !");
                }

                @Override
                public void onFailure(String message) {
                    Log.d(TAG, "onFailure !" + message);
                }
            }).build();
        }
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.b1) {
            //开始
            mLoadUtils.download();
            Log.d(TAG, "download !");
        } else if (v.getId() == R.id.b2) {
            //暂停
            mLoadUtils.onPause();
            Log.d(TAG, "onPause !");
        } else if (v.getId() == R.id.b3) {
            //重新开始
            mLoadUtils.onStart();
            Log.d(TAG, "onStart !");
        }
    }

    @Override
    protected void onDestroy() {
        mLoadUtils.onCancel();
        mLoadUtils = null;
        super.onDestroy();
    }
}
