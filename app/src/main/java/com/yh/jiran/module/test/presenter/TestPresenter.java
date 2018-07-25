package com.yh.jiran.module.test.presenter;

import com.yh.jiran.http.BodyFactory;
import com.yh.jiran.module.test.TestContract;
import com.yh.jiran.module.test.model.TestModel;
import java.util.HashMap;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public class TestPresenter implements TestContract.Presenter {
    private TestContract.View mView;
    private TestContract.Model mModel;

    public TestPresenter(TestContract.View view) {
        this.mView = view;
        mModel = new TestModel();
    }

    @Override
    public void showData() {
        String input = mView.getInput();
        RequestBody body = makeBody(input);
        mModel.getData(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> mView.showLoading())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        mView.hideLoading();
                        mView.update(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private RequestBody makeBody(String input) {
        HashMap<String, Object> params = new HashMap<>(3);
        params.put("extPubKey", "123" + input);
        params.put("mobile", "789");
        params.put("password", "456");
        return BodyFactory.createRequestBody(params);
    }
}
