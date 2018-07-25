package com.yh.jiran.http;

import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 根据传入参数生成RequestBody
 */
public class BodyFactory {

    public static RequestBody createRequestBody(HashMap<String, Object> params) {
        String json = new Gson().toJson(params);
        return RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
    }
}
