package com.yh.jiran.module.login.view;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.utils.SoftKeyUtil;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.module.login.LoginContract;
import com.yh.jiran.module.login.model.entity.User;
import com.yh.jiran.module.login.presenter.InfoPresenter;
import com.yh.jiran.utils.AccountManager;
import com.yh.jiran.utils.RouterMap;
import com.yh.jiran.utils.image.GlideLoader;
import com.yh.ui.img.GlideCircleTransform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author: 闫昊
 * @date: 2018/8/3
 * @function: 确定头像和用户名
 */
@Route(path = RouterMap.PATH_INFO_ACTIVITY)
public class InfoActivity extends ImmerseActivity implements LoginContract.InfoView {

    public static final int USER_NAME_COUNT = 15;
    private static final int PERMISSION_REQUEST_ALBUM = 101;
    private static final int CHOOSE_PHOTO = 102;
    private boolean isLegal = true;
    private LoginContract.InfoPresenter mPresenter;
    private User mUser;
    private RequestOptions mOptions;
    private Bitmap mBitmap;

    @BindView(R.id.iv_portrait)
    AppCompatImageView ivPortrait;
    @BindView(R.id.iv_camera)
    AppCompatImageView ivCamera;
    @BindView(R.id.edt_name)
    AppCompatEditText edtName;
    @BindView(R.id.tv_notice)
    AppCompatTextView tvNotice;
    @BindView(R.id.btn_enter)
    AppCompatButton btnEnter;

    @Override
    protected int setContent() {
        return R.layout.activity_info_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        mPresenter = new InfoPresenter(this);
        mOptions = new RequestOptions().centerCrop().transform(new GlideCircleTransform(this));
        Glide.with(this).load(R.drawable.vector_user_portrait).apply(mOptions).into(ivPortrait);
        mPresenter.present();
        edtName.setSelection(0, edtName.getText().toString().length());
        SoftKeyUtil.showSoftKeyboardDelayed(this, edtName);

        String imagePath = getExternalCacheDir() + "/profile.jpg";
        mBitmap = BitmapFactory.decodeFile(imagePath);
        if (mBitmap != null) {
            GlideLoader.loadCircle(this, mBitmap, ivPortrait);
        }

        /**
         * 1.只支持中英文、数字、下划线
         * 2.最长15个字
         * 3.是否被占用
         * 4.是否填写
         */
        Disposable disposable = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            TextWatcher nameWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    emitter.onNext(charSequence.toString().trim());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };
            edtName.addTextChangedListener(nameWatcher);
            emitter.setCancellable(() -> edtName.removeTextChangedListener(nameWatcher));
        })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (TextUtils.isEmpty(s)) {
                            isLegal = false;
                        } else if (!isInputLegal(s)) {
                            tvNotice.setText("只支持输入中英文、数字和下划线");
                            tvNotice.setVisibility(View.VISIBLE);
                            isLegal = false;
                        } else if (isRepeated(s)) {
                            tvNotice.setText("该用户名已被占用");
                            tvNotice.setVisibility(View.VISIBLE);
                        } else if (s.length() == USER_NAME_COUNT) {
                            tvNotice.setText("用户名最多15个字");
                            tvNotice.setVisibility(View.VISIBLE);
                        } else {
                            tvNotice.setVisibility(View.INVISIBLE);
                            isLegal = true;
                        }
                    }
                });
    }

    @Override
    protected void initData() {
        super.initData();
        mUser = AccountManager.getInstance().getCurrentUser();
    }

    private boolean isRepeated(String s) {
        return mPresenter.verifyRepeat(s);
    }

    /**
     * 用户名只支持中英文、数字和下划线
     */
    private boolean isInputLegal(String s) {
        String regex = "[A-Za-z0-9_\\u4e00-\\u9fa5]+";
        String regex1 = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
        Matcher matcher = Pattern.compile(regex).matcher(s);
        return matcher.matches();
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
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

    @OnClick({R.id.iv_portrait, R.id.iv_camera, R.id.btn_enter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_portrait:
                openAlbum();
                break;
            case R.id.iv_camera:
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_ALBUM);
                } else {
                    openAlbum();
                }
                break;
            case R.id.btn_enter:
                if (isLegal) {
                    // TODO: 2018/8/4 登录
                    ARouter.getInstance().build(mUser.getInStar() ? RouterMap.PATH_HOME_ACTIVITY : RouterMap.PATH_REC_STAR_ACTIVITY).navigation();
                    finish();
                } else {
                    Toast.makeText(this, "请完善个人信息", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 选择头像
     */
    private void openAlbum() {
        User user = AccountManager.getInstance().getCurrentUser();
        user.setHasPortrait(true);
        Toast.makeText(this, "打开相册", Toast.LENGTH_SHORT).show();

        Intent intentAlbum = new Intent("android.intent.action.GET_CONTENT");
        intentAlbum.setType("image/*");
        startActivityForResult(intentAlbum, CHOOSE_PHOTO);
    }

    @Override
    public void setDefaultName(String name) {
        edtName.setText(name);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_ALBUM:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "相册打开失败：无权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Bitmap bitmap = null;
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        Uri uri = data.getData();
                        try {
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        GlideLoader.loadCircle(this, bitmap, ivPortrait);
                        // TODO: 2018/8/6 存储到本地 1.存在文件则直接直接更新，不存在则创建并存储；2.上传服务器；3.进入页面的时候读取头像
                        File file = new File(getExternalCacheDir(), "profile.jpg");
                        if (file.exists()) {
                            file.delete();
                        }
                        try {
                            file.createNewFile();
                            FileOutputStream out = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                            out.flush();
                            out.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}
