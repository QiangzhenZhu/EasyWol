package com.a10835.easywol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.common.http.BmobApi;
import com.a10835.easywol.view.ProgressDialog;
import com.a10835.easywol.view.ToastUtil;
import com.a10835.easywol.view.XEditText;
import com.blankj.utilcode.util.RegexUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;

public class FindPassWordActivity extends BaseActivity {


    @BindView(R.id.et_find_password_phone)
    XEditText etFindPasswordPhone;
    @BindView(R.id.btn_next)
    TextView btnNext;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    private String phone;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pass_word);
        ButterKnife.bind(this);
    }

    @Override
    public void setEevent() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.btn_back, R.id.et_find_password_phone, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.et_find_password_phone:

                break;
            case R.id.btn_next:
                phone = etFindPasswordPhone.getText().toString().trim();
                if (!TextUtils.isEmpty(phone) && RegexUtils.isMobileSimple(phone)){
                    getSms(phone);
                }else {
                    etFindPasswordPhone.showSharkAnimotion();
                }
                break;
        }
    }

    public void getSms(final String phone){
        showProgressDialog();
        BmobApi.getSmsCode(phone, "模板一", new BmobApi.SmsOnClickListner() {
            @Override
            public void onSuccess(BmobException e) {
                progressDialog.dismiss();
                Intent intent = RegisterSMSActivity.newIntent(mContext,phone,2);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(BmobException e) {
                progressDialog.dismiss();
                ToastUtil.show(mContext,e.toString());
            }
        });
    }

    public void showProgressDialog()
    {
        progressDialog = new ProgressDialog();
        progressDialog.show(getSupportFragmentManager(),"FINDPASSWORD");databaseList();
        progressDialog.setCancelable(false);
    }
}
