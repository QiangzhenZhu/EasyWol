package com.a10835.easywol.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.utils.ActivityCollector;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.fm_tool_bar_icon)
    RelativeLayout fmToolBarIcon;
    @BindView(R.id.tv_user_info_phone)
    TextView tvUserInfoPhone;
    @BindView(R.id.btn_user_info_exit)
    TextView btnUserInfoExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        setWhiteStatusBar();
    }

    @Override
    public void setEevent() {
        fmToolBarIcon.setOnClickListener(this);
        btnUserInfoExit.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        return intent;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_tool_bar_icon:
                finish();
                break;
            case R.id.btn_user_info_exit:
                BmobUser.logOut();
                Intent intent = new Intent(mContext,LoginActivity.class);
                startActivity(intent);
                ActivityCollector.finshAll();
                break;
            default:
                break;
        }
    }
}
