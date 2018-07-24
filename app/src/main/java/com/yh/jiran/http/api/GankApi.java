// (c)2016 Flipboard Inc, All Rights Reserved.

package com.yh.jiran.http.api;

import com.yh.jiran.module.test.model.entity.GankBeautyResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import io.reactivex.Observable;

public interface GankApi {
    @GET("data/福利/{number}/{page}")
    Observable<GankBeautyResult> getBeauties(@Path("number") int number, @Path("page") int page);
}
