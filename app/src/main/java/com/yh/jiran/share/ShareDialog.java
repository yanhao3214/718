package com.yh.jiran.share;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yh.jiran.R;
import com.yh.ui.utils.DimenUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * @author: 闫昊
 * @date: 2018/8/1
 * @function: 分享弹窗
 */
public class ShareDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private boolean mWithStar = false;

    private int mShareType;
    private String mTitle;
    private String mText;
    private String mUrl;
    private String mImgUrl;
    private String mLocImage;
    private PlatformActionListener mShareListener = null;

    private AppCompatTextView tvStar;
    private AppCompatButton btnCancel;
    private AppCompatTextView tvWechat;
    private AppCompatTextView tvMoments;
    private AppCompatTextView tvLink;
    private AppCompatTextView tvPhoto;
    private AppCompatTextView tvCancel;

    public ShareDialog(@NonNull Context context) {
        super(context, R.style.ShareDialogStyle);
        mContext = context;
    }

    public ShareDialog(@NonNull Context context, boolean withStar) {
        super(context, R.style.ShareDialogStyle);
        mContext = context;
        mWithStar = withStar;
    }

    public ShareDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    public ShareDialog(@NonNull Context context, int themeResId, boolean withStar) {
        super(context, themeResId);
        mContext = context;
        mWithStar = withStar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share_layout);
        init();
    }

    private void init() {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = DimenUtil.getScreenWidth(mContext);
        window.setAttributes(layoutParams);

        tvStar = findViewById(R.id.tv_star);
        tvWechat = findViewById(R.id.tv_wechat);
        tvMoments = findViewById(R.id.tv_moments);
        tvLink = findViewById(R.id.tv_link);
        tvPhoto = findViewById(R.id.tv_photo);
        btnCancel = findViewById(R.id.btn_cancel);


        tvStar.setOnClickListener(this);
        tvWechat.setOnClickListener(this);
        tvMoments.setOnClickListener(this);
        tvLink.setOnClickListener(this);
        tvPhoto.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        tvStar.setVisibility(mWithStar ? View.VISIBLE : View.GONE);
        // TODO: 2018/8/2 动态设置“微信好友”的margin
//        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tvWechat.getLayoutParams();
//        lp.leftMargin = mWithStar ? 36 : 50;
//        tvWechat.setLayoutParams(lp);
    }

    public ShareDialog setShareType(int shareType) {
        mShareType = shareType;
        return this;
    }

    public ShareDialog setTitle(String title) {
        mTitle = title;
        return this;
    }

    public ShareDialog setText(String text) {
        mText = text;
        return this;
    }

    public ShareDialog setUrl(String url) {
        mUrl = url;
        return this;
    }

    public ShareDialog setImgUrl(String imgUrl) {
        mImgUrl = imgUrl;
        return this;
    }

    public ShareDialog setLocImage(String locImage) {
        mLocImage = locImage;
        return this;
    }

    public ShareDialog setListener(PlatformActionListener shareListener) {
        mShareListener = shareListener;
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_star:
                break;
            case R.id.tv_wechat:
                shareData(PlatformType.WEICHAT);
                break;
            case R.id.tv_moments:
                shareData(PlatformType.WEICHAT_MOMENTS);
                break;
            case R.id.tv_link:
                break;
            case R.id.tv_photo:
                break;
            case R.id.btn_cancel:
                break;
            default:
                break;
        }
        dismiss();
    }

    private void shareData(PlatformType type) {
        ShareData shareData = new ShareData();
        Platform.ShareParams params = new Platform.ShareParams();
        params.setShareType(mShareType);
        params.setTitle(mTitle);
        params.setText(mText);
        params.setUrl(mUrl);
        params.setImageUrl(mImgUrl);
        params.setImagePath(mLocImage);
        shareData.type = type;
        shareData.params = params;
        ShareManager.getInstance().shareData(shareData, mShareListener);
    }
}
