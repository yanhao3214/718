package com.yh.jiran.module.user.view;

import android.annotation.SuppressLint;
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
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.module.login.model.entity.User;
import com.yh.jiran.utils.AccountManager;
import com.yh.jiran.utils.RouterMap;
import com.yh.jiran.utils.image.GlideLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 个人信息编辑页
 */
@Route(path = RouterMap.PATH_USER_EDIT_ACTIVITY)
public class UserEditActivity extends ImmerseActivity {
    public static final String EDIT_USER_ID = "user_id";
    public static final int EDIT_USER_DESC_COUNT = 30;
    private static final int PERMISSION_REQUEST_ALBUM = 1;
    private static final int CHOOSE_PHOTO = 2;

    @Autowired(name = EDIT_USER_ID)
    String mUserId;
    @Autowired
    String imgUrl;
    @Autowired
    String userName;
    @Autowired
    String desc;

    @BindView(R.id.iv_cancel)
    AppCompatImageView ivCancel;
    @BindView(R.id.tv_submit)
    AppCompatTextView tvSubmit;
    @BindView(R.id.iv_portrait)
    CircleImageView ivPortrait;
    @BindView(R.id.edt_name)
    AppCompatEditText edtName;
    @BindView(R.id.edt_desc)
    AppCompatEditText edtDesc;
    @BindView(R.id.tv_limit)
    AppCompatTextView tvLimit;


    @Override
    protected int setContent() {
        return R.layout.activity_user_edit_layout;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);
        Toast.makeText(this, "用户Id是：" + mUserId, Toast.LENGTH_SHORT).show();
        // TODO: 2018/8/12 发送请求，获取用户信息
        GlideLoader.load(this, imgUrl, ivPortrait);
        edtName.setText(userName);
        edtDesc.setText(desc);

        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
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
            edtDesc.addTextChangedListener(textWatcher);
            emitter.setCancellable(() -> edtDesc.removeTextChangedListener(textWatcher));
        }).subscribe(integer -> tvLimit.setText(String.valueOf(integer - EDIT_USER_DESC_COUNT)));
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick({R.id.iv_cancel, R.id.tv_submit, R.id.iv_portrait})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel:
                finish();
                break;
            case R.id.tv_submit:
                // TODO: 2018/8/12
                /**
                 * 1.检查合法性
                 * 2.提交数据，等待服务器返回：未通过，停留；通过，返回个人主页
                 * 3.返回数据给个人主页，主页刷新界面（部分）
                 */
                dataBack();
                break;
            case R.id.iv_portrait:
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_ALBUM);
                } else {
                    openAlbum();
                }
                break;
            default:
                break;
        }
    }

    private void dataBack() {
        Intent intent = new Intent();
        intent.putExtra("name", edtName.getText().toString());
        intent.putExtra("desc", edtDesc.getText().toString());
        setResult(RouterMap.RESULT_CODE_USER_EDIT, intent);
        finish();
    }

    private void openAlbum() {
        User user = AccountManager.getInstance().getCurrentUser();
        user.setHasPortrait(true);
        Toast.makeText(this, "打开相册", Toast.LENGTH_SHORT).show();
        Intent intentAlbum = new Intent("android.intent.action.GET_CONTENT");
        intentAlbum.setType("image/*");
        startActivityForResult(intentAlbum, CHOOSE_PHOTO);
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
