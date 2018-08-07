package com.yh.jiran.module.dynamic.model;

import com.yh.jiran.module.dynamic.DynamicConcernContract;
import com.yh.jiran.module.dynamic.model.entity.DynamicOut;
import com.yh.jiran.utils.Consts;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 发现-关注 Model
 */
public class DynamicConcernModel implements DynamicConcernContract.Model {

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public List<DynamicOut> getDynamicData(int page) {
        List<DynamicOut> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            DynamicOut dynamic = new DynamicOut();

            List<String> imgUrls = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                imgUrls.add(Consts.BING_PIC);
            }
            dynamic.setImgUrl(imgUrls);

            dynamic.setType("1");
            dynamic.setTriggerName("可可说币");
            dynamic.setTriggerAction(3);
            dynamic.setImgStarUrl(Consts.BING_PIC);
            dynamic.setStarName("比特币星球啊");
            dynamic.setPublishTime("20分钟前");
            dynamic.setIn(false);
            dynamic.setText("到静安寺浦东嘉搜谱大V分批of较为频繁我欧文IER级外婆oasdioadoia给那送佛前破解破壁机水平的节目评委个回去我诶妇女为偶发VB你误会个人发票个围巾批发日期能不能到货是二表哥垄断且扒肉条跟你说等你离开农村是个我和法国人陪你欧式地回复我IEnew陪你放屁呢【全文你未分配哪位破fine发日后");
            dynamic.setAuthorName("肉王说币");
            dynamic.setImgAuthorUrl(Consts.BING_PIC);
            dynamic.setLike(3532);
            dynamic.setComment(266);
            dynamic.setStarId("ISO314159");

            DynamicOut dynamic2 = new DynamicOut();
            dynamic2.setImgUrl(imgUrls);
            dynamic2.setType("1");
            dynamic2.setTriggerName("可可说币");
            dynamic2.setTriggerAction(5);
            dynamic2.setImgStarUrl(Consts.BING_PIC);
            dynamic2.setStarName("啦啦啦啦啦啦");
            dynamic2.setPublishTime("3天前");
            dynamic2.setIn(true);
            dynamic2.setText("跟你说等你离开农村是个我和法国人陪你欧式地回复我IEnew陪【全文】你未分配哪位破fine发日后");
            dynamic2.setAuthorName("币圈邦德");
            dynamic2.setImgAuthorUrl(Consts.BING_PIC);
            dynamic2.setLike(0);
            dynamic2.setComment(0);
            dynamic2.setStarId("ISO89757");

            list.add(dynamic);
            list.add(dynamic2);
        }
        return list;
    }
}
