package com.a10835.easywol.common.http.impl;

import com.a10835.easywol.common.http.IResponse;

/**
 * Created by 10835 on 2018/5/1.
 */

public class BaseResponse implements IResponse{

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getData() {
        return null;
    }
}
