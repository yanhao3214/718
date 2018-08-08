package com.yh.jiran.custom.dialog.common;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;

import com.yh.jiran.R;

/**
 * @author: 闫昊
 * @date: 2018/8/8
 * @function: 通用Dialog
 */
public class JrDialog extends Dialog {

    /**
     * Ui
     */
    private View mView;
    private AppCompatTextView tvTitle;
    private AppCompatTextView tvMessage;
    private AppCompatButton btnPositive;
    private AppCompatButton btnNegative;

    /**
     * Data
     */
    private Context mContext;
    private CharSequence mTitle;
    private CharSequence mMessage;
    private CharSequence mPositiveText;
    private CharSequence mNegativeText;
    private DialogInterface.OnClickListener mPositiveListener;
    private DialogInterface.OnClickListener mNegativeListener;

    public JrDialog(@NonNull Context context) {
        super(context);
        mContext = context;
        init();
    }

    public JrDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        init();
    }

    private void init() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_common_layout, null);
        setContentView(mView);
        tvTitle = mView.findViewById(R.id.dialog_title);
        tvMessage = mView.findViewById(R.id.dialog_message);
        btnPositive = mView.findViewById(R.id.btn_positive);
        btnNegative = mView.findViewById(R.id.btn_negative);

//        mPositiveText = mContext.getText(R.string.common_yes);
//        mNegativeText = mContext.getText(R.string.common_cancel);
        mNegativeListener = (dialogInterface, i) -> dialogInterface.dismiss();

        config();
    }

    private void config() {
        setCancelable(false);

        if (null != mTitle) {
            tvTitle.setText(mTitle);
        }

        if (null != mMessage) {
            tvMessage.setText(mMessage);
        }

        if (null != mPositiveText) {
            btnPositive.setText(mPositiveText);
            if (null != mPositiveListener) {
                btnPositive.setOnClickListener(view -> mPositiveListener
                        .onClick(JrDialog.this, DialogInterface.BUTTON_POSITIVE));
            }
        }

        if (null != mNegativeText) {
            btnNegative.setVisibility(View.VISIBLE);
            btnNegative.setText(mNegativeText);
            if (null != mNegativeListener) {
                btnNegative.setOnClickListener(view -> mNegativeListener.
                        onClick(JrDialog.this, DialogInterface.BUTTON_NEGATIVE));
            }
        } else {
            btnNegative.setVisibility(View.GONE);
        }
    }

    public JrDialog title(CharSequence title) {
        mTitle = title;
        return this;
    }

    public JrDialog title(int title) {
        mTitle = mContext.getText(title);
        return this;
    }

    public JrDialog message(CharSequence msg) {
        mMessage = msg;
        return this;
    }

    public JrDialog message(int msg) {
        mMessage = mContext.getText(msg);
        return this;
    }

    public JrDialog withPositive(CharSequence positiveText, OnClickListener positiveListener) {
        mPositiveText = positiveText;
        mPositiveListener = positiveListener;
        return this;
    }

    public JrDialog withPositive(int positiveText, OnClickListener positiveListener) {
        mPositiveText = mContext.getText(positiveText);
        mPositiveListener = positiveListener;
        return this;
    }

    public JrDialog withNegative(CharSequence negativeText, OnClickListener negativeListener) {
        mNegativeText = negativeText;
        mNegativeListener = negativeListener;
        return this;
    }

    public JrDialog withNegative(int negativeText, OnClickListener negativeListener) {
        mNegativeText = mContext.getText(negativeText);
        mNegativeListener = negativeListener;
        return this;
    }

    /**
     * 设置确认按钮，字样默认“确认”，点击事件自定义
     */
    public JrDialog positive(OnClickListener positiveListener) {
        mPositiveText = mContext.getText(R.string.common_yes);
        mPositiveListener = positiveListener;
        return this;
    }

    /**
     * 设置取消按钮，字样默认“取消”，点击事件默认（dismiss()）
     */
    public JrDialog negative() {
        mNegativeText = mContext.getText(R.string.common_cancel);
        return this;
    }

    @Override
    public void show() {
        config();
        super.show();
    }
}
