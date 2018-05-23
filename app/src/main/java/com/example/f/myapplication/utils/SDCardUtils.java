package com.example.f.myapplication.utils;

import android.content.Context;
import android.os.Environment;

import com.example.f.myapplication.Base.myApplication;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


/**
 * Created by zhangbosen on 16/7/26.
 */
public class SDCardUtils {

    public static boolean SDCardIsReady() {
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
//            return true;
//        else
//            return false;
        return true;
    }

    public static String getStoragePath(Context context) {

        String cachePath = context.getExternalCacheDir().getAbsolutePath();
//        LogUtils.d("文件路径：" + cachePath);
        return cachePath;
    }

    public static String getFileDir(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }
    static File file;
    public static void save(Object response, long startsPoint, String fileName) {
        String string= null;
        try {
            string = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedInputStream inputStream=new BufferedInputStream(new ByteArrayInputStream(string.getBytes()));
        String path = SDCardUtils.getStoragePath(myApplication.getContext());
        file = new File(path,fileName);


        FileChannel channelOut = null;
        // 随机访问文件，可以指定断点续传的起始位置
        RandomAccessFile randomAccessFile = null;
        try {



            randomAccessFile = new RandomAccessFile(file, "rwd");
            //Chanel NIO中的用法，由于RandomAccessFile没有使用缓存策略，直接使用会使得下载速度变慢，亲测缓存下载3.3秒的文件，用普通的RandomAccessFile需要20多秒。
            channelOut = randomAccessFile.getChannel();
            // 内存映射，直接使用RandomAccessFile，是用其seek方法指定下载的起始位置，使用缓存下载，在这里指定下载位置。
            MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE, startsPoint, string.length());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                mappedBuffer.put(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                if (channelOut != null) {
                    channelOut.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        if (tempList == null) {
            return flag;
        }
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 删除文件夹
     *
     * @param folderPath 文件夹完整绝对路径
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String readString(String path) {
        String result = "";
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
            String s = "";
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                result = result + s + "\n";
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @return
     * @see {@linkplain #getMyCacheDir(String)}
     */
    public static String getMyCacheDir() {
        return getMyCacheDir(null);
    }

    /**
     * 获取或创建Cache目录
     *
     * @param bucket 临时文件目录，bucket = "/cache/" ，则放在"sdcard/linked-joyrun/cache"; 如果bucket=""或null,则放在"sdcard/linked-joyrun/"
     */
    public static String getMyCacheDir(String bucket) {
        String dir;

        // 保证目录名称正确
        if (bucket != null) {
            if (!bucket.equals("")) {
                if (!bucket.endsWith("/")) {
                    bucket = bucket + "/";
                }
            }
        } else
            bucket = "";

        String joyrun_default = "/html/";

        if (isSDCardExist()) {
            dir = Environment.getExternalStorageDirectory().toString() + joyrun_default + bucket;
        } else {
            dir = Environment.getDownloadCacheDirectory().toString() + joyrun_default + bucket;
        }

        File f = new File(dir);
        if (!f.exists()) {
            f.mkdirs();
        }
        return dir;
    }
}

