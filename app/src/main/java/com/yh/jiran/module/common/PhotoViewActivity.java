package com.yh.jiran.module.common;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.utils.Paths;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 大图浏览页面
 */
@Route(path = Paths.PATH_PHOTO_VIEW_ACTIVITY)
public class PhotoViewActivity extends ImmerseActivity {

    public static final String PHOTO_VIEW_URLS = "photo_view_urls";
    public static final String PHOTO_VIEW_POSITION = "photo_view_position";
    @BindView(R.id.view_pager_photo_view)
    ViewPager mViewPager;

    @Override
    protected int setContent() {
        return R.layout.activity_photo_view_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        List<String> urls = getIntent().getStringArrayListExtra(PHOTO_VIEW_URLS);
        int position = getIntent().getIntExtra(PHOTO_VIEW_POSITION, 0);
        mViewPager = findViewById(R.id.view_pager_photo_view);
        mViewPager.setAdapter(new PhotoViewPagerAdapter(this, urls));
        mViewPager.setCurrentItem(position);
    }
}
