package com.example.f.myapplication.utils;

/**
 * created by Billy_Cui on 2018/5/16
 *  时间工具类
 */
public class TimeUtil {

    /**
     * 根据毫秒返回分秒
     * @param time
     * @return
     */
    public static String getFormatMS(long time){

        time=time/10;
        int ms  = (int) (time%100);
        time=time/100;//总秒数
        int s= (int) (time%60);//秒
        int m= (int) (time/60);//分
//        int h=(int) (time/3600);//时
        return String.format("%02d:%02d.%02d",m,s,ms);
    }


    /**
     * 根据毫秒返回时分秒
     * @param time
     * @return
     */
    public static String getFormatHMS(long time){
        time=time/1000;//总秒数
        int s= (int) (time%60);//秒
        int m= (int) (time/60);//分
        int h=(int) (time/3600);//时
        return String.format("%02d:%02d:%02d",h,m,s);
    }
}
