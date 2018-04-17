package com.a10835.easywol.common.http;

import java.util.Map;

/**
 * Created by 10835 on 2018/4/17.
 */

public interface IRequest {
    public static final String GET = "GET";
    public static final String POST = "POST";

    /**
     * 指定请求的方式
     * @param method
     */
    public void setRequestMethod(String method);

    /**
     * 设置请求体
     * @param key
     * @param value
     */

    public void setBody(String key,String value);

    /**
     * 指定请求头
     * @param key
     * @param value
     */
    void setHeard(String key,String value);

    /**
     * 获取url
     * @return
     */
    String getUrl();


    /**
     * 获取请求头
     * @return
     */
    Map<String,String> getHeader();


    /**
     * 获取请求体
     * @return
     */
    Object getBody();




}
