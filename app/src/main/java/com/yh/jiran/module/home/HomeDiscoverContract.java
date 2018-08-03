package com.yh.jiran.module.home;

import com.yh.jiran.base.contract.IModel;
import com.yh.jiran.base.contract.IPresenter;
import com.yh.jiran.base.contract.IView;
import com.yh.jiran.module.home.model.entity.HotStar;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 首页-发现：MVP契约
 */
public interface HomeDiscoverContract {

    interface View extends IView {

        /**
         * 刷新热门星球UI
         */
        void refreshStar(List<HotStar> stars);

        /**
         * 刷新热门动态UI
         */
        void refreshDynamic();
    }

    interface Model extends IModel {

        /**
         * 获取热门星球数据
         */
        List<HotStar> getHotStarData(int page);

        /**
         * 获取热门动态数据
         */
        void getDynamicData();
    }

    interface Presenter extends IPresenter {

        /**
         * 更新热门星球
         */
        void updateStar(int page);

        /**
         * 更新热门动态
         */
        void updateDynamic();

        /**
         * 发送已加入
         */
        void sendJoin();
    }
}
