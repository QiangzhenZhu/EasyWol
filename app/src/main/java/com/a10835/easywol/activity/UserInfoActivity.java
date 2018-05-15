package com.a10835.easywol.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.a10835.easywol.R;

public class UserInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }

    @Override
    public void setEevent() {

    }

    @Override
    public void initData() {

    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,UserInfoActivity.class);
        return intent;
    }
}
