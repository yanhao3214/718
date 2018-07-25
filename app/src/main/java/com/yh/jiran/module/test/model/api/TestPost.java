package com.yh.jiran.module.test.model.api;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public interface TestPost {

    @POST("api/test/test")
    Observable<String> postTest(@Body RequestBody testBody);
}
