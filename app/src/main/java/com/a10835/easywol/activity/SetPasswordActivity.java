package com.a10835.easywol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.common.http.BmobApi;
import com.a10835.easywol.utils.ActivityCollector;
import com.a10835.easywol.utils.LogUtil;
import com.a10835.easywol.view.ProgressDialog;
import com.a10835.easywol.view.ToastUtil;
import com.a10835.easywol.view.XEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * type为1调用注册接口
 * type为2调用重置密码接口
 * 成功后返回登录界面
 */

public class SetPasswordActivity extends BaseActivity {
    public static final String CODE = "code";
    public static final String PHONE = "phone";
    public static final String TYPE = "type";
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.et_register_password)
    XEditText etRegisterPassword;
    @BindView(R.id.btn_sure)
    TextView btnSure;
    private String phone;
    private String code;
    private String password;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        ButterKnife.bind(this);
    }

    @Override
    public void setEevent() {

    }

    @Override
    public void initData() {
        phone = getIntent().getStringExtra(PHONE);
        code = getIntent().getStringExtra(CODE);
        type = getIntent().getIntExtra(TYPE,1);
    }

    public static Intent newIntent(Context context, String code, String phone,int type) {
        Intent intent = new Intent(context, SetPasswordActivity.class);
        intent.putExtra(CODE, code);
        intent.putExtra(PHONE, phone);
        intent.putExtra(TYPE,type);
        return intent;
    }

    @OnClick({R.id.btn_back, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_sure:
                if (type == 1){
                    SignOrLogin();
                }else {
                    resetPassword();
                }
                break;
        }
    }

    public void SignOrLogin() {
        password = etRegisterPassword.getText().toString().trim();
        if (password.length()>=8&&password.length()<=16) {

            final ProgressDialog dialog = new ProgressDialog();
            dialog.show(getSupportFragmentManager(), "SETPASSWORD");
            BmobUser user = new BmobUser();
            user.setMobilePhoneNumber(phone);
            user.setPassword(password);
            user.signOrLogin(code, new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser bmobUser, BmobException e) {
                    if (e == null) {
                        LogUtil.d("注册成功" + bmobUser.getUsername() + "-" + bmobUser.getObjectId());
                        dialog.dismiss();
                        ToastUtil.show(mContext,"注册成功");
                        ActivityCollector.finshAll();
                        startActivity(new Intent(mContext,LoginActivity.class));
                    } else {
                        dialog.dismiss();
                        ToastUtil.show(mContext,"注册失败");
                        LogUtil.d("失败:" + e.getMessage());
                    }
                }
            });
        }else {
            etRegisterPassword.showSharkAnimotion();
        }

    }

    /**
     * 重置密码
     */
    public void resetPassword(){
        password = etRegisterPassword.getText().toString().trim();
        final ProgressDialog dialog = new ProgressDialog();
        dialog.show(getSupportFragmentManager(), "SETPASSWORD");
        BmobApi.resetPassword(code, password, new BmobApi.ResetPasswordListner() {
            @Override
            public void onSuccess(Exception e) {
                dialog.dismiss();
                ToastUtil.show(mContext,"重置密码成功");
                ActivityCollector.finshAll();
                startActivity(new Intent(mContext,LoginActivity.class));
            }

            @Override
            public void onError(Exception e) {
                dialog.dismiss();
                ToastUtil.show(mContext,"重置密码失败");
            }
        });
    }
}
