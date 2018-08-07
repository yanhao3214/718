package com.yh.jiran.module.dynamic.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseFragment;
import com.yh.core.utils.NumberUtil;
import com.yh.jiran.R;
import com.yh.jiran.module.dynamic.DynamicConcernContract;
import com.yh.jiran.module.dynamic.model.entity.DynamicOut;
import com.yh.jiran.module.dynamic.presenter.DynamicConcernPresenter;
import com.yh.jiran.module.dynamic.view.adapter.DynamicOutAdapter;
import com.yh.jiran.module.home.view.StarActivity;
import com.yh.jiran.module.user.view.UserActivity;
import com.yh.jiran.share.ShareDialog;
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
    private DynamicConcernContract.Presenter mPresenter;
    public static final int DYNAMIC_PAGE_COUNT = 15;
    private int page;
    private DynamicOutAdapter mAdapter;
    private List<DynamicOut> mList = new ArrayList<>();

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

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mAdapter = new DynamicOutAdapter(mList);
        mRecycler.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecycler);

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            DynamicOut dynamic = mList.get(position);
            AppCompatButton btnJoin =
                    (AppCompatButton) adapter.getViewByPosition(position, R.id.btn_join);
            AppCompatButton btnMore =
                    (AppCompatButton) adapter.getViewByPosition(position, R.id.btn_more);
            AppCompatTextView tvLike =
                    (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_like);
            AppCompatTextView tvComment =
                    (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_comment);

            switch (view.getId()) {
                case R.id.tv_trigger:
                    ARouter.getInstance()
                            .build(Paths.PATH_USER_HOME_ACTIVITY)
                            .withString(UserActivity.USER_ACTIVITY_ID, dynamic.getTriggerId())
                            .navigation();
                    break;
                case R.id.iv_star:
                    ARouter.getInstance()
                            .build(Paths.PATH_STAR_HOME_ACTIVITY)
                            .withString(StarActivity.STAR_ACTIVITY_ID, dynamic.getStarId())
                            .navigation();
                    break;
                case R.id.btn_join:
                    btnJoin.setVisibility(View.GONE);
                    btnMore.setVisibility(View.VISIBLE);
                    // TODO: 2018/8/7 发送加入星球消息
                    break;
                case R.id.btn_more:
                    share();
                    break;
                case R.id.tv_text:
                    ARouter.getInstance()
                            .build(Paths.PATH_DYNAMIC_DETAIL_ACTIVITY)
                            .withString(DynamicDetailActivity.DYNAMIC_ACTIVITY_ID,
                                    dynamic.getDynamicId())
                            .navigation();
                    break;
                case R.id.iv_author:
                case R.id.tv_author:
                    ARouter.getInstance()
                            .build(Paths.PATH_USER_HOME_ACTIVITY)
                            .withString(UserActivity.USER_ACTIVITY_ID, dynamic.getCreatorId())
                            .navigation();
                    break;
                case R.id.tv_like:
                    int like = dynamic.getLike();
                    tvLike.setCompoundDrawablesWithIntrinsicBounds
                            (R.drawable.vector_dynamic_like_selected, 0, 0, 0);
                    tvLike.setText(NumberUtil.parseToK(++like));
                    tvLike.setTextColor(ContextCompat.getColor(getContext(), R.color.dynamic_yellow));
                    // TODO: 2018/8/7 更新点赞数

                    break;
                case R.id.tv_comment:
                    int comment = dynamic.getComment();
                    tvComment.setText(NumberUtil.parseToK(++comment));
                    tvComment.setTextColor(ContextCompat.getColor(getContext(), R.color.dynamic_yellow));
                    // TODO: 2018/8/7 1.评论页详情；2.发送评论信息，更新评论数
                    break;
                case R.id.tv_share:
                    share();
                    break;
                default:
                    break;
            }
        });
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
