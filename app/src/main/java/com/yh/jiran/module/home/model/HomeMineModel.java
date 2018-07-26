package com.yh.jiran.module.home.model;

import com.yh.jiran.module.home.HomeMineContract;
import com.yh.jiran.module.home.model.entity.MineStar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public class HomeMineModel implements HomeMineContract.Model {
    @Override
    public Object getData() {
        return null;
    }

    @Override
    public List<MineStar> getLocalData() {
        return null;
    }

    @Override
    public List<MineStar> getNetData() {
        List<MineStar> stars = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MineStar mineStar = new MineStar();
            mineStar.setHasNew(false);
            mineStar.setOwner("肉王说币");
            mineStar.setStatus("主理人");
            mineStar.setTitle("区块链热点追踪轨迹哈哈哈哈哈哈哈哈");
            MineStar mineStar1 = new MineStar();
            mineStar1.setHasNew(true);
            mineStar1.setOwner("股市k神");
            mineStar1.setStatus("嘉宾");
            mineStar1.setTitle("比特币热点信息知晓");
            MineStar mineStar2 = new MineStar();
            mineStar2.setHasNew(true);
            mineStar2.setOwner("计然区块链小助手");
            mineStar2.setStatus("成员");
            mineStar2.setTitle("多多公链");
            stars.add(mineStar);
            stars.add(mineStar1);
            stars.add(mineStar2);
        }
        return stars;
    }
}
