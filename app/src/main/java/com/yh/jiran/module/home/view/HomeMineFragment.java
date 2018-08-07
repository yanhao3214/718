package com.yh.jiran.module.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseFragment;
import com.yh.core.utils.SoftKeyUtil;
import com.yh.jiran.R;
import com.yh.jiran.module.home.HomeMineContract;
import com.yh.jiran.module.home.model.entity.HomeStar;
import com.yh.jiran.module.home.presenter.HomeMinePresenter;
import com.yh.jiran.module.home.view.adapter.StarGridAdapter;
import com.yh.jiran.module.home.view.adapter.StarGridQuickAdapter;
import com.yh.jiran.utils.Paths;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/7/25
 * @function: 首页-我的
 */
public class HomeMineFragment extends BaseFragment implements HomeMineContract.View {

    private List<HomeStar> mStars = new ArrayList<>();
    private HomeMineContract.Presenter mHomeMinePresenter;
    private StarGridAdapter mStarGridAdapter;

    @BindView(R.id.edt_search)
    AppCompatEditText edtSearch;
    @BindView(R.id.grid_star)
    GridView gridStar;
    @BindView(R.id.swipe_refresh_star)
    SwipeRefreshLayout refreshStar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home_mine_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @Override
    protected Object setContent() {
        return R.layout.fragment_home_mine_layout;
    }

    @Override
    protected void initData() {
        mHomeMinePresenter = new HomeMinePresenter(this);
        refreshGridView();
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        edtSearch.setFocusable(false);
        mStarGridAdapter = new StarGridAdapter(getContext(), mStars);
        gridStar.setAdapter(mStarGridAdapter);

        refreshStar.setOnRefreshListener(() -> {
            refreshGridView();
            refreshStar.setRefreshing(false);
        });

        gridStar.setOnItemClickListener((adapterView, view, i, l) -> {
            HomeStar star = mStars.get(i);
            toStarDetail(star);
        });
    }

    /**
     * 刷新星球列表
     */
    private void refreshGridView() {
        mStars.clear();
        mStars.addAll(mHomeMinePresenter.upDateStars());
        mStarGridAdapter.notifyDataSetChanged();
    }

    /**
     * 跳转至星球详情页面
     */
    private void toStarDetail(HomeStar star) {
        ARouter.getInstance()
                .build(Paths.PATH_STAR_ACTIVITY)
                .withLong("starId", star.getStarId())
                .withString("starTitle", star.getTitle())
                .navigation(getContext(), new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        star.setHasNew(false);
                        mStarGridAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void refreshUi(List<HomeStar> stars) {
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

    @OnClick(R.id.edt_search)
    public void search() {
        mHomeMinePresenter.jumpToSearch();
    }
}
