package com.example.f.myapplication.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.f.myapplication.Base.myApplication;

import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * 关于软件的工具类
 * Created by superstar on 16/11/19.
 */

public class AppUtil {

    /**
     * 获取顶层Activity的包名
     *
     * @return
     */
    public static String getTopActivityName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);

        ComponentName cn = activityManager.getRunningTasks(Integer.MAX_VALUE).get(0).topActivity;

        return cn.getClassName();
    }

    /**
     * 获取软件版本号
     *
     * @return
     */
    public static int getVersionCode() {
        Context context = myApplication.getContext();
        PackageManager manager = context.getPackageManager();//获取包管理器
        try {
            //通过当前的包名获取包的信息
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);//获取包对象信息
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 判断当前应用程序处于前台还是后台
     */
    public static boolean isApplicationBroughtToBackground() {
        ActivityManager am = (ActivityManager) myApplication.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(myApplication.getContext().getPackageName())) {
                return true;
            }
        }
        return false;
    }
    public static Drawable getDrawable(int imageId){
        return myApplication.getContext().getResources().getDrawable(imageId);
    }

    public static int getColor(int resId) {
        return myApplication.getContext().getResources().getColor(resId);
    }
}
