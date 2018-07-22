package com.yh.jiran.mvp.model;

import com.yh.jiran.mvp.contract.CourierContract;
import com.yh.jiran.mvp.model.entity.CourierInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/20
 * @function:
 */
public class CourierModel implements CourierContract.Model {

    @Override
    public List<CourierInfo> getData() {
        return null;
    }

    @Override
    public List<CourierInfo> getDatas(String com, String num) {
        List<CourierInfo> list = new ArrayList<>();
        CourierInfo info = new CourierInfo();
        info.setCom(com);
        info.setDatetime("2018-7-20 14:50");
        info.setRemark("数据获取中");
        info.setRemark("杭州西湖区古荡".concat(num));
        for (int i = 0; i < 15; i++) {
            list.add(info);
        }
        return list;
    }
}

