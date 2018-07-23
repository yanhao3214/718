package com.yh.jiran.mvp.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.yh.jiran.http.Network;
import com.yh.jiran.module.test.model.GankBeauty;
import com.yh.jiran.module.test.model.GankBeautyResult;
import com.yh.jiran.mvp.contract.CourierContract;
import com.yh.jiran.mvp.model.CourierModel;
import com.yh.jiran.mvp.ui.activity.CourierActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: 闫昊
 * @date: 2018/7/20
 * @function:
 */
public class CourierPresenter implements CourierContract.Presenter {
    private CourierContract.View mView;
    private CourierContract.Model mModel;

    public CourierPresenter(CourierContract.View view) {
        this.mView = view;
        mModel = new CourierModel();
    }

    @Override
    public void query() {
        String com = mView.getCom();
        String num = mView.getNumber();
        mView.updateUi(mModel.getDatas(com, num));
        mView.showBing("http://cn.bing.com/az/hprichbg/rb/VaranasiCandles_EN-US12230572751_1920x1080.jpg");
    }

    private void rxRetrofitTest() {
        Network.getBingApi().getPic("bing_pic")
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mView.showLoading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.bindLifecycle())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object o) {
                        mView.hideLoading();
                        mView.showBing((String) o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yh", "CourierActivity: e == " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

//        Network.getGankApi().getBeauties(30, 1)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<GankBeautyResult>() {
//                    @Override
//                    public void accept(GankBeautyResult gankBeautyResult) throws Exception {
//                        mView.showLoading();
//                    }
//                })
//                .observeOn(Schedulers.io())
//                .map(new Function<GankBeautyResult, List<String>>() {
//                    @Override
//                    public List<String> apply(GankBeautyResult gankBeautyResult) throws Exception {
//                        List<GankBeauty> beauties = gankBeautyResult.beauties;
//                        int size = beauties.size();
//                        List<String> urls = new ArrayList<>(size);
//                        for (GankBeauty gankBeauty : beauties) {
//                            urls.add(gankBeauty.url);
//                        }
//                        return urls;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(mView.bindLifecycle())
//                .subscribe(new Consumer() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        mView.hideLoading();
//                        mView.showBeauties((List<String>) o);
//                    }
//                });
    }
}
