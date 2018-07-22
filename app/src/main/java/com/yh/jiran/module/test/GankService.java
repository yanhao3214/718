package com.yh.jiran.module.test;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author: 闫昊
 * @date: 2018/7/20
 * @function: http://gank.io/api/today
 */
public interface GankService {
    @GET("today")
    Call<String> getGank();
}
