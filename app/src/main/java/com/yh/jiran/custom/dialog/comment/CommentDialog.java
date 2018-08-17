package com.yh.jiran.custom.dialog.comment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yh.jiran.R;
import com.yh.ui.utils.DimenUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;

/**
 * @author: 闫昊
 * @date: 2018/8/8
 * @function: 星球资料页-星球成员列表：主理人对人员（嘉宾+成员）的操作弹窗
 */
public class CommentDialog extends Dialog implements View.OnClickListener {

    /**
     * Data
     */
    public static final int COMMENT_COUNT = 140;
    private boolean mWithSource;
    private OnCommentClickListener mCommentListener;
    private String mSourceName;

    /**
     * View
     */
    private Context mContext;
    private View mView;
    private AppCompatTextView tvReply;
    private AppCompatTextView tvSourceName;
    private AppCompatEditText edtComment;
    private AppCompatImageView ivImg;
    private AppCompatTextView tvCountDown;
    private AppCompatTextView tvPublish;

    public CommentDialog(@Nullable Context context) {
        this(context, false);
    }

    public CommentDialog(@Nullable Context context, boolean withSource) {
        super(context, R.style.CommentDialogStyle);
        mContext = context;
        mWithSource = withSource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment_layout);
        initView();
        initData();
        initEvent();
    }

    @SuppressLint("InflateParams")
    private void initView() {

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = DimenUtil.getScreenWidth(mContext);
        window.setAttributes(params);

        tvReply = findViewById(R.id.tv_reply);
        tvSourceName = findViewById(R.id.tv_source_name);
        edtComment = findViewById(R.id.edt_comment);
        ivImg = findViewById(R.id.iv_img);
        tvCountDown = findViewById(R.id.tv_count_down);
        tvPublish = findViewById(R.id.tv_publish);
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        tvReply.setVisibility(mWithSource ? View.VISIBLE : View.GONE);
        tvSourceName.setVisibility(mWithSource ? View.VISIBLE : View.GONE);
        tvPublish.setEnabled(false);
        if (!TextUtils.isEmpty(mSourceName)) {
            tvSourceName.setText("@" + mSourceName);
        }
        ivImg.setOnClickListener(this);
        tvPublish.setOnClickListener(this);
    }

    @SuppressLint("CheckResult")
    private void initEvent() {
        Observable
                .create((ObservableOnSubscribe<Integer>) emitter -> {
                    TextWatcher textWatcher = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            emitter.onNext(s.length());
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    };
                    edtComment.addTextChangedListener(textWatcher);
                    emitter.setCancellable(() -> edtComment.removeTextChangedListener(textWatcher));
                })
                .subscribe(integer -> {
                    int offset = COMMENT_COUNT - integer;
                    tvCountDown.setText(offset >= 0 ? "" : String.valueOf(offset));
                    tvPublish.setTextColor(ContextCompat.getColor(mContext,
                            (integer > 0 && offset >= 0) ? R.color.text_blue : R.color.colorGrey3));
                    tvPublish.setEnabled(integer > 0 && offset >= 0);
                });
    }

    @SuppressLint("SetTextI18n")
    public CommentDialog sourceName(String name) {
        mSourceName = name;
        return this;
    }

    public CommentDialog publish(OnCommentClickListener commentClickListener) {
        mCommentListener = commentClickListener;
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_img:
                break;
            case R.id.tv_publish:
                if (mCommentListener != null) {
                    mCommentListener.onPublish(
                            this, edtComment.getText().toString().trim());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setOnKeyListener(@Nullable OnKeyListener onKeyListener) {
        OnKeyListener keyListener = (dialog, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                cancel();
            }
            return false;
        };
        super.setOnKeyListener(keyListener);
    }
}
