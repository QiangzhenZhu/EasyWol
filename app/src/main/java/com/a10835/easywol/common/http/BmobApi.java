package com.a10835.easywol.common.http;

import android.content.Context;
import android.util.Log;

import com.a10835.easywol.activity.RegisterSMSActivity;
import com.a10835.easywol.utils.LogUtil;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 10835 on 2018/5/3.
 */

public class BmobApi {

    /**
     * 获取验证码
     * @param phone
     * @param template
     * @param listner
     */
    public static void getSmsCode(String phone, String template, final SmsOnClickListner listner){
        BmobSMS.requestSMSCode(phone,"模板一", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId,BmobException ex) {
                if(ex==null){//验证码发送成功
                    LogUtil.setTag("bmob");
                    LogUtil.d("smile"+"短信id："+smsId);//用于查询本次短信发送详情
                    listner.onSuccess(ex);
                } else{
                    listner.onError(ex);
                }
            }
        });
    }

    /**
     * 短信监听
     */
    public interface SmsOnClickListner{
        void onSuccess(BmobException e);
        void onError(BmobException e);
    }

    public interface  VerigySmsListner{
        void onSuccess(BmobException e);
        void onError(BmobException e);
    }


    /**
     * 验证验证码
     * @param mContext
     * @param phone
     * @param code
     * @param listner
     */
    public static void verifySmsCode(Context mContext, String phone, String code, final VerigySmsListner listner){
        BmobSMS.verifySmsCode(phone, code,new UpdateListener() {

            @Override
            public void done(BmobException ex) {
                // TODO Auto-generated method stub
                if(ex==null){//短信验证码已验证成功
                    LogUtil.setTag("bmob");
                    LogUtil.d( "验证通过");
                    listner.onSuccess(ex);
                }else{
                    LogUtil.d("验证失败：code ="+ex.getErrorCode()+",msg = "+ex.getLocalizedMessage());
                    listner.onError(ex);
                }
            }
        });
    }

    /**
     * 登录接口
     */
    public  static void login(String phone, String password, final LoginStateListner loginStateListner){
        BmobUser.loginByAccount(phone, password, new LogInListener<BmobUser>() {

            @Override
            public void done(BmobUser user, BmobException e) {
                if(user!=null){
                    LogUtil.setTag("Login");
                    LogUtil.d("登录成功");
                    loginStateListner.onSuccess(e);
                }else {
                    LogUtil.setTag("Login");
                    LogUtil.d("登录失败");
                    loginStateListner.onError(e);
                }
            }
        });
    }

    /**
     * 登录监听接口
     */
    public interface LoginStateListner{
        void onSuccess(BmobException e);
        void onError(BmobException e);
    }

    /**
     * 重置密码
     * @param code
     * @param newPassword
     */
    public static void resetPassword(String code, String newPassword, final ResetPasswordListner listner){
        BmobUser.resetPasswordBySMSCode(code,newPassword, new UpdateListener() {

            @Override
            public void done(BmobException ex) {
                if(ex==null){
                    LogUtil.setTag("resetPassword");
                    LogUtil.d("重置密码成功");
                    listner.onSuccess(ex);

                }else{
                    LogUtil.setTag("resetPassword");
                    LogUtil.d("重置失败：code ="+ex.getErrorCode()+",msg = "+ex.getLocalizedMessage());
                    listner.onError(ex);
                }
            }
        });
    }

    /**
     * 重置密码监听接口
     */
    public interface ResetPasswordListner{
        void onSuccess(Exception e);
        void onError(Exception e);
    }




}
