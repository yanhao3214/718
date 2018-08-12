package com.yh.jiran.module.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.module.home.HomeDiscoverContract;
import com.yh.jiran.module.home.model.entity.HotStar;
import com.yh.jiran.module.home.presenter.HomeDiscoverPresenter;
import com.yh.jiran.module.home.view.adapter.DiscoverStarQuickAdapter;
import com.yh.jiran.utils.RouterMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/7/25
 * @function: 首页-发现
 */
public class HomeDiscoverFragment extends BaseFragment implements HomeDiscoverContract.View {
    private HomeDiscoverContract.Presenter mPresenter;
    private DiscoverStarQuickAdapter mStarAdapter;
    private List<HotStar> mStarList = new ArrayList<>();
    private List mDynamicList = new ArrayList();
    private int mStarPage = 1;
    public static final int DISCOVER_STAR_PAGE_TOTAL = 10;

    @BindView(R.id.list_star)
    RecyclerView recyclerStar;
    @BindView(R.id.list_dynamic)
    RecyclerView listDynamic;

    @Override
    protected Object setContent() {
        return R.layout.fragment_home_discover_layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home_discover_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        LinearLayoutManager starManager = new LinearLayoutManager(getContext());
        starManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerStar.setLayoutManager(starManager);
        mStarAdapter = new DiscoverStarQuickAdapter(mStarList);
        recyclerStar.setAdapter(mStarAdapter);
        recyclerStar.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mStarAdapter.setOnItemClickListener((adapter, view, position) -> {
            // TODO: 2018/8/2 跳转到星球详情页
            ARouter.getInstance()
                    .build(RouterMap.PATH_STAR_HOME_ACTIVITY)
                    .withLong(StarActivity.STAR_ID, mStarList.get(position).getId())
                    .navigation();
        });
        mStarAdapter.bindToRecyclerView(recyclerStar);
        mStarAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HotStar hotStar = mStarList.get(position);
            AppCompatButton btnCancel = (AppCompatButton) adapter.
                    getViewByPosition(position, R.id.btn_join);
            if (!hotStar.isIn()) {
                btnCancel.setBackgroundResource(R.drawable.bg_discover_join_done);
                btnCancel.setText("已加入");
                btnCancel.setTextColor(ContextCompat.getColor(getContext(), R.color.textDark3));
                hotStar.setIn(true);
                // TODO: 2018/8/2 发送加入该星球的消息--->to server
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter = new HomeDiscoverPresenter(this);
        mPresenter.updateStar(mStarPage);
    }

    @OnClick(R.id.tv_change)
    public void onViewClicked() {
        // TODO: 2018/8/2 更新数据：热门星球
        if (mStarPage >= DISCOVER_STAR_PAGE_TOTAL) {
            mStarPage -= DISCOVER_STAR_PAGE_TOTAL;
        }
        mPresenter.updateStar(++mStarPage);
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
    public void refreshStar(List<HotStar> stars) {
        mStarList.clear();
        mStarList.addAll(stars);
        mStarAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshDynamic() {

    }
}
