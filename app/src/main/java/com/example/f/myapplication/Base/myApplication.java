package com.example.f.myapplication.Base;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Billy_Cui on 2018/5/16
 * Describe:
 */
public class myApplication extends Application {

    private RefWatcher refWatcher;

    private static myApplication sMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        setupLeakCanary();
        sMyApplication = this;
    }

    protected void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        enabledStrictMode();
        refWatcher = LeakCanary.install(this);
    }

    private static void enabledStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                .detectAll() //
                .penaltyLog() //
                .penaltyDeath() //
                .build());
    }

    public static RefWatcher getRefWatcher(Context context) {
        myApplication application = (myApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    public static Context getContext(){
        return sMyApplication;
    }

}
