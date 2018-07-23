package com.yh.jiran.http.api;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author: 闫昊
 * @date: 2018/7/23
 * @function: "http://guolin.tech/api/bing_pic"
 */
public interface BingApi {

    /**
     * 获取必应图片
     * @param type
     * @return
     */
    @GET("{type}")
    Observable<String> getPic(@Path("type") String type);
}
