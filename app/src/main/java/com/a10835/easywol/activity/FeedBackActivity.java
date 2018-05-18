package com.a10835.easywol.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.bean.FeedBack;
import com.a10835.easywol.view.ProgressDialog;
import com.a10835.easywol.view.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

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
    private ProgressDialog dialog;

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
        setWhiteStatusBar();
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
                finish();
                break;
            case R.id.btn_feed_back_commit:
                dialog = new ProgressDialog();
                dialog.show(getSupportFragmentManager(),"FEEDBACK");
                dialog.setCancelable(false);
                saveFeedBackInfo(etFeedBackEmail.getText().toString().trim(),etFeadBackContent.getText().toString().trim());
                break;


        }
    }


    public void saveFeedBackInfo(String content,String email){
        if (TextUtils.isEmpty(email)){
            dialog.dismiss();
            ToastUtil.show(mContext,"邮箱不能为空");
            return;
        }else if (TextUtils.isEmpty(content)){
            dialog.dismiss();
            ToastUtil.show(mContext,"内容不能为空");
            return;
        }
        FeedBack feedBack = new FeedBack();
        feedBack.setBmobUser(BmobUser.getCurrentUser());
        feedBack.setContent(content);
        feedBack.setEmail(email);
        feedBack.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                    dialog.dismiss();
                    ToastUtil.show(mContext,"感谢您的反馈");
                    finish();
                }else {
                    dialog.dismiss();
                    ToastUtil.show(mContext,e.getErrorCode()+": "+e.getMessage());
                }
            }
        });
    }
}
