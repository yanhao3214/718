package com.yh.jiran.module.search.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.share.ShareDialog;
import com.yh.jiran.utils.Consts;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

/**
 * @author: 闫昊
 * @date: 2018/7/31
 * @function: 搜索结果-动态
 */
public class SearchDynamicFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;

//    private List<HomeStar> mStars = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_search_dynamic_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @Override
    protected Object setContent() {
        return R.layout.fragment_search_dynamic_layout;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
    }

    @OnClick(R.id.btn_share)
    public void onViewClicked() {
//        OnekeyShare oks = new OnekeyShare();
//        oks.disableSSOWhenAuthorize();
//        // title标题，微信、QQ和QQ空间等平台使用
//        oks.setTitle("来自计然区块链的分享");
//        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
////        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
//        // 启动分享GUI
//        oks.show(getContext());
//        testShareSdk();
        showShareDialog();
    }

    private void showShareDialog() {
        new ShareDialog(getContext(),true)
                .setTitle("分享弹窗的标题")
                .setText("分享的具体内容")
                .setUrl("http://www.baidu.com")
                .setImgUrl(Consts.BING_PIC)
                .show();
    }

    private void testShareSdk() {
        Platform.ShareParams params = new Platform.ShareParams();
        params.setAddress("http:www.baidu.com");
        params.setTitle("计然区块链");
        params.setComment("分享动态的具体内容");
        params.setImageUrl(Consts.BING_PIC);
        Platform platform = ShareSDK.getPlatform(QQ.NAME);
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(getContext(), "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(getContext(), "分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(getContext(), "分享被取消了", Toast.LENGTH_SHORT).show();
            }
        });
        platform.share(params);
    }
}
