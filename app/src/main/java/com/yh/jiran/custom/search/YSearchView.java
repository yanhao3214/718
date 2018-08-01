package com.yh.jiran.custom.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yh.core.utils.SoftKeyUtil;
import com.yh.jiran.R;
import com.yh.jiran.greendao.DbManager;
import com.yh.jiran.greendao.SearchHistoryDao;
import com.yh.jiran.module.home.model.entity.SearchHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * @author: 闫昊
 * @date: 2018/7/27
 * @function: 自定义搜索控件，包含历史记录
 */
public class YSearchView extends LinearLayout {

    private Context mContext;
    private SearchHistoryDao mDao;
    private List<SearchHistory> mDatas = new ArrayList<>();
    private SearchCallback mSearchCallback;

    private RecyclerView listHistory;
    private SearchHistoryAdapter mAdapter;
    private View clearFooter;
    private YSearchEdit edtSearch;
    private AppCompatTextView tvCancel;
    private AppCompatTextView tvClear;


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

    @SuppressWarnings("AlibabaMethodTooLong")
    @SuppressLint("CheckResult")
    private void init() {
        initView();
        mDao = DbManager.getInstance().getSearchHistoryDao();
        queryHistory("");

        clearFooter.setOnClickListener(view -> {
            clearHistory();
            queryHistory("");
        });

        /**
         * 设置搜索框清除按钮点击回调
         */
        edtSearch.setClearCallback(() -> {
            mSearchCallback.onClear();
            listHistory.setVisibility(VISIBLE);
        });

        /**
         * 软键盘搜索键监听
         */
        edtSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                Toast.makeText(mContext, "onEditorAction生效", Toast.LENGTH_SHORT).show();
                SoftKeyUtil.hideSoftKeyboard(edtSearch.getContext(), edtSearch);
                String input = edtSearch.getText().toString().trim();
                if (!hasData(input)) {
                    insertHistory(input);
                    queryHistory("");
                }
                search(input);
                return true;
            }
            return false;
        });

        /**
         * 实时监听搜索框的输入内容，无响应延迟
         * 根据输入内容查询数据库，模糊搜索，并显示结果
         */
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            final TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    emitter.onNext(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            };
            edtSearch.addTextChangedListener(textWatcher);
            emitter.setCancellable(() -> edtSearch.removeTextChangedListener(textWatcher));
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (TextUtils.isEmpty(s)) {
                        mSearchCallback.onClear();
                        listHistory.setVisibility(VISIBLE);
                    }
                    queryHistory(s);
                });

        /**
         * 删除搜索历史单条记录
         */
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            mDao.delete(mDatas.get(position));
            mDatas.remove(position);
            mAdapter.notifyDataSetChanged();
            clearFooter.setVisibility(mDatas.size() > 0 ? VISIBLE : GONE);
        });

        /**
         * 历史列表item点击事件：
         * 内容自动填充到输入框、隐藏输入法、执行回调函数onSearch()
         */
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            String key = mDatas.get(position).getContent();
            edtSearch.setText(key);
            edtSearch.setSelection(0, key.length());
            SoftKeyUtil.hideSoftKeyboard(mContext, edtSearch);
            search(key);
        });

        /**
         * 点击“取消”的回调事件
         */
        tvCancel.setOnClickListener((view -> {
            if (mSearchCallback != null) {
                mSearchCallback.onCancel();
            }
        }));
    }

    @SuppressLint("InflateParams")
    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.custom_search_layout, this);
        edtSearch = findViewById(R.id.edt_search);
        tvCancel = findViewById(R.id.tv_cancel);
        listHistory = findViewById(R.id.list_history);

        mAdapter = new SearchHistoryAdapter(mDatas);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listHistory.setLayoutManager(manager);
        listHistory.setAdapter(mAdapter);
        listHistory.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

        clearFooter = LayoutInflater.from(mContext).inflate(R.layout.footer_star_history_layout, null);
        mAdapter.addFooterView(clearFooter);
    }

    /**
     * 开始搜索
     *
     * @param input 搜索关键词
     */
    private void search(String input) {
        if (mSearchCallback != null) {
            mSearchCallback.onSearch(input);
        }
        listHistory.setVisibility(GONE);
    }

    /**
     * 模糊查询
     */
    private void queryHistory(String s) {
        List<SearchHistory> tempList = mDao.queryBuilder()
                .where(SearchHistoryDao.Properties.Content.like("%" + s + "%"))
                .orderDesc(SearchHistoryDao.Properties.HistoryId)
                .list();
        mDatas.clear();
        mDatas.addAll(tempList);
        mAdapter.notifyDataSetChanged();
        clearFooter.setVisibility(TextUtils.isEmpty(s) && mDatas.size() > 0 ? VISIBLE : GONE);
    }

    /**
     * 清空历史记录
     */
    private void clearHistory() {
        mDao.deleteAll();
        clearFooter.setVisibility(GONE);
        mDatas.clear();
        mAdapter.notifyDataSetChanged();
    }

    private void insertHistory(String input) {
        SearchHistory history = new SearchHistory();
        history.setContent(input);
        mDao.insert(history);
    }

    private boolean hasData(String input) {
        List<SearchHistory> tempList = mDao.queryRaw("where CONTENT=?", input);
        return tempList.size() > 0;
    }

    public void setSearchCallback(SearchCallback callback) {
        mSearchCallback = callback;
    }
}
