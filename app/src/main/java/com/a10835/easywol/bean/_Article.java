package com.a10835.easywol.bean;

import cn.bmob.v3.BmobObject;

/**
 * 作者：10835
 * 时间：2018/5/15/21:27
 */
public class _Article extends BmobObject {
    private String title;
    private String url;
    private String content;
    private String type;
    private String m_id;
    private String desc;
    private String cover;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
