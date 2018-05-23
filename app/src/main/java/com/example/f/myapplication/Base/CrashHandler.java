//package com.example.f.myapplication.Base;
//
//import android.content.Context;
//import android.os.Looper;
//import android.os.SystemClock;
//import android.widget.Toast;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.io.Writer;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Billy_Cui on 2018/5/21
// * Describe:异常处理类
// * https://blog.csdn.net/u012333411/article/details/49019415
// */
//public class CrashHandler implements Thread.UncaughtExceptionHandler {
//    public static final String TAG = "CrashHandler";
//    //唯一实例
//    private static CrashHandler INSTANCE = new CrashHandler();
//    private Context mContext;
//    //用于存储设备信息和异常信息
//    private Map<String, String> infos = new HashMap<String, String>();
//    //系统默认的UncaughtException处理类
//    private Thread.UncaughtExceptionHandler mDefaultHandler;
//    //用于格式化日期,作为日志文件名的一部分
//    private DateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//    private String nameString;
//
//    //构造器 私有化 保证一个实例
//    private CrashHandler() {
//    }
//
//    //获取实例
//    public static CrashHandler getInstance() {
//        return INSTANCE;
//    }
//
//    /**
//     * 初始化
//     *
//     * @param context
//     */
//    public void init(Context context) {
//        mContext = context;
//        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
//        Thread.setDefaultUncaughtExceptionHandler(this);
//        autoClear(5);
//    }
//
//    /**
//     * 当UNcaughtException发生时会转入该函数来处理
//     */
//    @Override
//    public void uncaughtException(Thread t, Throwable e) {
//        if (!handlerException(e) && mDefaultHandler !=null){
//
//        }
//    }
//
//    /**
//     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成
//     * @param e
//     * @return true:处理了该异常信息; 否则返回false.
//     */
//    private boolean handlerException(Throwable e) {
//        if (e == null){
//            return false;
//        }
//        new Thread(){
//            @Override
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(mContext,"很抱歉,程序出现异常,即将重启.",Toast.LENGTH_LONG).show();;
//                Looper.loop();
//                super.run();
//            }
//        }.start();
//        //收集设备参数信息
//        collectDeviceInfo(mContext);
//        //保存日志文件
//        saveCrashInfoFile(e);
//        SystemClock.sleep(3000);
//        return false;
//    }
//
//    /**
//     * 保存错误信息到文件中
//     * @param e
//     * @return 返回文件名称,便于将文件传送到服务器
//     */
//    private String saveCrashInfoFile(Throwable e) {
//        StringBuffer sb = new StringBuffer();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = simpleDateFormat.format(new java.util.Date());
//        sb.append("\r\n"+date+"\n");
//        for (Map.Entry<String,String> entry : infos.entrySet()){
//            String key = entry.getKey();
//            String value = entry.getValue();
//            sb.append(key + "=" + value + "\n");
//        }
//        Writer writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//        e.printStackTrace(printWriter);
//        Throwable cause = e.getCause();
//        while(cause != null){
//            cause.printStackTrace(printWriter);
//            cause = cause.getCause();
//        }
//        printWriter.flush();
//        printWriter.close();
//        String result = writer.toString();
//        sb.append(result);
//
//        String fileName = writerFile(sb.toString());
//
//        return fileName;
//    }
//
//    /**
//     * 保存文件
//     * @param s
//     * @return  文件名
//     */
//    private String writerFile(String s) {
//
//        return null;
//    }
//
//    /**
//     * 收集设备参数信息
//     * @param context
//     */
//    private void collectDeviceInfo(Context context) {
//
//    }
//
//
//    public void autoClear(final int autoClearDay){
//
//    }
//}
