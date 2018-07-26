package com.yh.jiran.module.home;

import com.yh.jiran.base.contract.IModel;
import com.yh.jiran.base.contract.IPresenter;
import com.yh.jiran.base.contract.IView;
import com.yh.jiran.module.home.model.entity.MineStar;

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
        List<MineStar> getLocalData();

        /**
         * 获取服务器数据
         *
         * @return
         */
        List<MineStar> getNetData();
    }

    interface View extends IView {

        /**
         * 更新界面
         */
        void refreshUi(List<MineStar> stars);
    }

    interface Presenter extends IPresenter {

        /**
         * 更新数据
         */
        List<MineStar> upDateStars();

        /**
         * 跳转到搜索页面
         */
        void jumpToSearch();
    }
}
