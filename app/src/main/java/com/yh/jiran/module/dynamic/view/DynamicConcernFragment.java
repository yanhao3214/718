package com.yh.jiran.module.dynamic.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseFragment;
import com.yh.core.utils.NumberUtil;
import com.yh.jiran.R;
import com.yh.jiran.base.WebViewActivity;
import com.yh.jiran.custom.dialog.callback.CommonCallback;
import com.yh.jiran.custom.dialog.callback.ConcernCallback;
import com.yh.jiran.custom.dialog.callback.EliteCallback;
import com.yh.jiran.custom.dialog.callback.MuteCallback;
import com.yh.jiran.custom.dialog.common.JrDialog;
import com.yh.jiran.custom.dialog.host.HostDialog;
import com.yh.jiran.custom.dialog.member.MemberDialog;
import com.yh.jiran.module.dynamic.DynamicConcernContract;
import com.yh.jiran.module.dynamic.model.entity.DynamicOut;
import com.yh.jiran.module.dynamic.presenter.DynamicConcernPresenter;
import com.yh.jiran.module.dynamic.view.adapter.DynamicOutAdapter;
import com.yh.jiran.module.home.view.StarActivity;
import com.yh.jiran.module.user.view.UserActivity;
import com.yh.jiran.custom.dialog.share.ShareDialog;
import com.yh.jiran.utils.Consts;
import com.yh.jiran.utils.Paths;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/7/25
 * @function: 动态-关注页
 */
public class DynamicConcernFragment extends BaseFragment implements DynamicConcernContract.View {

    public static final String DYNAMIC_OUT_TYPE_CONNATE = "1";
    public static final String DYNAMIC_OUT_TYPE_FORWARD = "2";
    private DynamicConcernContract.Presenter mPresenter;
    public static final int DYNAMIC_PAGE_COUNT = 15;
    private int page;
    private DynamicOutAdapter mAdapter;
    private List<DynamicOut> mList = new ArrayList<>();
    private boolean mSelfCollect = false;

    @BindView(R.id.recycler_concern)
    RecyclerView mRecycler;

