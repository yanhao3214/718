package com.yh.jiran.module.dynamic;

import com.yh.jiran.base.contract.IModel;
import com.yh.jiran.base.contract.IPresenter;
import com.yh.jiran.base.contract.IView;
import com.yh.jiran.module.dynamic.model.entity.DynamicOut;
import com.yh.jiran.module.home.model.entity.HotStar;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 动态-关注：MVP契约
 */
public interface DynamicConcernContract {

    interface View extends IView {

        /**
         * 刷新热门星球UI
         */
        void refreshUi(List<DynamicOut> dynamics);

    }

    interface Model extends IModel {

        /**
         * 获取热门动态数据
         */
        List<DynamicOut> getDynamicData(int page);
    }

    interface Presenter extends IPresenter {

        /**
         * 初始化
         */
        void present();

        /**
         * 更新热门动态
         */
        void updateDynamic(int page);

        /**
         * 发送已加入
         */
        void sendJoin();
    }
}
