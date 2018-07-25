package com.yh.jiran.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.yh.core.app.BaseActivity;
import com.yh.jiran.R;
import com.yh.jiran.utils.Paths;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: 闫昊
 * @date: 2018/7/18
 * @function: 测试界面，测试代码，待删除
 */
@Route(path = Paths.PATH_ROUTER_ACTIVITY)
public class RouterActivity extends BaseActivity {

    @BindView(R.id.tv_interval)
    TextView tvInterval;
    private Disposable mDisposable;

    @BindView(R.id.edt_input)
    EditText edtInput;
    @BindView(R.id.btn_search)
    Button btnSearch;
    private int drawableRes = R.drawable.ic_launcher_background;
    @BindView(R.id.tv_router)
    TextView tvRouter;
    @BindView(R.id.iv_rx_java)
    ImageView ivRxJava;
    private String mIntervalText = "YH";

    @Override
    protected int setContent() {
        return R.layout.activity_arouter_layout;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.d("yh", "RouterActivity: onCreate()");
        String result = getIntent().getStringExtra("text");
        Boolean success = getIntent().getBooleanExtra("success", false);
        if (success) {
            tvRouter.setText(result);
        }
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {
        super.initView();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.e("yh", "Observable emit 1" + "\n");
                emitter.onNext(1);
                Log.e("yh", "Observable emit 2" + "\n");
                emitter.onNext(2);
                Log.e("yh", "Observable emit 3" + "\n");
                emitter.onNext(3);
                emitter.onComplete();
                Log.e("yh", "Observable emit 4" + "\n");
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                Log.e("yh", "onSubscribe: mDisposable.isDisposable() == " + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                i++;
                if (i == 2) {
                    mDisposable.dispose();
                }
                Log.e("yh", "onNext: i == " + i);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yh", "onError : e == " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                Log.e("yh", "onComplete" + "\n");
            }
        });

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    Log.e("yh", "Observable thread == " + Thread.currentThread().getName());
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onComplete();
                }
            }
        })
                //发射时候所在的线程，只能设置一次，多次设置不改变
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                //接受事件所在的线程，可多次设置，每次设置都改变线程
                .observeOn(Schedulers.newThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("yh", "doOnNext : accept : " + Thread.currentThread().getName());
                        Log.e("yh", "doOnNext : accept : i == " + integer);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("yh", "subscribe: accept : " + Thread.currentThread().getName());
                        Log.e("yh", "subscribe: accept : i == " + integer);
                    }
                });

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                if (!emitter.isCancelled()) {
                    emitter.onNext("Hello World");
                    emitter.onNext("Hello You");
                }
            }
        }, BackpressureStrategy.DROP)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Observable<String> createButtonClickObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                btnSearch.setOnClickListener((view) -> emitter.onNext(edtInput.getText().toString().trim()));
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        btnSearch.setOnClickListener(null);
                    }
                });
            }
        });
    }

    private Observable<String> createEditChangeObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                final TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        emitter.onNext(charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                };
                edtInput.addTextChangedListener(textWatcher);
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        edtInput.removeTextChangedListener(textWatcher);
                    }
                });
            }
        }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return s.length() >= 2;
            }
        }).debounce(1500, TimeUnit.MILLISECONDS);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onStart() {
        super.onStart();
        Observable<String> searchObservable = createButtonClickObservable();
        searchObservable
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        showLoading();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<String, List<String>>() {
                    @Override
                    public List<String> apply(String s) throws Exception {
                        return searchForList(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                // TODO: 2018/7/23 RxJava生命周期绑定，位置？使用？
                .compose(RxLifecycle.bind(lifecycle()))
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        stopLoading();
                        updateUi(strings);
                    }
                });

        Observable<String> editChangedObservable = createEditChangeObservable();
        mDisposable = editChangedObservable
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        showLoading();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<String, List<String>>() {
                    @Override
                    public List<String> apply(String s) throws Exception {
                        return searchForSimpleList(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        stopLoading();
                        tvRouter.setText(strings.toString().trim());
                    }
                });

        Flowable.interval(1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("yh", "interval - accept : " + aLong);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mIntervalText = mIntervalText.concat("" + aLong);
                        tvInterval.setText(mIntervalText);
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mDisposable == null || mDisposable.isDisposed()) {
            return;
        }
        mDisposable.dispose();
    }

    private void updateUi(List<String> strings) {
        tvRouter.setText(strings.toString());
    }

    private void stopLoading() {
    }

    private List<String> searchForList(String s) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(s);
        }
        return list;
    }

    private List<String> searchForSimpleList(String s) {
        List<String> list = new ArrayList<>();
        list.add(s);
        return list;
    }

    private void showLoading() {
        Toast.makeText(this, "loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
