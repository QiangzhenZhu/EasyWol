package com.a10835.easywol.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * 作者：10835
 * 时间：2018/5/19/00:11
 */
public class FeedBack extends BmobObject {
    private String content;
    private String email;
    private BmobUser bmobUser;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BmobUser getBmobUser() {
        return bmobUser;
    }

    public void setBmobUser(BmobUser bmobUser) {
        this.bmobUser = bmobUser;
    }
}
