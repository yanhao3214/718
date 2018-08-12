package com.yh.jiran.module.search.view;

import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.custom.search.SearchCallback;
import com.yh.jiran.custom.search.YSearchView;
import com.yh.jiran.utils.RouterMap;

import butterknife.BindView;

/**
 * @author: 闫昊
 * @date: 2018/7/26
 * @function: 搜索界面
 */
@Route(path = RouterMap.PATH_SEARCH_ACTIVITY)
public class SearchActivity extends ImmerseActivity {

    private BaseFragment fragmentResult;

    @BindView(R.id.search_view)
    YSearchView searchView;

    @Override
    protected int setContent() {
        return R.layout.activity_search_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        fragmentResult = new SearchFragment();
        searchView.setSearchCallback(new SearchCallback() {
            @Override
            public void onSearch(String input) {
                Toast.makeText(SearchActivity.this, "搜索的是：" + input, Toast.LENGTH_SHORT).show();
                // TODO: 2018/7/31 根据关键词搜索，并显示搜索结果Fragment
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_result, fragmentResult);
                transaction.commit();
            }

            @Override
            public void onCancel() {
                finish();
            }

            @Override
            public void onClear() {
                Toast.makeText(SearchActivity.this, "清除了搜索框", Toast.LENGTH_SHORT).show();
                // TODO: 2018/7/31 隐藏搜索结果Fragment
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.remove(fragmentResult);
                transaction.commit();
            }
        });
    }
}
