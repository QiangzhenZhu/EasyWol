package com.a10835.easywol.common.http.impl;

import com.a10835.easywol.common.http.IRequest;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;

/**
 * Created by 10835 on 2018/5/1.
 */

public class BaseRequest implements IRequest {
    private String url;
    private String method = POST;
    private Map<String,String> header;
    private Map<String,String> body;

    public BaseRequest(String url) {
        this.url = url;
        header = new HashMap<>();
        body = new HashMap<>();
        //header.put()
    }

    @Override
    public void setRequestMethod(String method) {

    }

    @Override
    public void setBody(String key, String value) {

    }

    @Override
    public void setHeard(String key, String value) {

    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public Map<String, String> getHeader() {
        return null;
    }

    @Override
    public Object getBody() {
        return null;
    }
}
