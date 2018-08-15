package com.yh.jiran.custom.dialog.member;

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
public class MemberOperateDialog extends Dialog implements View.OnClickListener {

    /**
     * Data
     */
    private MemberOperationType mType;
    private RemoveClickListener mRemoveistener;
    private MuteClickListener mMuteListener;
    private MuteRelieveClickListener mRelieveListener;

    /**
     * Data
     */
    private Context mContext;
    private AppCompatTextView tvRemoveGuest;
    private AppCompatTextView tvMuteTemp;
    private AppCompatTextView tvMuteForever;
    private AppCompatTextView tvMuteRelieveMute;

    public MemberOperateDialog(@NonNull Context context, MemberOperationType type) {
        super(context, R.style.ShareDialogStyle);
        mContext = context;
        mType = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_member_operate_layout);
        init();
    }

    private void init() {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        // TODO: 2018/8/8 这3行去掉，Dialog宽度如何变化？
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = DimenUtil.getScreenWidth(mContext);
        window.setAttributes(params);

        tvRemoveGuest = findViewById(R.id.tv_remove_guest);
        tvMuteTemp = findViewById(R.id.tv_mute_3);
        tvMuteForever = findViewById(R.id.tv_mute_forever);
        tvMuteRelieveMute = findViewById(R.id.tv_relieve_mute);

        tvRemoveGuest.setOnClickListener(this);
        tvMuteTemp.setOnClickListener(this);
        tvMuteForever.setOnClickListener(this);
        tvMuteRelieveMute.setOnClickListener(this);

        switch (mType) {
            case REMOVE_GUEST:
                tvRemoveGuest.setVisibility(View.VISIBLE);
                tvMuteTemp.setVisibility(View.GONE);
                tvMuteForever.setVisibility(View.GONE);
                tvMuteRelieveMute.setVisibility(View.GONE);
                break;
            case MUTE:
                tvRemoveGuest.setVisibility(View.GONE);
                tvMuteTemp.setVisibility(View.VISIBLE);
                tvMuteForever.setVisibility(View.VISIBLE);
                tvMuteRelieveMute.setVisibility(View.GONE);
                break;
            case RELIEVE_MUTE:
                tvRemoveGuest.setVisibility(View.GONE);
                tvMuteTemp.setVisibility(View.GONE);
                tvMuteForever.setVisibility(View.GONE);
                tvMuteRelieveMute.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public MemberOperateDialog removeGuest(RemoveClickListener removeClickListener) {
        mRemoveistener = removeClickListener;
        return this;
    }

    public MemberOperateDialog mute(MuteClickListener muteClickListener) {
        mMuteListener = muteClickListener;
        return this;
    }

    public MemberOperateDialog relieveMute(MuteRelieveClickListener muteRelieveClickListener) {
        mRelieveListener = muteRelieveClickListener;
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_remove_guest:
                mRemoveistener.remove(this);
                break;
            case R.id.tv_mute_3:
                mMuteListener.temp(this);
                break;
            case R.id.tv_mute_forever:
                mMuteListener.forever(this);
                break;
            case R.id.tv_relieve_mute:
                mRelieveListener.relieve(this);
                break;
            default:
                break;
        }
    }
}
