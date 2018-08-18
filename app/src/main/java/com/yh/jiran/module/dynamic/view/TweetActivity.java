package com.yh.jiran.module.dynamic.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.custom.text.SimpleTextWatcher;
import com.yh.jiran.module.dynamic.view.adapter.TweetPicGridAdapter;
import com.yh.jiran.utils.RouterMap;
import com.yh.jiran.utils.image.JrGlideEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author: 闫昊
 * @date: 2018/8/14
 * @function: 动态发布页
 */
@Route(path = RouterMap.PATH_DYNAMIC_TWEET_ACTIVITY)
public class TweetActivity extends ImmerseActivity implements EasyPermissions.PermissionCallbacks {

    public static final String TWEET_CONNATE = "mIsConnate";

    public static final int TWEET_TEXT_CONNATE_COUNT = 5000;
    public static final int TWEET_TEXT_FORWARD_COUNT = 140;
    public static final int TWEET_PICS_COUNT = 9;
    public final int REQUEST_CODE_TWEET_PICS = 333;
    private String mPermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private TweetPicGridAdapter mGridAdapter;
    private List<Object> mPics = new ArrayList<>();
    private int mAddPic = R.drawable.img_tweet_add_pic;
    private int mCount;
    private boolean mTextLegal = false;
    private boolean mAccessoryLegal = false;

    @BindView(R.id.tv_star)
    AppCompatTextView tvStar;
    @BindView(R.id.tv_tweet)
    AppCompatTextView tvTweet;
    @BindView(R.id.layout_tweet_star)
    LinearLayout layoutTweetStar;
    @BindView(R.id.edt_tweet)
    AppCompatEditText edtTweet;
    @BindView(R.id.tv_limit)
    AppCompatTextView tvLimit;
    @BindView(R.id.iv_source)
    AppCompatImageView ivSource;
    @BindView(R.id.tv_author)
    AppCompatTextView tvAuthor;
    @BindView(R.id.tv_source_star)
    AppCompatTextView tvSourceStar;
    @BindView(R.id.tv_source_text)
    AppCompatTextView tvSourceText;
    @BindView(R.id.layout_source)
    LinearLayout layoutSource;
    @BindView(R.id.grid_pics)
    GridView gridPics;


    @Autowired
    boolean mIsConnate;

    @Override
    protected int setContent() {
        return R.layout.activity_tweet_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);

        mCount = mIsConnate ? TWEET_TEXT_CONNATE_COUNT : TWEET_TEXT_FORWARD_COUNT;
        mPics.add(mAddPic);
        mGridAdapter = new TweetPicGridAdapter(this, mPics);
        gridPics.setAdapter(mGridAdapter);

        tvTweet.setEnabled(false);
        gridPics.setVisibility(mIsConnate ? View.VISIBLE : View.GONE);
        layoutSource.setVisibility(mIsConnate ? View.GONE : View.VISIBLE);

        verifyInput();

        /**
         * 删除某张图片
         */
        mGridAdapter.setOnPicDeleteListener(position -> {
            mPics.remove(position);
            if (!(mPics.get(mPics.size() - 1) instanceof Integer)) {
                mPics.add(mAddPic);
            }
            mGridAdapter.notifyDataSetChanged();
        });

        gridPics.setOnItemClickListener((parent, view, position, id) -> {
            choosePics(TWEET_PICS_COUNT - mPics.size() + 1);
            Toast.makeText(TweetActivity.this, "选择图片--来自Matisse", Toast.LENGTH_SHORT).show();
        });
    }

    @SuppressLint("CheckResult")
    private void verifyInput() {
        Observable
                .create((ObservableOnSubscribe<Integer>) emitter -> {
                    SimpleTextWatcher watcher = new SimpleTextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            emitter.onNext(s.length());
                        }
                    };
                    edtTweet.addTextChangedListener(watcher);
                    emitter.setCancellable(() -> edtTweet.removeTextChangedListener(watcher));
                })
                .subscribe(integer -> {
                    int offset = mCount - integer;
                    tvLimit.setVisibility(offset >= 0 ? View.GONE : View.VISIBLE);
                    tvLimit.setText(offset >= 0 ? "" : String.valueOf(offset));
                    mTextLegal = integer > 0 && integer <= mCount;

                    tvTweet.setTextColor(ContextCompat.getColor(this, mTextLegal || mAccessoryLegal ? R.color.text_blue : R.color.colorGrey3));
                    tvTweet.setEnabled(mTextLegal || mAccessoryLegal);
                });
    }

    /**
     * 选择图片：1.动态权限的检查与申请； 2.真正选择图片
     */
    private void choosePics(int num) {
        if (EasyPermissions.hasPermissions(this, mPermission)) {
            matisse(num);
        } else {
            EasyPermissions.requestPermissions(this, "需要权限打开相册", 101, mPermission);
        }
    }

    /**
     * 选择图片：真正执行
     */
    private void matisse(int num) {
        Matisse
                .from(this)
                .choose(MimeType.ofAll())
                .countable(true)
                .maxSelectable(num > 0 ? num : 1)
                .gridExpectedSize(350)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.8f)
                .theme(R.style.Matisse_Zhihu)
                .imageEngine(new JrGlideEngine())
                .forResult(REQUEST_CODE_TWEET_PICS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TWEET_PICS && resultCode == RESULT_OK) {
            List<Uri> matisses = Matisse.obtainResult(data);
            int current = mPics.size();
            int next = matisses.size();
            mPics.addAll(current - 1, matisses);
            if (current + next > 9) {
                mPics.remove(mPics.size() - 1);
            }

            mGridAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "权限申请成功", Toast.LENGTH_SHORT).show();
        matisse(TWEET_PICS_COUNT);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "权限申请失败", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.iv_close, R.id.tv_tweet, R.id.layout_tweet_star, R.id.layout_source})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.tv_tweet:
                finish();

                // TODO: 2018/8/18 发送消息：发布动态
                break;
            case R.id.layout_tweet_star:
                ARouter.getInstance().build(RouterMap.PATH_STAR_PICK_ACTIVITY).navigation();
                break;
            case R.id.layout_source:
                ARouter.getInstance().build(RouterMap.PATH_DYNAMIC_FORWARD_ACTIVITY).navigation();
                break;
            default:
                break;
        }
    }
}
