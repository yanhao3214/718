package com.yh.jiran.module.home.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.utils.Paths;

/**
 * @author: 闫昊
 * @date: 2018/7/26
 * @function: 搜索界面
 */
@Route(path = Paths.PATH_SEARCH_ACTIVITY)
public class SearchActivity extends ImmerseActivity {
    @Override
    protected int setContent() {
        return R.layout.activity_search_layout;
    }
}
