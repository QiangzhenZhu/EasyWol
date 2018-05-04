package com.a10835.easywol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.common.http.BmobApi;
import com.a10835.easywol.view.ProgressDialog;
import com.a10835.easywol.view.ToastUtil;
import com.a10835.easywol.view.XEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;

/**
 * 登录成功后经信息保存到本地，然后在SplashActivity里判断BmobUser存不存在来选择登陆还是直接跳转到主界面
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.et_login_phone)
    XEditText etLoginPhone;
    @BindView(R.id.et_login_password)
    XEditText etLoginPassword;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.tv_find_password)
    TextView tvFindPassword;
    @BindView(R.id.tv_user_register)
    TextView tvUserRegister;
    private String phone;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @Override
    public void setEevent() {
        btnLogin.setOnClickListener(this);
        tvFindPassword.setOnClickListener(this);
        tvUserRegister.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_find_password:
                Intent findIntent = new Intent(mContext,FindPassWordActivity.class);
                startActivity(findIntent);
                break;
            case R.id.tv_user_register:
                Intent registerIntent = new Intent(mContext,RegisterActivity.class);
                startActivity(registerIntent);
                break;
            default:
                break;
        }
    }

    /**
     * 登录操作
     */
    public void login() {
        phone = etLoginPhone.getText().toString().trim();
        password = etLoginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            etLoginPhone.showSharkAnimotion();
            return;
        } else if (TextUtils.isEmpty(password)) {
            etLoginPassword.showSharkAnimotion();
            return;
        } else {
            final ProgressDialog dialog = new ProgressDialog();
            dialog.show(getSupportFragmentManager(), "LOGIN");
            BmobApi.login(phone, password, new BmobApi.LoginStateListner() {
                @Override
                public void onSuccess(BmobException e) {
                    dialog.dismiss();

                    Intent intent = new Intent(mContext, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onError(BmobException e) {
                    dialog.dismiss();
                    ToastUtil.show(mContext, e.getMessage());
                }
            });
        }
    }
}
