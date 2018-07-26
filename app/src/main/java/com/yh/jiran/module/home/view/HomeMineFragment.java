package com.yh.jiran.module.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.module.home.HomeMineContract;
import com.yh.jiran.module.home.model.entity.MineStar;
import com.yh.jiran.module.home.presenter.HomeMinePresenter;
import com.yh.jiran.module.home.view.adapter.StarGridAdapter;
import com.yh.jiran.utils.Paths;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * @author: 闫昊
 * @date: 2018/7/25
 * @function:
 */
public class HomeMineFragment extends BaseFragment implements HomeMineContract.View {

    private List<MineStar> mStars = new ArrayList<>();
    private HomeMineContract.Presenter mHomeMinePresenter;
    private StarGridAdapter mStarGridAdapter;

    @BindView(R.id.edt_search)
    AppCompatEditText edtSearch;
    @BindView(R.id.grid_star)
    GridView gridStar;

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
        mStars.clear();
        mStars.addAll(mHomeMinePresenter.upDateStars());
        mStarGridAdapter.notifyDataSetChanged();
    }

    @OnItemSelected(R.id.grid_star)
    void onStarClick() {
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        mStarGridAdapter = new StarGridAdapter(getContext(), mStars);
        gridStar.setAdapter(mStarGridAdapter);
        gridStar.setOnItemClickListener((adapterView, view, i, l) ->
                ARouter.getInstance()
                        .build(Paths.PATH_STAR_ACTIVITY)
                        .withLong("starId", mStars.get(i).getStarId())
                        .withString("starTitle", mStars.get(i).getTitle())
                        .navigation());
    }

    @Override
    public void refreshUi(List<MineStar> stars) {
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
