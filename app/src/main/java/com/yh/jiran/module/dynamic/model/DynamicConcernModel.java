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

            /**
             * 有头部、多图或无图、无链接
             */
            dynamic.setImgUrl(imgUrls);
            dynamic.setType("1");
            dynamic.setTriggerName("可可说币");
            dynamic.setTriggerAction(3);
            dynamic.setImgStarUrl(Consts.BING_PIC);
            dynamic.setStarName("比特币星球啊");
            dynamic.setPublishTime("10分钟前");
            dynamic.setIn(false);
            dynamic.setText("到https://www.baidu.com/静安寺分批https://www.youku.com/浦东卢卡斯嘉https://www.baidu.com/佛前破解破https://www.baidu.com/目评委个回去搜谱大V我诶妇女为偶发VB你误会个人发票个围巾批发日期能不能到货是二表哥垄断且扒肉条跟你说等你离开农村是个我和法国人陪你欧式地回复我IEnew陪你放屁呢【全文你未分配哪位破fine发日后到静安寺浦东嘉搜谱大V分批of较为频繁我欧文IER级外婆oasdioadoia给那送佛前破解破壁机水平的节目评委个回去我诶妇女为偶发VB你误会个人发票个围巾批发日期能不能到货是二表哥垄断且扒肉条跟你说等你离开农村是个我和法国人陪你欧式地回复我IEnew陪你放屁呢【全文你未分配哪位破fine发到静安寺浦东嘉搜谱大V分批of较为频繁我欧文IER级外婆oasdioadoia给那送佛前破解破壁机水平的节目评委个回去我诶妇女为偶发VB你误会个人发票个围巾批发日期能不能到货是二表哥垄断且扒肉条跟你说等你离开农村是个我和法国人陪你欧式地回复我IEnew陪你放屁呢【全文你未分配哪位破fine发");
            dynamic.setAuthorName("肉王说币");
            dynamic.setImgAuthorUrl(Consts.BING_PIC);
            dynamic.setLike(3532);
            dynamic.setComment(266);
            dynamic.setStarId("ISO314159");

            /**
             * 无头部、多图或无图、无链接
             */
            DynamicOut dynamic2 = new DynamicOut();
            dynamic2.setImgUrl(imgUrls);
            dynamic2.setType("1");
            dynamic2.setTriggerName("可可说币");
            dynamic2.setTriggerAction(5);
            dynamic2.setImgStarUrl(Consts.BING_PIC);
            dynamic2.setStarName("啦啦啦啦啦啦");
            dynamic2.setPublishTime("3天前");
            dynamic2.setIn(true);
            dynamic2.setText("跟你说等你离https://www.baidu.com/开农村是个我和法国人https://www.baidu.com/陪你欧式地回复我IEnew陪【全文】你未分配哪位破fine发日后");
            dynamic2.setAuthorName("币圈邦德");
            dynamic2.setImgAuthorUrl(Consts.BING_PIC);
            dynamic2.setLike(0);
            dynamic2.setComment(3);
            dynamic2.setStarId("ISO89757");

            /**
             * 带连接内容、无头部、无图
             */
            DynamicOut dynamic3 = new DynamicOut();
            dynamic3.setType("1");
            dynamic3.setLinkContent("这是链接的文字内容，计然哈哈哈哈哈，啦啦啦啦，已和无法拨info文凭不过好的撒看到啥时候");
            dynamic3.setLinkImage(Consts.BING_PIC);
            dynamic3.setLinkUrl("https://mail.163.com/");
            dynamic3.setTriggerName("可可说币");
            dynamic3.setTriggerAction(5);
            dynamic3.setImgStarUrl(Consts.BING_PIC);
            dynamic3.setStarName("啦啦啦啦啦啦");
            dynamic3.setPublishTime("3天前");
            dynamic3.setIn(true);
            dynamic3.setText("跟你说等https://www.baidu.com/你离开农村https://www.baidu.com/是个我和法国人陪你https://www.baidu.com/欧式地回复我IEnew陪【全文】你未分配哪位破fine发日后打算打算多少个人合同合同任务任务二分部防丢偶偶发布公告的风格");
            dynamic3.setAuthorName("币圈邦德");
            dynamic3.setImgAuthorUrl(Consts.BING_PIC);
            dynamic3.setLike(7);
            dynamic3.setComment(0);
            dynamic3.setStarId("ISO89757");

            /**
             * 转发，带连接
             */
            DynamicOut dynamic4 = new DynamicOut();
            dynamic4.setType("2");
            dynamic4.setTriggerName("可可说币");
            dynamic4.setTriggerAction(1);
            dynamic4.setImgStarUrl(Consts.BING_PIC);
            dynamic4.setStarName("啦啦啦啦啦啦");
            dynamic4.setPublishTime("23秒前");
            dynamic4.setIn(false);
            dynamic4.setText("跟你说等https://www.baidu.com/你离开农村https://www.baidu.com/是个我和法国人陪你https://www.baidu.com/欧式地回复我IEnew陪【全文】你未分配哪位破fine发日后打算打算多少个人合同合同任务任务二分部防丢偶偶发布公告的风格");
            dynamic4.setsHasDelete(false);
            dynamic4.setsAuthorName("区块链朱荣");
            dynamic4.setsStarName("发发公链");
            dynamic4.setsText("你说等https://www.baidu.com/你离开农村https://www.baidu.com/是个我和法国人陪你https://www.baidu.com/欧式地回复我");
            dynamic4.setsLinkTitle("这是被转发动态的链接的标题");
            dynamic4.setsLinkImage(Consts.BING_PIC2);
            dynamic4.setsLinkContent("比特币正在解禁6000美金：交易所拟上线哈哈哈哈啦啦啦gogogo");
            dynamic4.setsLinkUrl("https://www.json.cn/");
            dynamic4.setAuthorName("币圈邦德");
            dynamic4.setImgAuthorUrl(Consts.BING_PIC1);
            dynamic4.setLike(33);
            dynamic4.setComment(0);
            dynamic4.setStarId("ISO89758");

            /**
             * 转发，多图
             */
            DynamicOut dynamic5 = new DynamicOut();
            dynamic5.setType("2");
            dynamic5.setTriggerName("啦啦说币");
            dynamic5.setTriggerAction(1);
            dynamic5.setImgStarUrl(Consts.BING_PIC);
            dynamic5.setStarName("啦啦星球");
            dynamic5.setPublishTime("1小时前");
            dynamic5.setIn(true);
            dynamic5.setText("跟你说等https://www.baidu.com/你离开农村https://www.baidu.com/是个我和法国人陪你https://www.baidu.com/欧式地回复我IEnew陪【全文】你未分配哪位破fine发日后打算打算多少个人合同合同任务任务二分部防丢偶偶发布公告的风格");
            dynamic5.setsHasDelete(false);
            dynamic5.setsAuthorName("区块链张飞");
            dynamic5.setsStarName("啦啦公链");
            dynamic5.setsText("你说等https://www.baidu.com/你离开农村https://www.baidu.com/是个我和法国人陪你https://www.baidu.com/欧式地回复我");
            dynamic5.setsImageUrls(imgUrls);
            dynamic5.setAuthorName("币圈柯基");
            dynamic5.setImgAuthorUrl(Consts.BING_PIC1);
            dynamic5.setLike(0);
            dynamic5.setComment(1349);
            dynamic5.setStarId("ISO89758");
            dynamic5.setsStarId("ISO89759");

            /**
             * 转发，已删除
             */
            DynamicOut dynamic6 = new DynamicOut();
            dynamic6.setType("2");
            dynamic6.setTriggerName("啦啦说币");
            dynamic6.setTriggerAction(3);
            dynamic6.setImgStarUrl(Consts.BING_PIC2);
            dynamic6.setStarName("果果星球");
            dynamic6.setPublishTime("1小时前");
            dynamic6.setIn(false);
            dynamic6.setText("跟你说等https://www.baidu.com/你离开农村https://www.baidu.com/是个我和法国人陪你https://www.baidu.com/欧式地回复我IEnew陪【全文】你未分配哪位破fine发日后打算打算多少个人合同合同任务任务二分部防丢偶偶发布公告的风格");
            dynamic6.setsHasDelete(true);
            dynamic6.setAuthorName("币圈柯基");
            dynamic6.setImgAuthorUrl(Consts.BING_PIC1);
            dynamic6.setLike(0);
            dynamic6.setComment(1349);
            dynamic6.setStarId("ISO89777");
            dynamic6.setsStarId("ISO89788");

            list.add(dynamic);
            list.add(dynamic2);
            list.add(dynamic3);
            list.add(dynamic4);
            list.add(dynamic5);
            list.add(dynamic6);
        }
        return list;
    }
}
