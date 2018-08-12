package com.yh.jiran.module.login.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.utils.SoftKeyUtil;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.custom.ClearEdit;
import com.yh.jiran.module.login.LoginContract;
import com.yh.jiran.module.login.model.entity.User;
import com.yh.jiran.utils.AccountManager;
import com.yh.jiran.utils.RouterMap;
import com.yh.ui.utils.TextUtil;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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
@Route(path = RouterMap.PATH_LOGIN_ACTIVITY)
public class LoginActivity extends ImmerseActivity implements LoginContract.LoginView {
    public static final int LOGIN_CODE_VALID_TIME = 60;
    public static final int PHONE_NUMBER_COUNT = 11;
    public static final int PHONE_CODE_COUNT = 4;
    private User mUser;
    private Disposable mDisposable;

    @BindView(R.id.edt_phone_input)
    ClearEdit edtPhoneInput;
    @BindView(R.id.edt_code_input)
    AppCompatEditText edtCodeInput;
    @BindView(R.id.tv_get_code)
    AppCompatTextView tvGetCode;
    @BindView(R.id.tv_notice)
    AppCompatTextView tvNotice;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    @BindView(R.id.tv_addition)
    AppCompatTextView tvAddition;
    @BindView(R.id.tv_protocol)
    AppCompatTextView tvProtocol;

    @Override
    protected int setContent() {
        return R.layout.activity_login_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        TextUtil.setHint(this, edtPhoneInput, "输入手机号", 16, R.color.textDark3);
        TextUtil.setHint(this, edtCodeInput, "短信验证码", 16, R.color.textDark3);
    }

    @Override
    protected void initData() {
        super.initData();
//        AccountManager.getInstance().createFakeUser();
        mUser = AccountManager.getInstance().getCurrentUser();
        mDisposable = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            TextWatcher phoneWatcher = new TextWatcher() {
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
            edtPhoneInput.addTextChangedListener(phoneWatcher);
            emitter.setCancellable(() -> edtPhoneInput.removeTextChangedListener(phoneWatcher));
        }).subscribe(s -> {
            tvGetCode.setTextColor(ContextCompat.getColor(LoginActivity.this,
                    s.length() == PHONE_NUMBER_COUNT ? R.color.text_blue : R.color.colorGrey3));
            tvGetCode.setClickable(s.length() == PHONE_NUMBER_COUNT);
        });

        Disposable disposableCode = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                TextWatcher codeWatcher = new TextWatcher() {
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
                edtCodeInput.addTextChangedListener(codeWatcher);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (s.length() == PHONE_CODE_COUNT) {
                    SoftKeyUtil.hideSoftKeyboard(LoginActivity.this, edtCodeInput);
                    login();
                }
            }
        });
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 不带背压，用interval实现定时
     */
    private void startCountDown() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(LOGIN_CODE_VALID_TIME + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return LOGIN_CODE_VALID_TIME - aLong;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        tvGetCode.setClickable(false);
                        tvGetCode.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.colorGrey3));
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
                        tvGetCode.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.text_blue));
                    }
                });
    }

    /**
     * 带背压，Thread.sleep()实现定时
     * todo 删除
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

    @OnClick({R.id.tv_get_code, R.id.btn_login, R.id.tv_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                startCountDown();
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_protocol:
                ARouter.getInstance()
                        .build(RouterMap.PATH_WEBVIEW_ACTIVITY)
                        .withString("title", "计然蜂巢用户协议手册")
                        .withString("url", "https://www.jianshu.com/p/119823e5cfb5")
                        .navigation();
                break;
            default:
                break;
        }
    }

    private void login() {
        String phone = edtPhoneInput.getText().toString();
        String code = edtCodeInput.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
        }
        if (phone.length() != PHONE_NUMBER_COUNT) {
            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
        }
        if (!code.equals(code)) {
            tvNotice.setVisibility(View.VISIBLE);
            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
        }
        if (!TextUtils.isEmpty(phone) && phone.length() == PHONE_NUMBER_COUNT && !TextUtils.isEmpty(code) && code.equals(code)) {
            ARouter.getInstance()
                    .build(mUser.getHasName() ? RouterMap.PATH_HOME_ACTIVITY : RouterMap.PATH_INFO_ACTIVITY)
                    .navigation();
            finish();
        }
    }
}
