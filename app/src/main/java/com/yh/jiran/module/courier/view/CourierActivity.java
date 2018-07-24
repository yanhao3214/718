package com.yh.jiran.module.courier.view;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseActivity;
import com.yh.jiran.R;
import com.yh.jiran.module.courier.CourierContract;
import com.yh.jiran.module.courier.model.entity.CourierInfo;
import com.yh.jiran.module.courier.presenter.CourierPresenter;
import com.yh.jiran.module.courier.view.adapter.CourierBaseAdapter;
import com.yh.jiran.utils.Paths;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/7/20
 * @function:
 */
@Route(path = Paths.PATH_COURIER_ACTIVITY)
public class CourierActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, CourierContract.View {
    @BindView(R.id.iv_beauty_1)
    ImageView ivBeauty1;
    private List<CourierInfo> mInfoList = new ArrayList<>();
    private CourierBaseAdapter mCourierAdapter;
    private String company;
    private CourierContract.Presenter mPresenter;

    @BindView(R.id.spinner_company)
    Spinner spinCompany;

    @BindView(R.id.edit_number)
    EditText edtNumber;

    @BindView(R.id.list_info)
    ListView listInfo;

    @Override
    protected int setContent() {
        return R.layout.activity_courier_layout;
    }

    @Override
    protected void initView() {
        Log.d("yh", "CourierActivity: onCreate()");
        super.initView();
        edtNumber.setText("438532650175");
        edtNumber.setSelection(0, edtNumber.getText().toString().length());
        ArrayAdapter mSpinAdapter = ArrayAdapter.createFromResource(this,
                R.array.distribution_company, android.R.layout.simple_spinner_item);
        mSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCompany.setAdapter(mSpinAdapter);
        spinCompany.setOnItemSelectedListener(this);
        spinCompany.setSelection(0, true);

        mCourierAdapter = new CourierBaseAdapter(this, mInfoList);
        listInfo.setAdapter(mCourierAdapter);
        listInfo.setDivider(null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new CourierPresenter(this);
    }

    @Override
    public String getCom() {
        return company;
    }

    @Override
    public String getNumber() {
        return edtNumber.getText().toString().trim();
    }

    @Override
    @OnClick(R.id.btn_query)
    public void submit() {
        mPresenter.query();
    }

    @Override
    public void updateUi(List<CourierInfo> list) {
        mInfoList.clear();
        mInfoList.addAll(list);
        mCourierAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBeauties(List<String> urls) {
        Glide.with(this).load(urls.get(0)).into(ivBeauty1);
    }

    @Override
    public void showBing(String url) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.xxx1)
                .error(R.drawable.xxx2)
                .override(300,300);
        Glide.with(this).load(url).thumbnail(0.1f).apply(options).into(ivBeauty1);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                company = "sf";
                break;
            case 1:
                company = "st";
                break;
            case 2:
                company = "yt";
                break;
            case 3:
                company = "yd";
                break;
            case 4:
                company = "tt";
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /**
     * 绑定RxJava生命周期，再Presenter中调用
     * @return
     */
    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    @Override
    public void showError(int str) {
        Toast.makeText(this, "加载错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "正在加载", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "加载完成", Toast.LENGTH_SHORT).show();
    }
}
