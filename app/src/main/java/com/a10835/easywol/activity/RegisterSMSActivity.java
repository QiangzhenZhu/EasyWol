package com.a10835.easywol.activity;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.common.http.BmobApi;
import com.a10835.easywol.view.PasswordView;
import com.a10835.easywol.view.ProgressDialog;
import com.a10835.easywol.view.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;

public class RegisterSMSActivity extends BaseActivity {
    public static final String PHONE = "SMS_PHONE";
    public static final String TYPE = "type";
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_ver_code)
    TextView tvVerCode;
    @BindView(R.id.passwordView)
    PasswordView passwordView;
    @BindView(R.id.btn_ref_get_sms)
    TextView btnRefGetSms;
    private String phone;
    private CountDownTimer timer;
    private boolean isClicable = false;
    private String code;
    private int type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sms);
        ButterKnife.bind(this);


    }



    @Override
    public void setEevent() {
        passwordView.setPasswordListener(new PasswordView.PasswordListener() {
            @Override
            public void passwordChange(String changeText) {

            }

            @Override
            public void passwordComplete() {
                code = passwordView.getPassword();
                //VerifySmsCode();
                Intent intent = SetPasswordActivity.newIntent(mContext,code,phone,type);
                startActivity(intent);


            }

            @Override
            public void keyEnterPress(String password, boolean isComplete) {

            }
        });
    }

    @Override
    public void initData() {

        timer = new CountDownTimer(60*1000,1000) {
            @Override
            public void onTick(long l) {
                String ref = String.format(getResources().getString(R.string.tv_register_sms_next),String.valueOf(l/1000));
                btnRefGetSms.setText(ref);

            }

            @Override
            public void onFinish() {
                isClicable = true;
                btnRefGetSms.setText("重新获取");
            }
        };
        timer.start();
        phone = getIntent().getStringExtra(PHONE);
        type = getIntent().getIntExtra(TYPE,1);
        String text = String.format(getResources().getString(R.string.tv_ver_code_register_sms),phone);
        tvVerCode.setText(text);

    }

    @OnClick({R.id.btn_back, R.id.tv_ver_code, R.id.passwordView, R.id.btn_ref_get_sms})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_ref_get_sms:
                if (isClicable){
                   getSms();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 获取短信验证
     */
    public void getSms(){
        BmobApi.getSmsCode(phone, "模板一", new BmobApi.SmsOnClickListner() {
            @Override
            public void onSuccess(BmobException ex) {
                isClicable = false;
                timer.start();
            }

            @Override
            public void onError(BmobException ex) {
                ToastUtil.show(mContext,ex.toString());
            }
        });
    }
    /**
     * 验证短信验证码
     *//*
    public void VerifySmsCode(){
        final ProgressDialog dialog = new ProgressDialog();
        dialog.show(getSupportFragmentManager(),"VERIFY");
        BmobApi.verifySmsCode(mContext, phone, code, new BmobApi.VerigySmsListner() {
            @Override
            public void onSuccess(BmobException e) {
                dialog.dismiss();
                Intent intent = SetPasswordActivity.newIntent(mContext,code,phone);
                startActivity(intent);

            }

            @Override
            public void onError(BmobException e) {
                dialog.dismiss();
                ToastUtil.show(mContext,"短信验证错误");


            }
        });
    }*/


    /**
     * 返回Intent
     * @param context
     * @param phone
     * @param type 1注册 2找回密码
     * @return
     */
    public static Intent newIntent(Context context, String phone,int type) {
        Intent intent = new Intent(context, RegisterSMSActivity.class);
        intent.putExtra(PHONE, phone);
        intent.putExtra(TYPE,type);
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }


}
