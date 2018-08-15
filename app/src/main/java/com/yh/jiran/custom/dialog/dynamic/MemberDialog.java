package com.yh.jiran.custom.dialog.dynamic;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yh.jiran.R;
import com.yh.jiran.custom.dialog.dynamic.callback.CommonCallback;
import com.yh.ui.utils.DimenUtil;

/**
 * @author: 闫昊
 * @date: 2018/8/8
 * @function: 星球内：成员用户对动态的操作弹窗 + 星球外：所有人对动态的操作弹窗
 */
public class MemberDialog extends Dialog implements View.OnClickListener {

    /**
     * Data
     */
    private boolean mIsMine;
    private boolean mHasCollect;
    private CommonCallback mCommonCallback;

    /**
     * Data
     */
    private Context mContext;
    private AppCompatTextView tvCollect;
    private AppCompatTextView tvDelete;
    private AppCompatTextView tvCancel;

    public MemberDialog(@NonNull Context context, boolean isMine, boolean hasCollect) {
        super(context, R.style.ShareDialogStyle);
        mContext = context;
        mIsMine = isMine;
        mHasCollect = hasCollect;
    }

    public MemberDialog(@NonNull Context context, int themeResId, boolean isMine, boolean hasCollect) {
        super(context, themeResId);
        mContext = context;
        mIsMine = isMine;
        mHasCollect = hasCollect;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_dynamic_member_layout);
        init();
    }

    private void init() {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        // TODO: 2018/8/8 这3行去掉，Dialog宽度如何变化？
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = DimenUtil.getScreenWidth(mContext);
        window.setAttributes(params);

        tvCollect = findViewById(R.id.tv_collect);
        tvDelete = findViewById(R.id.tv_delete);
        tvCancel = findViewById(R.id.tv_cancel);

        tvCollect.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        tvCollect.setText(mHasCollect ? "取消收藏" : "收藏");
        tvDelete.setVisibility(mIsMine ? View.VISIBLE : View.GONE);
    }

    public MemberDialog perform(CommonCallback commonCallback) {
        mCommonCallback = commonCallback;
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_delete:
                mCommonCallback.onDelete(this);
                break;
            case R.id.tv_collect:
                mCommonCallback.onCollect(this);
                break;
            case R.id.tv_cancel:
                mCommonCallback.onCancel(this);
                break;
            default:
                break;
        }
    }
}
