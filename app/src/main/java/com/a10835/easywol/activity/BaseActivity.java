package com.a10835.easywol.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.a10835.easywol.utils.ActivityCollector;
import com.a10835.easywol.utils.LogUtil;

/**
 * Created by 10835 on 2018/5/2.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    private boolean isShowActionBar = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityCollector.getInstance().addActivity(this);
        if (isShowActionBar) {
            hideActionBar();
        }
        LogUtil.setTag(getClass().getSimpleName());
        LogUtil.d("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        setEevent();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
        LogUtil.d("onDestroy");
    }

    /**
     * 隐藏状态栏
     */
    public void hideActionBar(){
        if (getSupportActionBar() != null){
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();
        }
    }


    /**
     * 设置事件跳转
     */
    public abstract void setEevent();

    /**
     *设置View data数据
     */
    public abstract void initData();



}
