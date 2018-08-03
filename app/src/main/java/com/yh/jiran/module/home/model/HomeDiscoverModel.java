package com.yh.jiran.module.home.model;

import com.yh.jiran.module.home.HomeDiscoverContract;
import com.yh.jiran.module.home.model.entity.HotStar;
import com.yh.jiran.utils.Consts;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/3
 * @function: 首页-发现：Model
 */
public class HomeDiscoverModel implements HomeDiscoverContract.Model {
    public static final int DISCOVER_STAR_PAGE_COUNT = 5;

    @Override
    public List<HotStar> getHotStarData(int page) {
        // TODO: 2018/8/3 获取热门星球数据
        List<HotStar> oddStars = new ArrayList<>();
        List<HotStar> evenStars = new ArrayList<>();
        for (int i = 0; i < DISCOVER_STAR_PAGE_COUNT - 1; i++) {
            HotStar hotStar = new HotStar();
            hotStar.setIn(false);
            hotStar.setImgUrl(Consts.BING_PIC);
            hotStar.setMember(3333);
            hotStar.setName("计然多多多多多多多多多多");
            hotStar.setId((long) 1001);
            oddStars.add(hotStar);
        }
        HotStar hotStar1 = new HotStar();
        hotStar1.setIn(true);
        hotStar1.setImgUrl(Consts.BING_PIC);
        hotStar1.setMember(563548);
        hotStar1.setName("计然多多");
        hotStar1.setId((long) 1001);
        oddStars.add(hotStar1);
        for (int i = 0; i < DISCOVER_STAR_PAGE_COUNT - 1; i++) {
            HotStar hotStar = new HotStar();
            hotStar.setIn(true);
            hotStar.setImgUrl(Consts.BING_PIC);
            hotStar.setMember(333);
            hotStar.setName("比特星球哈哈哈");
            hotStar.setId((long) 1002);
            evenStars.add(hotStar);
        }
        HotStar hotStar2 = new HotStar();
        hotStar2.setIn(false);
        hotStar2.setImgUrl(Consts.BING_PIC);
        hotStar2.setMember(563548);
        hotStar2.setName("计然多多");
        hotStar2.setId((long) 1003);
        evenStars.add(hotStar2);
        return page % 2 == 1 ? oddStars : evenStars;
    }

    @Override
    public void getDynamicData() {

    }

    @Override
    public Object getData() {
        return null;
    }
}
