package com.a10835.easywol.view;

import android.content.Context;
import android.icu.text.UnicodeSetSpanner;
import android.widget.Toast;

/**
 * Created by 10835 on 2018/5/3.
 */

public class ToastUtil {
    public static Toast toast;
    public static void show(Context context,String text){
        if (toast == null){
            showText(context,text,Toast.LENGTH_SHORT);
        }else {
            toast.cancel();
            showText(context,text,Toast.LENGTH_SHORT);
        }

    }
    public static void showLong(Context context,String text){

        if (toast == null){
            showText(context,text,Toast.LENGTH_LONG);
        }else {
            toast.cancel();
            showText(context,text,Toast.LENGTH_LONG);
        }
    }

    public static void showText(Context context,String text,int type){
        toast = Toast.makeText(context,text,type);
        toast.show();
    }


}
