package com.a10835.easywol.common.http;

/**
 * Created by 10835 on 2018/4/17.
 */

public interface IResponse {
    //返回状态码
    int getCode();
    //返回消息的内容
    String getData();
}
