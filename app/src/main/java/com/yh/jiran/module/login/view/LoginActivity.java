package com.yh.jiran.module.login.view;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseActivity;
import com.yh.jiran.R;
import com.yh.jiran.module.login.LoginContract;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 登录界面
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {
    public static final int LOGIN_CODE_VALID_TIME = 60;
    private AppCompatTextView tvGetCode;

    @Override
    protected int setContent() {
        return R.layout.activity_login_layout;
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    /**
     * 不带被压，用interval实现定时
     */
    private void onCodeClick() {
        Observable.interval(0,1, TimeUnit.SECONDS)
                .take(LOGIN_CODE_VALID_TIME+1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return LOGIN_CODE_VALID_TIME -aLong;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        tvGetCode.setClickable(false);
                        tvGetCode.setBackgroundResource(R.drawable.bg_discover_join_done);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // TODO: 2018/8/3
                    }

                    @Override
                    public void onNext(Long aLong) {
                        tvGetCode.setText(aLong + "s后获取");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        tvGetCode.setText("重新发送");
                        tvGetCode.setClickable(true);
                        tvGetCode.setBackgroundResource(R.drawable.bg_discover_join_normal);
                    }
                });
    }

    /**
     * 带背压，Thread.sleep()实现定时
     */
    private void flowableCodeClick() {
        Flowable
                .create(new FlowableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                        int s = LOGIN_CODE_VALID_TIME;
                        while (s > 0) {
                            emitter.onNext(s);
                            s--;
                            Thread.sleep(1000);
                        }
                        emitter.onComplete();
                    }
                }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        tvGetCode.setClickable(false);
                        tvGetCode.setBackgroundResource(R.drawable.bg_discover_join_done);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        //接受61个事件（包括onComplete）
                        s.request(LOGIN_CODE_VALID_TIME + 1);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        tvGetCode.setText(integer + "s后获取");
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                        tvGetCode.setText("重新发送");
                        tvGetCode.setClickable(true);
                        tvGetCode.setBackgroundResource(R.drawable.bg_discover_join_normal);
                    }
                });
    }

    @Override
    public void showError(int str) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public String getPhone() {
        return null;
    }

    @Override
    public String getVeriCode() {
        return null;
    }

    @Override
    public void login(String phone, String code) {

    }
}
