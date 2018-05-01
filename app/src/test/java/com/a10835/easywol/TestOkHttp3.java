package com.a10835.easywol;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 10835 on 2018/4/29.
 */

public class TestOkHttp3 {


    /**
     * OkHttpClient get测试；
     */
    @Test
    public void testGet(){
        //create a okhttpclient object
        OkHttpClient client = new OkHttpClient();
        //create a request builder
        Request request = new Request.Builder().url("http://eu.httpbin.org/get?id=2920").build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * test OkHttpPost
     */
    @Test
    public void testPost(){
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");//数据类型
        RequestBody requestBody = RequestBody.create(mediaType,"{\"name\":\"zhuzhenqiang\"}");
        Request request = new Request.Builder()
                .url("http://eu.httpbin.org/post")//请求行
                //.header()//请求头
                .post(requestBody)//请求体
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * test interceptor
     */
    @Test
    public void testInterCeptor(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                long start = System.currentTimeMillis();
                Request request = chain.request();
                Response response = chain.proceed(request);
                long end = System.currentTimeMillis();
                System.out.println("TestInterCeptor:"+(end - start));
                return response;
            }
        };
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Request request = new Request.Builder().url("http://eu.httpbin.org/get?id=2920").build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 缓存测试
     */
    @Test
    public void testCache(){
        Cache cache = new Cache(new File("cache.cache"),1024*1024);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        Request request = new Request.Builder().url("http://eu.httpbin.org/get?id=2920").cacheControl(CacheControl.FORCE_NETWORK).build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
