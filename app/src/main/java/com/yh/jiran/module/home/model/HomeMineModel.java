package com.yh.jiran.module.home.model;

import com.yh.jiran.greendao.DbManager;
import com.yh.jiran.module.home.HomeMineContract;
import com.yh.jiran.module.home.model.entity.HomeStar;

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
    public List<HomeStar> getLocalData() {
        return null;
    }

    @Override
    public List<HomeStar> getNetData() {
        List<HomeStar> stars = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HomeStar homeStar = new HomeStar();
            homeStar.setHasNew(false);
            homeStar.setOwner("肉王说币");
            homeStar.setStatus("主理人");
            homeStar.setTitle("区块链热点追踪轨迹哈哈哈哈哈哈哈哈");
            HomeStar homeStar1 = new HomeStar();
            homeStar1.setHasNew(true);
            homeStar1.setOwner("股市k神");
            homeStar1.setStatus("嘉宾");
            homeStar1.setTitle("比特币热点信息知晓");
            HomeStar homeStar2 = new HomeStar();
            homeStar2.setHasNew(true);
            homeStar2.setOwner("计然区块链小助手");
            homeStar2.setStatus("成员");
            homeStar2.setTitle("多多公链");
            stars.add(homeStar);
            stars.add(homeStar1);
            stars.add(homeStar2);
        }
        return stars;
    }
}
