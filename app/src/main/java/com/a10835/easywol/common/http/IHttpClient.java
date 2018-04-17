package com.a10835.easywol.common.http;

/**
 * Created by 10835 on 2018/4/17.
 */

public interface IHttpClient {
    IResponse get(IRequest request,boolean forceCache);
    IResponse post(IRequest request,boolean forceCache);
}
