package com.yh.jiran.module.test;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @author: 闫昊
 * @date: 2018/7/20
 * @function:
 */
public interface QueryService {

    @GET
    Call<ResponseBody> queryInfo(@Url String url, @QueryMap WeakHashMap<String, Object> params);
}
