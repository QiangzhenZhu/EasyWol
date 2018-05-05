package com.a10835.easywol.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.a10835.easywol.R;
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

    /**
     * android6.0以上标题栏显示为白色，状态栏为白底黑色
     */
    public void setWhiteStatusBar(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            ((AppCompatActivity) mContext).getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            Window window = ((AppCompatActivity) mContext).getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
    }



}
