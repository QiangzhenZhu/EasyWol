package com.a10835.easywol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a10835.easywol.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_title_tool_bar)
    TextView tvTitleToolBar;
    @BindView(R.id.fm_tool_bar_icon)
    RelativeLayout fmToolBarIcon;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.et_fead_back_content)
    EditText etFeadBackContent;
    @BindView(R.id.et_feed_back_email)
    EditText etFeedBackEmail;
    @BindView(R.id.btn_feed_back_commit)
    TextView btnFeedBackCommit;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
        setEevent();
    }

    @Override
    public void setEevent() {
        fmToolBarIcon.setOnClickListener(this);
        etFeadBackContent.setOnClickListener(this);
        etFeedBackEmail.setOnClickListener(this);
        btnFeedBackCommit.setOnClickListener(this);
    }

    @Override
    public void initData() {
        tvTitleToolBar.setText("问题反馈");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_tool_bar_icon:

                break;
            case R.id.btn_feed_back_commit:

                break;


        }
    }
}
