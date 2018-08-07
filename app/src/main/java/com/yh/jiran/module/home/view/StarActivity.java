package com.yh.jiran.module.home.view;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseActivity;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.module.home.HomeMineContract;
import com.yh.jiran.module.home.model.entity.HomeStar;
import com.yh.jiran.utils.Paths;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 星球页面
 */
@Route(path = Paths.PATH_STAR_HOME_ACTIVITY)
public class StarActivity extends ImmerseActivity implements HomeMineContract.View {
    public static final String STAR_ACTIVITY_ID = "star_id";

    @BindView(R.id.tv_star_detail)
    AppCompatTextView tvStarDetail;

    @Override
    protected int setContent() {
        return R.layout.activity_star_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle bundle = getIntent().getExtras();
        tvStarDetail.setText("星球ID：".concat(bundle.getString(STAR_ACTIVITY_ID)));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