    @Override
    protected Object setContent() {
        return R.layout.fragment_dynamic_concern_layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_dynamic_concern_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    public boolean isSelfCollect() {
        return mSelfCollect;
    }

    public void setSelfCollect(boolean mSelfCollect) {
        this.mSelfCollect = mSelfCollect;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mAdapter = new DynamicOutAdapter(mList, mSelfCollect);
        mRecycler.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecycler);

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            DynamicOut dynamic = mList.get(position);
            if (dynamic.getType().equals(DYNAMIC_OUT_TYPE_FORWARD)) {
                switch (view.getId()) {
                    case R.id.tv_trigger:
                        toUserHome(dynamic.getTriggerId());
                        break;
                    case R.id.iv_star:
                    case R.id.tv_name:
                        toStarHome(dynamic.getStarId());
                        break;
                    case R.id.btn_join:
                        AppCompatButton btnJoin =
                                (AppCompatButton) adapter.getViewByPosition(position, R.id.btn_join);
                        AppCompatButton btnMore =
                                (AppCompatButton) adapter.getViewByPosition(position, R.id.btn_more);
                        btnJoin.setVisibility(View.GONE);
                        btnMore.setVisibility(View.VISIBLE);
                        // TODO: 2018/8/7 发送加入星球消息
                        break;
                    case R.id.btn_more:
                        operate();
                        break;
                    case R.id.iv_collect:
                        onCollect((AppCompatImageView) adapter.getViewByPosition(position, R.id.iv_collect));
                        break;
                    case R.id.tv_text:
                        toDynamicHome(dynamic.getDynamicId());
                        break;
                    case R.id.tv_origin_star:
                        toStarHome(dynamic.getsStarId());
                        break;
                    case R.id.tv_source_text:
                        toDynamicHome(dynamic.getsDynamicId());
                        break;
                    case R.id.layout_link:
                        toLink(dynamic.getsLinkUrl(), dynamic.getsLinkTitle());
                        break;
                    case R.id.iv_publisher:
                    case R.id.tv_publisher:
                        toUserHome(dynamic.getAuthorId());
                        break;
                    case R.id.tv_like:
                        AppCompatTextView tvLike =
                                (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_like);
                        onLike(dynamic, tvLike);
                        break;
                    case R.id.tv_comment:
                        AppCompatTextView tvComment =
                                (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_comment);
                        onComment(dynamic, tvComment);
                        perform();
                        break;
                    case R.id.tv_share:
                        share();
                        break;
                    default:
                        break;
                }
            } else if (dynamic.getType().equals(DYNAMIC_OUT_TYPE_CONNATE)) {


                switch (view.getId()) {
                    case R.id.tv_trigger:
                        toUserHome(dynamic.getTriggerId());
                        break;
                    case R.id.tv_name:
                    case R.id.iv_star:
                        toStarHome(dynamic.getStarId());
                        break;
                    case R.id.btn_join:
                        AppCompatButton btnJoin =
                                (AppCompatButton) adapter.getViewByPosition(position, R.id.btn_join);
                        AppCompatButton btnMore =
                                (AppCompatButton) adapter.getViewByPosition(position, R.id.btn_more);
                        btnJoin.setVisibility(View.GONE);
                        btnMore.setVisibility(View.VISIBLE);
                        // TODO: 2018/8/7 发送加入星球消息
                        break;
                    case R.id.btn_more:
                        operate();
                        break;
                    case R.id.iv_collect:
                        onCollect((AppCompatImageView) adapter.getViewByPosition(position, R.id.iv_collect));
                        break;
                    case R.id.tv_text:
                        toDynamicHome(dynamic.getDynamicId());
                        break;
                    case R.id.layout_link:
                        toLink(dynamic.getLinkUrl(), dynamic.getLinkTitle());
                        break;
                    case R.id.iv_author:
                    case R.id.tv_author:
                        toUserHome(dynamic.getAuthorId());
                        break;
                    case R.id.tv_like:
                        AppCompatTextView tvLike =
                                (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_like);
                        onLike(dynamic, tvLike);
                        break;
                    case R.id.tv_comment:
                        AppCompatTextView tvComment =
                                (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_comment);
                        onComment(dynamic, tvComment);

                        // TODO: 2018/8/10 待移植
                        perform();
                        break;
                    case R.id.tv_share:
                        share();
                        break;
                    default:
                        break;
                }
            }

        });
    }

    /**
     * 跳转到个人主页主页
     */
    private void toUserHome(String userId, boolean otherView, boolean hasConcern) {
        ARouter.getInstance()
                .build(Paths.PATH_USER_HOME_ACTIVITY)
                .withString(UserActivity.USER_ID, userId)
                .withBoolean(UserActivity.USER_OTHER_VIEW, otherView)
                .withBoolean(UserActivity.USER_HAS_COLLECT, hasConcern)
                .navigation();
    }

    /**
     * 跳转到个人主页主页,（默认参数，自己视角）
     */
    private void toUserHome(String userId) {
        ARouter.getInstance()
                .build(Paths.PATH_USER_HOME_ACTIVITY)
                .withString(UserActivity.USER_ID, userId)
                .withBoolean(UserActivity.USER_OTHER_VIEW, false)
                .withBoolean(UserActivity.USER_HAS_COLLECT, false)
                .navigation();
    }

    /**
     * 跳转到星球主页
     */
    private void toStarHome(String starId) {
        ARouter.getInstance()
                .build(Paths.PATH_STAR_HOME_ACTIVITY)
                .withString(StarActivity.STAR_ID, starId)
                .navigation();
    }

    /**
     * 跳转到动态详情页
     */
    private void toDynamicHome(String dynamicId) {
        ARouter.getInstance()
                .build(Paths.PATH_DYNAMIC_DETAIL_ACTIVITY)
                .withString(DynamicDetailActivity.DYNAMIC_ID, dynamicId)
                .navigation();
    }

    /**
     * 跳转到动态详情页
     */
    private void toLink(String linkUrl, String linkTitle) {
        ARouter.getInstance()
                .build(Paths.PATH_WEBVIEW_ACTIVITY)
                .withString(WebViewActivity.WEBVIEW_URL, linkUrl)
                .withString(WebViewActivity.WEBVIEW_TITLE, linkTitle)
                .navigation();
    }

    private void onCollect(ImageView ivCollect) {
        if (ivCollect.getDrawable().getCurrent().getConstantState() == ContextCompat.getDrawable(getContext(), R.drawable.ic_collect_selected).getConstantState()) {
            ivCollect.setImageResource(R.drawable.ic_collect_normal);
            Toast.makeText(getContext(), "发送取消收藏的消息", Toast.LENGTH_SHORT).show();
            // TODO: 2018/8/12 发送取消收藏消息
        } else {
            ivCollect.setImageResource(R.drawable.ic_collect_selected);
            Toast.makeText(getContext(), "发送添加收藏的消息", Toast.LENGTH_SHORT).show();
            // TODO: 2018/8/12 发送收藏消息
        }
    }

    /**
     * 点赞
     */
    private void onLike(DynamicOut dynamic, TextView textView) {
        int like = dynamic.getLike();
        textView.setCompoundDrawablesWithIntrinsicBounds
                (R.drawable.vector_dynamic_like_selected, 0, 0, 0);
        textView.setText(NumberUtil.parseToK(++like));
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.dynamic_yellow));

        // TODO: 2018/8/7 更新点赞数
    }

    /**
     * 评论
     */
    private void onComment(DynamicOut dynamic, TextView textView) {
        int comment = dynamic.getComment();
        textView.setText(NumberUtil.parseToK(++comment));
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.dynamic_yellow));

        // TODO: 2018/8/7 1.评论页详情；2.发送评论信息，更新评论数
    }

    /**
     * 动态操作：星球外所有人
     */
    private void operate() {
        new MemberDialog(getContext(), true, false)
                .perform(new CommonCallback() {
                    @Override
                    public void onDelete(Dialog dialog) {
                        dialog.dismiss();
                        new JrDialog(getContext())
                                .title("删除")
                                .message("确定要删除这一条动态吗？")
                                .positive((dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    Toast.makeText(getContext(), "删除动态成功", Toast.LENGTH_SHORT).show();
                                })
                                .negative()
                                .show();
                        // TODO: 2018/8/8 发送消息：删除动态
                    }

                    @Override
                    public void onCollect(Dialog dialog) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), "发送消息：收藏动态", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(Dialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }

    /**
     * 动态操作：星球内主理人（测试）
     */
    private void perform() {
        new HostDialog(getContext(), false, false, false, false, true)
                .elite(dialog -> {
                    Toast.makeText(getContext(), "发送消息：加入精华", Toast.LENGTH_SHORT).show();
                    // TODO: 2018/8/8  发送消息：加入精华
                    dialog.dismiss();
                })
                .mute(new MuteCallback() {
                    @Override
                    public void onMuteTemp(Dialog dialog) {
                        dialog.dismiss();
                        new JrDialog(getContext())
                                .title("禁言")
                                .message("确定要将 马云坤 禁言3天吗？")
                                .positive((dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    Toast.makeText(getContext(), "禁言3天成功", Toast.LENGTH_SHORT).show();
                                })
                                .negative()
                                .show();
                        // TODO: 2018/8/8  发送消息：禁言3天
                    }

                    @Override
                    public void onMuteForever(Dialog dialog) {
                        dialog.dismiss();
                        new JrDialog(getContext())
                                .title("禁言")
                                .message("确定要将 马云坤 永久禁言吗？")
                                .positive((dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    Toast.makeText(getContext(), "永久禁言成功", Toast.LENGTH_SHORT).show();
                                })
                                .negative()
                                .show();
                        // TODO: 2018/8/8  发送消息：永久禁言
                    }

                    @Override
                    public void onCancel(Dialog dialog) {
                        // TODO: 2018/8/8  发送消息：解除禁言
                        Toast.makeText(getContext(), "解除禁言成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .concern(dialog -> {
                    Toast.makeText(getContext(), "发送消息：关注作者", Toast.LENGTH_SHORT).show();
                    // TODO: 2018/8/8  发送消息：关注作者
                    dialog.dismiss();
                })
                .common(new CommonCallback() {
                    @Override
                    public void onDelete(Dialog dialog) {
                        Toast.makeText(getContext(), "发送消息：删除动态", Toast.LENGTH_SHORT).show();
                        // TODO: 2018/8/8  发送消息：删除动态
                        dialog.dismiss();
                    }

                    @Override
                    public void onCollect(Dialog dialog) {
                        Toast.makeText(getContext(), "取消收藏成功", Toast.LENGTH_SHORT).show();
                        // TODO: 2018/8/8  发送消息：取消收藏
                        dialog.dismiss();
                    }

                    @Override
                    public void onCancel(Dialog dialog) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void share() {
        new ShareDialog(getContext(), false)
                .setTitle("分享弹窗的标题")
                .setText("分享的具体内容")
                .setUrl("http://www.baidu.com")
                .setImgUrl(Consts.BING_PIC)
                .show();
        // TODO: 2018/8/7 分享功能完善
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new DynamicConcernPresenter(this);
        mPresenter.present();
    }

    @Override
    public void refreshUi(List<DynamicOut> dynamics) {
        mList.clear();
        mList.addAll(dynamics);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return null;
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
}
