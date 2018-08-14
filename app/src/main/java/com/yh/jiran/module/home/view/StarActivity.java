package com.yh.jiran.module.home.view;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.custom.dialog.common.JrDialog;
import com.yh.jiran.custom.dialog.share.ShareDialog;
import com.yh.jiran.module.home.HomeMineContract;
import com.yh.jiran.module.home.model.entity.HomeStar;
import com.yh.jiran.module.home.view.adapter.BaseFragmentPagerAdapter;
import com.yh.jiran.module.star.view.DynamicInFragment;
import com.yh.jiran.utils.Consts;
import com.yh.jiran.utils.RouterMap;
import com.yh.jiran.utils.image.GlideLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 主题星球页面
 */
@Route(path = RouterMap.PATH_STAR_HOME_ACTIVITY)
public class StarActivity extends ImmerseActivity implements HomeMineContract.View {
    public static final String STAR_ID = "star_id";
    private BaseFragmentPagerAdapter mAdapter;
    List<BaseFragment> mFragments = new ArrayList<>(2);

    @BindArray(R.array.tab_star)
    String[] mTabs;
    @BindView(R.id.iv_image)
    AppCompatImageView ivImage;
    @BindView(R.id.tv_desc)
    AppCompatTextView tvDesc;
    @BindView(R.id.tv_member)
    AppCompatTextView tvMember;
    @BindView(R.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R.id.tv_join)
    AppCompatTextView tvJoin;
    @BindView(R.id.tv_push_operate)
    AppCompatTextView tvPushOperate;
    @BindView(R.id.tv_push_done)
    AppCompatTextView tvPushDone;
    @BindView(R.id.tab_topic)
    TabLayout tabTopic;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.iv_tweet)
    AppCompatImageView ivTweet;
    @BindView(R.id.iv_tweet_enable)
    AppCompatImageView ivTweetEnable;

    @Override
    protected int setContent() {
        return R.layout.activity_topic_star_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        GlideLoader.loadRound(this, Consts.BING_PIC5, ivImage);
        mFragments.add(new DynamicInFragment());
        mFragments.add(new DynamicInFragment());
        mAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), mTabs, mFragments);
        viewPager.setAdapter(mAdapter);
        tabTopic.setupWithViewPager(viewPager);

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

    @Override
    public void refreshUi(List<HomeStar> stars) {

    }

    @OnClick({R.id.iv_back, R.id.iv_detail, R.id.iv_share, R.id.iv_image, R.id.tv_name, R.id.tv_desc,
            R.id.tv_join, R.id.tv_push_operate, R.id.tv_push_done, R.id.iv_tweet, R.id.iv_tweet_enable})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_detail:
            case R.id.iv_image:
            case R.id.tv_name:
            case R.id.tv_desc:
                toStarInfo();
                break;
            case R.id.iv_share:
                new ShareDialog(this, true)
                        .setText("分享星球的内容")
                        .setTitle("分享星球的标题")
                        .setUrl(Consts.GUOLIN_PIC)
                        .setImgUrl(Consts.BING_PIC)
                        .show();
                break;
            case R.id.tv_join:
                tvJoin.setVisibility(View.INVISIBLE);
                tvPushOperate.setVisibility(View.VISIBLE);
                new JrDialog(this)
                        .title("消息推送")
                        .message("是否开启消息通知")
                        .negative()
                        .positive((dialog, which) -> {
                            openPush();
                            dialog.dismiss();
                        })
                        .show();

                // TODO: 2018/8/14 发送消息：加入星球
                break;
            case R.id.tv_push_operate:
                openPush();
                break;
            case R.id.tv_push_done:
                shutPush();
                break;
            case R.id.iv_tweet:
                ARouter.getInstance()
                        .build(RouterMap.PATH_DYNAMIC_TWEET_ACTIVITY)
                        .withString(RouterMap.STAR_ID, "00024")
                        .navigation();
                break;
            case R.id.iv_tweet_enable:
                break;
            default:
                break;
        }
    }

    private void shutPush() {
        tvPushDone.setVisibility(View.INVISIBLE);
        tvPushOperate.setVisibility(View.VISIBLE);

        // TODO: 2018/8/14 发送消息：取消推送
    }

    private void openPush() {
        tvPushOperate.setVisibility(View.INVISIBLE);
        tvPushDone.setVisibility(View.VISIBLE);

        // TODO: 2018/8/14 发送消息：开启推送
    }

    private void toStarInfo() {
        ARouter.getInstance()
                .build(RouterMap.PATH_STAR_INFO_ACTIVITY)
                .withString(RouterMap.STAR_ID, "000023")
                .navigation();
    }
}
