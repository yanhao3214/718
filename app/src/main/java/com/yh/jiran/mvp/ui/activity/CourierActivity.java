package com.yh.jiran.mvp.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.yh.core.app.BaseActivity;
import com.yh.jiran.R;
import com.yh.jiran.mvp.presenter.CourierPresenter;
import com.yh.jiran.mvp.ui.adapter.CourierBaseAdapter;
import com.yh.jiran.mvp.contact.CourierContract;
import com.yh.jiran.mvp.model.entity.CourierInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/7/20
 * @function:
 */
public class CourierActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, CourierContract.View {
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

    @Override
    public void showError(int str) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}