package com.yh.jiran.module.home.model;

import com.yh.jiran.module.home.HomeMineContract;
import com.yh.jiran.module.home.model.entity.HomeStar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/27
 * @function:
 */
public class PickStarModel implements HomeMineContract.PickStarModel {
    private List<HomeStar> mStarList = new ArrayList<>();

    @Override
    public List<HomeStar> getMyStar() {
        mStarList.clear();
        for (int i = 0; i < 2; i++) {
            HomeStar homeStar = new HomeStar();
            homeStar.setHasNew(false);
            homeStar.setOwner("区块链安全直播赵伟专场");
            homeStar.setStatus("主理人");
            homeStar.setTitle("对话以太坊ETH");
            HomeStar homeStar2 = new HomeStar();
            homeStar2.setHasNew(true);
            homeStar2.setOwner("计然区块链小助手");
            homeStar2.setStatus("成员");
            homeStar2.setTitle("多多公链");
            mStarList.add(homeStar);
            mStarList.add(homeStar2);
        }
        return mStarList;
    }

    @Override
    public List<HomeStar> getRecentStar() {
        mStarList.clear();
        for (int i = 0; i < 2; i++) {
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
            mStarList.add(homeStar1);
            mStarList.add(homeStar2);
        }
        return mStarList;
    }

    @Override
    public List<HomeStar> getHotStar() {
        mStarList.clear();
        for (int i = 0; i < 12; i++) {
            HomeStar homeStar2 = new HomeStar();
            homeStar2.setHasNew(true);
            homeStar2.setOwner("计然区块链小助手");
            homeStar2.setStatus("成员");
            homeStar2.setTitle("多多公链");
            HomeStar homeStar = new HomeStar();
            homeStar.setHasNew(false);
            homeStar.setOwner("区块链安全直播赵伟专场");
            homeStar.setStatus("主理人");
            homeStar.setTitle("对话以太坊ETH");
            mStarList.add(homeStar2);
            mStarList.add(homeStar);
        }
        return mStarList;
    }

    @Override
    public Object getData() {
        return null;
    }
}
