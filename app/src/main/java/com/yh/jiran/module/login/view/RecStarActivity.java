package com.yh.jiran.module.login.view;

import android.support.v7.widget.AppCompatButton;
import android.widget.GridView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.module.home.model.entity.HotStar;
import com.yh.jiran.module.login.LoginContract;
import com.yh.jiran.module.login.presenter.RecPresenter;
import com.yh.jiran.module.login.view.adapter.LoginRecGridAdapter;
import com.yh.jiran.utils.Paths;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/8/3
 * @function: 推荐星球页
 */
@Route(path = Paths.PATH_REC_STAR_ACTIVITY)
public class RecStarActivity extends ImmerseActivity implements LoginContract.RecView {

    private LoginContract.RecPresenter mPresenter;
    private LoginRecGridAdapter mAdapter;
    private List<HotStar> mStarList = new ArrayList<>();

    @BindView(R.id.grid_rec_star)
    GridView gridRecStar;
    @BindView(R.id.btn_enter)
    AppCompatButton btnEnter;

    @Override
    protected int setContent() {
        return R.layout.activity_rec_star_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        mPresenter = new RecPresenter(this);
        mAdapter = new LoginRecGridAdapter(this, mStarList);
        gridRecStar.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.present(1);
        Toast.makeText(this, "推荐个数： " + mStarList.size(), Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.btn_enter)
    public void onViewClicked() {
        ARouter.getInstance().build(Paths.PATH_HOME_ACTIVITY).navigation();
        finish();
    }

    @Override
    public void refreshRecs(List<HotStar> stars) {
        mStarList.clear();
        mStarList.addAll(stars);
        mAdapter.notifyDataSetChanged();
    }
}
