package com.a10835.easywol.utils;

import android.os.Debug;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.IllegalFormatCodePointException;

/**
 * Created by 10835 on 2018/5/2.
 * 通过DEBUG来控制日志的打印
 */

public class LogUtil {
    public static String TAG ="EasyWol";
    public static final Boolean DEBUG = true;


    /**
     * Log.v();
     * @param text
     */
    public static void v(String text){
        if (DEBUG){
            Log.v(TAG,text);
        }
    }

    /**
     * log.d();
     * @param text
     */
    public static void d(String text){
        if (DEBUG){
            Log.d(TAG,text);
        }
    }


    /**
     * Log.i();
     * @param text
     */
    public static void i(String text){
        if (DEBUG){
            Log.i(TAG,text);
        }
    }


    /**
     * log.w();
     * @param text
     */
    public static void w(String text){
        if (DEBUG){
            Log.w(TAG,text);
        }
    }

    /**
     * log.e
     * @param text
     */
    public static void e(String text){
        if (DEBUG){
            Log.e(TAG,text);
        }
    }
    public static void setTag(String text){
        if (!TextUtils.isEmpty(text)){
            TAG = text;
        }
    }









}
