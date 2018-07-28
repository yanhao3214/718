package com.yh.jiran.custom.search;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yh.jiran.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/27
 * @function:
 */
public class YSearchView extends LinearLayout {

    private List<String> mList = new ArrayList<>();
    private SearchCallback mSearchCallback;

    private Context mContext;

    private EditText edtSearch;
    private TextView tvCancel;
    private ListView listHistory;

    public YSearchView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public YSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initAttrs(context, attrs);
        init();
    }

    public YSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
    }

    private void init() {
        initView();
        queryHistory("");

        edtSearch.setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                String input = edtSearch.getText().toString().trim();
                if (mSearchCallback != null) {
                    mSearchCallback.onSearch(input);
                }
                if (!hasData(input)) {
                    insertHistory(input);
                    queryHistory(input);
                }
            }
            return false;
        });

        listHistory.setOnItemClickListener((adapterView, view, i, l) -> {
            String history = mList.get(i);
            edtSearch.setText(history);
        });
    }


    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.custom_search_layout, this);
        edtSearch = findViewById(R.id.edt_search);
        tvCancel = findViewById(R.id.tv_cancel);
        listHistory = findViewById(R.id.list_history);
    }

    private boolean hasData(String input) {
        return false;
    }

    private void queryHistory(String s) {
    }

    private void insertHistory(String input) {

    }

}
