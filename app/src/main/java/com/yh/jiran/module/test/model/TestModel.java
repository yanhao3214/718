package com.yh.jiran.module.test.model;

import com.yh.jiran.module.test.TestContract;
import com.yh.jiran.module.test.model.api.TestPost;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public class TestModel implements TestContract.Model {
    @Override
    public Observable<String> getData(RequestBody body) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.121:7835/pchain-api/")
                .client(new OkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(TestPost.class)
                .postTest(body);
    }

    @Override
    public String getData() {
        return null;
    }
}
