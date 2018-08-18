package com.yh.jiran.module.home.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.utils.SoftKeyUtil;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.module.home.HomeMineContract;
import com.yh.jiran.module.home.model.entity.HomeStar;
import com.yh.jiran.module.home.presenter.PickStarPresenter;
import com.yh.jiran.module.home.view.adapter.StarPickQuickAdapter;
import com.yh.jiran.utils.RouterMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author: 闫昊
 * @date: 2018/7/27
 * @function: 星球选择界面
 */
@Route(path = RouterMap.PATH_STAR_PICK_ACTIVITY)
public class StarPickActivity extends ImmerseActivity implements HomeMineContract.PickStarView {

    private HomeMineContract.PickStarPresenter mPresenter;
    private StarPickQuickAdapter mHostAdapter;
    private StarPickQuickAdapter mGuestAdapter;
    private StarPickQuickAdapter mMemberAdapter;
    private StarPickQuickAdapter mResultAdapter;
    private List<HomeStar> mHostList = new ArrayList<>();
    private List<HomeStar> mGuestList = new ArrayList<>();
    private List<HomeStar> mMemberList = new ArrayList<>();
    private List<HomeStar> mResultList = new ArrayList<>();

    @BindView(R.id.edt_search)
    AppCompatEditText edtSearch;
    @BindView(R.id.tv_null)
    AppCompatTextView tvNull;
    @BindView(R.id.list_host)
    RecyclerView listHost;
    @BindView(R.id.list_guest)
    RecyclerView listGuest;
    @BindView(R.id.list_member)
    RecyclerView listMember;
    @BindView(R.id.layout_my_stars)
    LinearLayout layoutMyStars;
    @BindView(R.id.list_result)
    RecyclerView listResult;
    @BindView(R.id.scroll_view)
    NestedScrollView scrollView;
    @BindView(R.id.layout_nav)
    LinearLayout layoutNav;
    @BindView(R.id.tv_cancel)
    AppCompatTextView tvCancel;
    @BindView(R.id.layout_frame)
    FrameLayout layoutFrame;


    @Override
    protected int setContent() {
        return R.layout.activity_star_pick_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        mPresenter = new PickStarPresenter(this);

        LinearLayoutManager hostManager = new LinearLayoutManager(this);
        hostManager.setOrientation(LinearLayoutManager.VERTICAL);
        listHost.setLayoutManager(hostManager);
        mHostAdapter = new StarPickQuickAdapter(mHostList);
        listHost.setAdapter(mHostAdapter);
        listHost.setNestedScrollingEnabled(false);

        LinearLayoutManager guestManager = new LinearLayoutManager(this);
        guestManager.setOrientation(LinearLayoutManager.VERTICAL);
        listGuest.setLayoutManager(guestManager);
        mMemberAdapter = new StarPickQuickAdapter(mMemberList);
        listGuest.setAdapter(mMemberAdapter);
        listGuest.setNestedScrollingEnabled(false);

        LinearLayoutManager memberManager = new LinearLayoutManager(this);
        memberManager.setOrientation(LinearLayoutManager.VERTICAL);
        listMember.setLayoutManager(memberManager);
        mGuestAdapter = new StarPickQuickAdapter(mGuestList);
        listMember.setAdapter(mGuestAdapter);
        listMember.setNestedScrollingEnabled(false);

        LinearLayoutManager resultManager = new LinearLayoutManager(this);
        resultManager.setOrientation(LinearLayoutManager.VERTICAL);
        mResultAdapter = new StarPickQuickAdapter(mResultList);
        listResult.setLayoutManager(resultManager);
        listResult.setAdapter(mResultAdapter);
        listResult.setNestedScrollingEnabled(false);

    }

    @Override
    protected void initData() {
        mHostList.clear();
        mHostList.addAll(mPresenter.getMyStar());
        mGuestList.clear();
        mGuestList.addAll(mPresenter.getRecentStar());
        mMemberList.clear();
        mMemberList.addAll(mPresenter.getHotStar());
        mHostAdapter.notifyDataSetChanged();
        mGuestAdapter.notifyDataSetChanged();
        mMemberAdapter.notifyDataSetChanged();
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

    @OnClick({R.id.iv_back, R.id.edt_search, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.edt_search:
                layoutNav.setVisibility(View.GONE);
                tvCancel.setVisibility(View.VISIBLE);
                layoutFrame.setForeground(new ColorDrawable(0x7F272A33));
                break;
            case R.id.tv_cancel:
                layoutNav.setVisibility(View.VISIBLE);
                tvCancel.setVisibility(View.GONE);
                layoutFrame.setForeground(null);
                SoftKeyUtil.hideSoftKeyboard(this, edtSearch);
                break;
            default:
                break;
        }
    }
}
