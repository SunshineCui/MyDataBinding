//package com.example.f.myapplication.utils;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.view.LayoutInflater;
//import android.widget.ProgressBar;
//
//import com.example.f.myapplication.Base.CommonProgressDialog;
//import com.example.f.myapplication.R;
//
///**
// * Created by Billy_Cui on 2018/5/22
// * Describe:
// */
//public  class DialogUtils {
//
//    public static void  showDialog(Context context,String newVewsion,String url ,String content){
//        new android.app.AlertDialog.Builder(context)
//                .setTitle("版本更新")
//                .setMessage(content)
//                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).show();
//    }
//
//    public static void progressDialog(Context context,String newVewsion,String url ,String content){
//        CommonProgressDialog cpd = new CommonProgressDialog(context);
//        cpd.setCancelable(false);
//        cpd.setTitle("正在下载");
//        cpd.setCustomTitle(LayoutInflater.from(context).inflate(R.layout.title_dialog,null));
//        cpd.setMessage("正在下载");
//        cpd.setCancelable(true);
//        cpd.setIndeterminate(true);
//        cpd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//
//    }
//}
