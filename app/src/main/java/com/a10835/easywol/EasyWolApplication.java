package com.a10835.easywol;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;

/**
 * Created by 10835 on 2018/4/17.
 */

public class EasyWolApplication extends Application {
    public static EasyWolApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化Bmob
        Bmob.initialize(this, "7d99b7d43637f8b1ab42e2899d68aa9b");

        
        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), "bd13608ef8", true);
        //初始化验证工具类
        Utils.init(this);

    }

    /**
     * 返回一个全局的Context对象
     * @return
     */
    public static EasyWolApplication getInstance(){
        return instance;
    }

}
