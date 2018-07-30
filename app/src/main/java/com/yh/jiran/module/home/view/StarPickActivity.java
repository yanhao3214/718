package com.yh.jiran.module.home.view;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.custom.ScrollerListView;
import com.yh.jiran.module.home.HomeMineContract;
import com.yh.jiran.module.home.model.entity.HomeStar;
import com.yh.jiran.module.home.presenter.PickStarPresenter;
import com.yh.jiran.module.home.view.adapter.StarPickAdapter;
import com.yh.jiran.module.home.view.adapter.StarPickQuickAdapter;
import com.yh.jiran.utils.Paths;
import com.yh.jiran.utils.UiHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author: 闫昊
 * @date: 2018/7/27
 * @function:
 */
@Route(path = Paths.PATH_STAR_PICK_ACTIVITY)
public class StarPickActivity extends ImmerseActivity implements HomeMineContract.PickStarView {

    private HomeMineContract.PickStarPresenter mPresenter;
    private StarPickQuickAdapter mMineAdapter;
    private StarPickQuickAdapter mRecentAdapter;
    private StarPickQuickAdapter mHotAdapter;
    private List<HomeStar> mMineList = new ArrayList<>();
    private List<HomeStar> mRecentList = new ArrayList<>();
    private List<HomeStar> mHotList = new ArrayList<>();

    @BindView(R.id.edt_search)
    AppCompatEditText edtSearch;
    @BindView(R.id.list_mine)
    RecyclerView listMine;
    @BindView(R.id.list_recent)
    RecyclerView listRecent;
    @BindView(R.id.list_hot)
    RecyclerView listHot;
    @BindView(R.id.layout_stars)
    LinearLayout layoutStars;

    @Override
    protected int setContent() {
        return R.layout.activity_star_pick_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        mPresenter = new PickStarPresenter(this);

        LinearLayoutManager mineManager = new LinearLayoutManager(this);
        mineManager.setOrientation(LinearLayoutManager.VERTICAL);
        listMine.setLayoutManager(mineManager);
        mMineAdapter = new StarPickQuickAdapter(mMineList);
        listMine.setAdapter(mMineAdapter);
        listMine.setNestedScrollingEnabled(false);

        LinearLayoutManager hotManager = new LinearLayoutManager(this);
        hotManager.setOrientation(LinearLayoutManager.VERTICAL);
        listHot.setLayoutManager(hotManager);
        mHotAdapter = new StarPickQuickAdapter(mHotList);
        listHot.setAdapter(mHotAdapter);
        listHot.setNestedScrollingEnabled(false);

        LinearLayoutManager recentManager = new LinearLayoutManager(this);
        recentManager.setOrientation(LinearLayoutManager.VERTICAL);
        listRecent.setLayoutManager(recentManager);
        mRecentAdapter = new StarPickQuickAdapter(mRecentList);
        listRecent.setAdapter(mRecentAdapter);
        listRecent.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        mMineList.clear();
        mMineList.addAll(mPresenter.getMyStar());
        mRecentList.clear();
        mRecentList.addAll(mPresenter.getRecentStar());
        mHotList.clear();
        mHotList.addAll(mPresenter.getHotStar());
        mMineAdapter.notifyDataSetChanged();
        mRecentAdapter.notifyDataSetChanged();
        mHotAdapter.notifyDataSetChanged();
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

    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        finish();
    }
}
