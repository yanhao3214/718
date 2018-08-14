package com.yh.jiran.module.star.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.utils.RouterMap;
import com.yh.ui.utils.TextUtil;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;


/**
 * @author: 闫昊
 * @date: 2018/8/14
 * @function: 星球资料编辑页
 */
@Route(path = RouterMap.PATH_STAR_EDIT_ACTIVITY)
public class StarEditActivity extends ImmerseActivity {

    public static int STAR_NAME_COUNT = 10;
    public static int STAR_DESC_COUNT = 80;

    @BindView(R.id.iv_back)
    AppCompatImageView ivBack;
    @BindView(R.id.edt_name)
    AppCompatEditText edtName;
    @BindView(R.id.tv_name_count)
    AppCompatTextView tvNameCount;
    @BindView(R.id.edt_desc)
    AppCompatEditText edtDesc;
    @BindView(R.id.tv_desc_count)
    AppCompatTextView tvDescCount;
    @BindView(R.id.iv_image)
    AppCompatImageView ivImage;
    @BindView(R.id.iv_camera)
    CircleImageView ivCamera;

    @Override
    protected int setContent() {
        return R.layout.activity_star_edit_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);

        TextUtil.setHint(this, edtName, getString(R.string.topic_star_desc_hint), 18, R.color.colorGrey3);

        countDown(edtName, tvNameCount, STAR_NAME_COUNT);
        countDown(edtDesc, tvDescCount, STAR_DESC_COUNT);
    }

    private void countDown(EditText edit, TextView text, int count) {
        Disposable disposable = Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count1, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count1) {
                    emitter.onNext(s.length());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            };
            edit.addTextChangedListener(textWatcher);
            emitter.setCancellable(() -> edit.removeTextChangedListener(textWatcher));
        }).subscribe(integer -> text.setText(String.valueOf(integer - count)));
    }

    @OnClick({R.id.iv_back, R.id.tv_submit, R.id.iv_image, R.id.iv_camera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                // TODO: 2018/8/14 合法性验证；发送消息
                finish();
                break;
            case R.id.iv_image:
            case R.id.iv_camera:
                // TODO: 2018/8/14 图片选择优化
                break;
            default:
                break;
        }
    }
}
