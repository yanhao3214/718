package com.yh.jiran.module.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.module.home.model.entity.HotStar;
import com.yh.jiran.module.home.view.adapter.DiscoverStarQuickAdapter;

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
public class HomeDiscoverFragment extends BaseFragment {
    private DiscoverStarQuickAdapter mStarAdapter;
    private List<HotStar> mStarList = new ArrayList<>();

    @BindView(R.id.list_hot)
    RecyclerView listHot;
    @BindView(R.id.list_dynamic)
    RecyclerView listDynamic;

    @Override
    protected Object setContent() {
        return R.layout.fragment_home_discover_layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home_discover_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        LinearLayoutManager starManager = new LinearLayoutManager(getContext());
        starManager.setOrientation(LinearLayoutManager.VERTICAL);
        listHot.setLayoutManager(starManager);
        mStarAdapter = new DiscoverStarQuickAdapter(mStarList);
        listHot.setAdapter(mStarAdapter);

        mStarAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // TODO: 2018/8/2 跳转到星球详情页
            }
        });
        mStarAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                HotStar hotStar = mStarList.get(position);
                if (!hotStar.isIn()) {
//                    (AppCompatTextView)view.setBackgroundResource(R.drawable.bg_discover_join_done);
//                    view.setText
                    hotStar.setIn(true);
                    // TODO: 2018/8/2 发送加入该星球的消息--->to server

                }
            }
        });
    }

    @OnClick(R.id.tv_change)
    public void onViewClicked() {
        // TODO: 2018/8/2 更新数据：热门星球
    }
}
