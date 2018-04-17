package com.a10835.easywol.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.a10835.easywol.R;

public class SplashActivity extends AppCompatActivity {
    private Context mContext;
    private ImageView mAnimateImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
        final AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_splash);
        mAnimateImage = findViewById(R.id.iv_splash);
        mAnimateImage.setImageDrawable(animatedVectorDrawable);
        animatedVectorDrawable.start();

        //延迟1.5s启动跳转新活动
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);



    }
}
