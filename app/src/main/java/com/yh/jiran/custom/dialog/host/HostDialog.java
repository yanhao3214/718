package com.yh.jiran.custom.dialog.host;

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
import com.yh.jiran.custom.dialog.callback.CommonCallback;
import com.yh.jiran.custom.dialog.callback.ConcernCallback;
import com.yh.jiran.custom.dialog.callback.EliteCallback;
import com.yh.jiran.custom.dialog.callback.MuteCallback;
import com.yh.jiran.custom.dialog.callback.MuteType;
import com.yh.ui.utils.DimenUtil;

/**
 * @author: 闫昊
 * @date: 2018/8/8
 * @function: 星球内：主理人对动态的操作弹窗
 */
public class HostDialog extends Dialog implements View.OnClickListener {

    /**
     * Data
     */
    private boolean mIsMine;
    private boolean mIsElite;
    private boolean mHasMute;
    private MuteType mMuteType;
    private boolean mHasConcern;
    private boolean mHasCollect;
    private CommonCallback mCommonCallback;
    private EliteCallback mEliteCallback;
    private MuteCallback mMuteCallback;
    private ConcernCallback mConcernCallback;

    /**
     * Data
     */
    private Context mContext;
    private AppCompatTextView tvElite;
    private AppCompatTextView tvMute3;
    private AppCompatTextView tvMuteForever;
    private AppCompatTextView tvDelete;
    private AppCompatTextView tvConcern;
    private AppCompatTextView tvCollect;
    private AppCompatTextView tvCancel;

    public HostDialog(@NonNull Context context, boolean isMine, boolean isElite, boolean hasMute,
                      boolean hasConcern, boolean hasCollect) {
        super(context, R.style.ShareDialogStyle);
        mContext = context;
        mIsMine = isMine;
        mIsElite = isElite;
        mHasMute = hasMute;
        mHasConcern = hasConcern;
        mHasCollect = hasCollect;
    }

    public HostDialog(@NonNull Context context, int themeResId, boolean isMine, boolean isElite,
                      boolean hasMute, boolean hasConcern, boolean hasCollect) {
        super(context, themeResId);
        mContext = context;
        mIsMine = isMine;
        mIsElite = isElite;
        mHasMute = hasMute;
        mHasConcern = hasConcern;
        mHasCollect = hasCollect;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_dynamic_host_layout);
        init();
    }

    private void init() {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        // TODO: 2018/8/8 这3行去掉，Dialog宽度如何变化？
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = DimenUtil.getScreenWidth(mContext);
        window.setAttributes(params);

        tvElite = findViewById(R.id.tv_elite);
        tvMute3 = findViewById(R.id.tv_mute_3);
        tvMuteForever = findViewById(R.id.tv_mute_forever);
        tvDelete = findViewById(R.id.tv_delete);
        tvConcern = findViewById(R.id.tv_concern);
        tvCollect = findViewById(R.id.tv_collect);
        tvCancel = findViewById(R.id.tv_cancel);

        tvElite.setOnClickListener(this);
        tvMute3.setOnClickListener(this);
        tvMuteForever.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        tvConcern.setOnClickListener(this);
        tvCollect.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        tvElite.setText(mIsElite ? "移除精华" : "加入精华");
        if (mIsMine) {
            tvMute3.setVisibility(View.GONE);
            tvMuteForever.setVisibility(View.GONE);
            tvConcern.setVisibility(View.GONE);
        } else {
            tvMute3.setVisibility(View.VISIBLE);
            tvMute3.setText(mHasMute ? "解除禁言" : "禁言3天");
            tvMuteForever.setVisibility(mHasMute ? View.GONE : View.VISIBLE);
            tvConcern.setVisibility(mHasConcern ? View.GONE : View.VISIBLE);
        }
        tvCollect.setText(mHasCollect ? "取消收藏" : "收藏");
    }

    public HostDialog common(CommonCallback commonCallback) {
        mCommonCallback = commonCallback;
        return this;
    }

    public HostDialog elite(EliteCallback eliteCallback) {
        mEliteCallback = eliteCallback;
        return this;
    }

    public HostDialog mute(MuteCallback muteCallback) {
        mMuteCallback = muteCallback;
        return this;
    }

    public HostDialog concern(ConcernCallback concernCallback) {
        mConcernCallback = concernCallback;
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_elite:
                mEliteCallback.onElite(this);
                break;
            case R.id.tv_mute_3:
                if (!mHasMute) {
                    mMuteCallback.onMuteTemp(this);
                } else {
                    mMuteCallback.onCancel(this);
                }
                break;
            case R.id.tv_mute_forever:
                mMuteCallback.onMuteForever(this);
                break;
            case R.id.tv_delete:
                mCommonCallback.onDelete(this);
                break;
            case R.id.tv_concern:
                mConcernCallback.onConcern(this);
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
