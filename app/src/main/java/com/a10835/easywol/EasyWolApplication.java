package com.a10835.easywol;

import android.app.Application;
import android.content.Context;

/**
 * Created by 10835 on 2018/4/17.
 */

public class EasyWolApplication extends Application {
    public static EasyWolApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 返回一个全局的Context对象
     * @return
     */
    public static EasyWolApplication getInstance(){
        return instance;
    }

}
