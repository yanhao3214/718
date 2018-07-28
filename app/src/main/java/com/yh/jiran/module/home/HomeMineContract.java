package com.yh.jiran.module.home;

import com.yh.jiran.base.contract.IModel;
import com.yh.jiran.base.contract.IPresenter;
import com.yh.jiran.base.contract.IView;
import com.yh.jiran.module.home.model.entity.HomeStar;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public interface HomeMineContract {

    interface Model extends IModel {

        /**
         * 获取本地数据
         *
         * @return
         */
        List<HomeStar> getLocalData();

        /**
         * 获取服务器数据
         *
         * @return
         */
        List<HomeStar> getNetData();
    }

    interface PickStarModel extends IModel {

        /**
         * 获取数据：我的星球
         *
         * @return
         */
        List<HomeStar> getMyStar();

        /**
         * 获取数据：最近使用
         *
         * @return
         */
        List<HomeStar> getRecentStar();

        /**
         * 获取数据：热门星球
         *
         * @return
         */
        List<HomeStar> getHotStar();
    }

    interface View extends IView {

        /**
         * 更新界面
         */
        void refreshUi(List<HomeStar> stars);
    }

    interface PickStarView extends IView {

    }

    interface Presenter extends IPresenter {

        /**
         * 更新数据
         *
         * @return
         */
        List<HomeStar> upDateStars();

        /**
         * 跳转到搜索页面
         */
        void jumpToSearch();
    }

    interface PickStarPresenter extends IPresenter {
        /**
         * 获取数据：我的星球
         *
         * @return
         */
        List<HomeStar> getMyStar();

        /**
         * 获取数据：最近使用
         *
         * @return
         */
        List<HomeStar> getRecentStar();

        /**
         * 获取数据：热门星球
         *
         * @return
         */
        List<HomeStar> getHotStar();
    }
}
