package com.yh.jiran.mvp.contract;

import com.yh.jiran.mvp.contract.base.IModel;
import com.yh.jiran.mvp.contract.base.IPresenter;
import com.yh.jiran.mvp.contract.base.IView;
import com.yh.jiran.mvp.model.entity.CourierInfo;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/20
 * @function: Courier契约类，集中管理相关接口
 */
public interface CourierContract {

    interface View extends IView {

        /**
         * 获取快递公司信息
         *
         * @return 快递公司代号
         */
        String getCom();

        /**
         * 获取快递单号
         *
         * @return 快递单号
         */
        String getNumber();

        /**
         * 提交数据请求申请
         */
        void submit();

        /**
         * 显示查询结果
         *
         * @param list 结果列表
         */
        void updateUi(List<CourierInfo> list);

        /**
         * 加载图片
         *
         * @param urls
         */
        void showBeauties(List<String> urls);

        /**
         * 加载必应图片
         *
         * @param url
         */
        void showBing(String url);
    }

    interface Model extends IModel<List<CourierInfo>> {

        /**
         * 获取数据
         *
         * @param com
         * @param num
         * @return
         */
        List<CourierInfo> getDatas(String com, String num);
    }

    interface Presenter extends IPresenter {

        /**
         * 查询快递
         */
        void query();
    }
}
