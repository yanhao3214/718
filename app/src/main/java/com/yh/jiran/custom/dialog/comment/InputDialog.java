package com.yh.jiran.custom.dialog.comment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yh.jiran.R;
import com.yh.jiran.custom.dialog.member.callback.MemberOperationType;
import com.yh.jiran.custom.dialog.member.callback.MuteClickListener;
import com.yh.jiran.custom.dialog.member.callback.MuteRelieveClickListener;
import com.yh.jiran.custom.dialog.member.callback.RemoveClickListener;
import com.yh.ui.utils.DimenUtil;

/**
 * @author: 闫昊
 * @date: 2018/8/8
 * @function: 星球资料页-星球成员列表：主理人对人员（嘉宾+成员）的操作弹窗
 */
public class InputDialog extends Dialog implements View.OnClickListener {

    /**
     * Data
     */

    /**
     * Data
     */
    private Context mContext;
    private AppCompatEditText edtComment;
    private AppCompatImageView ivImg;
    private AppCompatTextView tvPublish;

    public InputDialog(@Nullable Context context) {
        super(context, R.style.CommentDialogStyle);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment_layout);
        init();
    }

    private void init() {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        // TODO: 2018/8/8 这3行去掉，Dialog宽度如何变化？
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = DimenUtil.getScreenWidth(mContext);
        window.setAttributes(params);

        edtComment = findViewById(R.id.edt_comment);
        ivImg = findViewById(R.id.iv_img);
        tvPublish = findViewById(R.id.tv_publish);

        ivImg.setOnClickListener(this);
        tvPublish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_img:

                break;
            case R.id.tv_publish:

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
