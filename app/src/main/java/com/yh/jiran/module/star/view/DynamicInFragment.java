package com.yh.jiran.module.star.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yh.core.app.BaseFragment;
import com.yh.core.utils.NumberUtil;
import com.yh.jiran.R;
import com.yh.jiran.base.WebViewActivity;
import com.yh.jiran.custom.dialog.callback.CommonCallback;
import com.yh.jiran.custom.dialog.callback.MuteCallback;
import com.yh.jiran.custom.dialog.common.JrDialog;
import com.yh.jiran.custom.dialog.host.HostDialog;
import com.yh.jiran.custom.dialog.share.ShareDialog;
import com.yh.jiran.module.dynamic.model.entity.DynamicOut;
import com.yh.jiran.module.dynamic.view.DynamicConcernFragment;
import com.yh.jiran.module.dynamic.view.DynamicDetailActivity;
import com.yh.jiran.module.home.view.StarActivity;
import com.yh.jiran.module.star.view.adapter.DynamicInAdapter;
import com.yh.jiran.module.user.view.UserActivity;
import com.yh.jiran.utils.Consts;
import com.yh.jiran.utils.RouterMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/8/13
 * @function: 星球内动态列表Fragment
 */
public class DynamicInFragment extends BaseFragment implements View.OnClickListener {

    private Context mContext;
    private DynamicInAdapter mAdapter;
    private List<DynamicOut> mDatas = new ArrayList<>();
    private PopupWindow mPopupWindow;
    private View mPopView;
    private RadioButton rbDefault;
    private RadioButton rbNew;
    private boolean sortDefault = true;
    private View mHeader;
    private AppCompatTextView tvNum;
    private AppCompatTextView tvSort;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected Object setContent() {
        return R.layout.fragment_star_elite_layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_star_elite_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @SuppressLint({"SetTextI18n", "InflateParams"})
    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        mContext = getContext();
        mHeader = LayoutInflater.from(mContext).inflate(R.layout.header_star_dynamic_layout, null);
        tvNum = mHeader.findViewById(R.id.tv_num);
        tvSort = mHeader.findViewById(R.id.tv_sort);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new DynamicInAdapter(mDatas);
        mAdapter.addHeaderView(mHeader);
        recyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(recyclerView);
        mAdapter.setOnItemChildClickListener(this::onItemClick);

        mDatas.clear();
        mDatas.addAll(getData());
        mAdapter.notifyDataSetChanged();

        // TODO: 2018/8/14 空指针异常？？？
//        View grey = mAdapter.getViewByPosition(recyclerView, 1, R.id.head_grey);
//        grey.setVisibility(View.GONE);
        tvNum.setText(mDatas.size() + "条动态");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sort:

                break;
            case R.id.rb_default:
                sortDefault = true;
                Toast.makeText(mContext, "默认排序", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();

                // TODO: 2018/8/14 请求数据，默认排序
                break;
            case R.id.rb_new:
                sortDefault = false;
                Toast.makeText(mContext, "最新发布", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();

                // TODO: 2018/8/14 请求数据：最新发布
                break;
            default:
                break;
        }
    }

    /**
     * 列表Item子View点击事件
     */
    private void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DynamicOut dynamic = mDatas.get(position);
        if (dynamic.getType().equals(DynamicConcernFragment.DYNAMIC_OUT_TYPE_CONNATE)) {
            switch (view.getId()) {
                case R.id.iv_author:
                case R.id.tv_name:
                    toUserHome(dynamic.getAuthorId());
                    break;
                case R.id.btn_join:
                    AppCompatButton btnJoin =
                            (AppCompatButton) adapter.getViewByPosition(position, R.id.btn_join);
                    AppCompatButton btnMore =
                            (AppCompatButton) adapter.getViewByPosition(position, R.id.btn_more);
                    btnJoin.setVisibility(View.GONE);
                    btnMore.setVisibility(View.VISIBLE);
                    // TODO: 2018/8/7 发送加入星球消息
                    break;
                case R.id.btn_more:
                    perform();
                    break;
                case R.id.tv_text:
                    toDynamicHome(dynamic.getDynamicId());
                    break;
                case R.id.tv_origin_star:
                    toStarHome(dynamic.getsStarId());
                    break;
                case R.id.tv_source_text:
                    toDynamicHome(dynamic.getsDynamicId());
                    break;
                case R.id.layout_link:
                    toLink(dynamic.getsLinkUrl(), dynamic.getsLinkTitle());
                    break;
                case R.id.tv_like:
                    AppCompatTextView tvLike =
                            (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_like);
                    onLike(dynamic, tvLike);
                    break;
                case R.id.tv_comment:
                    AppCompatTextView tvComment =
                            (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_comment);
                    onComment(dynamic, tvComment);
                    break;
                case R.id.tv_share:
                    share();
                    break;
                default:
                    break;
            }
        } else if (dynamic.getType().equals(DynamicConcernFragment.DYNAMIC_OUT_TYPE_CONNATE)) {

            switch (view.getId()) {
                case R.id.tv_trigger:
                    toUserHome(dynamic.getTriggerId());
                    break;
                case R.id.tv_name:
                case R.id.iv_star:
                    toStarHome(dynamic.getStarId());
                    break;
                case R.id.btn_join:
                    AppCompatButton btnJoin =
                            (AppCompatButton) adapter.getViewByPosition(position, R.id.btn_join);
                    AppCompatButton btnMore =
                            (AppCompatButton) adapter.getViewByPosition(position, R.id.btn_more);
                    btnJoin.setVisibility(View.GONE);
                    btnMore.setVisibility(View.VISIBLE);
                    // TODO: 2018/8/7 发送加入星球消息
                    break;
                case R.id.btn_more:
                    perform();
                    break;
                case R.id.tv_text:
                    toDynamicHome(dynamic.getDynamicId());
                    break;
                case R.id.layout_link:
                    toLink(dynamic.getLinkUrl(), dynamic.getLinkTitle());
                    break;
                case R.id.iv_author:
                case R.id.tv_author:
                    toUserHome(dynamic.getAuthorId());
                    break;
                case R.id.tv_like:
                    AppCompatTextView tvLike =
                            (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_like);
                    onLike(dynamic, tvLike);
                    break;
                case R.id.tv_comment:
                    AppCompatTextView tvComment =
                            (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_comment);
                    onComment(dynamic, tvComment);
                    break;
                case R.id.tv_share:
                    share();
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * 跳转到个人主页主页
     */
    private void toUserHome(String userId, boolean otherView, boolean hasConcern) {
        ARouter.getInstance()
                .build(RouterMap.PATH_USER_HOME_ACTIVITY)
                .withString(UserActivity.USER_ID, userId)
                .withBoolean(UserActivity.USER_OTHER_VIEW, otherView)
                .withBoolean(UserActivity.USER_HAS_COLLECT, hasConcern)
                .navigation();
    }

    /**
     * 跳转到个人主页主页,（默认参数，自己视角）
     */
    private void toUserHome(String userId) {
        ARouter.getInstance()
                .build(RouterMap.PATH_USER_HOME_ACTIVITY)
                .withString(UserActivity.USER_ID, userId)
                .withBoolean(UserActivity.USER_OTHER_VIEW, false)
                .withBoolean(UserActivity.USER_HAS_COLLECT, false)
                .navigation();
    }

    /**
     * 跳转到星球主页
     */
    private void toStarHome(String starId) {
        ARouter.getInstance()
                .build(RouterMap.PATH_STAR_HOME_ACTIVITY)
                .withString(StarActivity.STAR_ID, starId)
                .navigation();
    }

    /**
     * 跳转到动态详情页
     */
    private void toDynamicHome(String dynamicId) {
        ARouter.getInstance()
                .build(RouterMap.PATH_DYNAMIC_DETAIL_ACTIVITY)
                .withString(DynamicDetailActivity.DYNAMIC_ID, dynamicId)
                .navigation();
    }

    /**
     * 跳转到动态详情页
     */
    private void toLink(String linkUrl, String linkTitle) {
        ARouter.getInstance()
                .build(RouterMap.PATH_WEBVIEW_ACTIVITY)
                .withString(WebViewActivity.WEBVIEW_URL, linkUrl)
                .withString(WebViewActivity.WEBVIEW_TITLE, linkTitle)
                .navigation();
    }

    /**
     * 点赞
     */
    private void onLike(DynamicOut dynamic, TextView textView) {
        int like = dynamic.getLike();
        textView.setCompoundDrawablesWithIntrinsicBounds
                (R.drawable.vector_dynamic_like_selected, 0, 0, 0);
        textView.setText(NumberUtil.parseToK(++like));
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.dynamic_yellow));

        // TODO: 2018/8/7 更新点赞数
    }

    /**
     * 评论
     */
    private void onComment(DynamicOut dynamic, TextView textView) {
        int comment = dynamic.getComment();
        textView.setText(NumberUtil.parseToK(++comment));
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.dynamic_yellow));

        // TODO: 2018/8/7 1.评论页详情；2.发送评论信息，更新评论数
    }

    private void share() {
        new ShareDialog(mContext, false)
                .setTitle("分享弹窗的标题")
                .setText("分享的具体内容")
                .setUrl("http://www.baidu.com")
                .setImgUrl(Consts.BING_PIC)
                .show();
        // TODO: 2018/8/7 分享功能完善
    }

    /**
     * 排序弹窗
     */
    @SuppressLint("InflateParams")
    private void pop() {
        mPopView = LayoutInflater.from(mContext).inflate(R.layout.pop_sort_layout, null);
        rbDefault = mPopView.findViewById(R.id.rb_default);
        rbNew = mPopView.findViewById(R.id.rb_new);
        rbDefault.setOnClickListener(this);
        rbNew.setOnClickListener(this);
        rbDefault.setCompoundDrawablesWithIntrinsicBounds(0, 0, sortDefault ? R.drawable.vector_sort_selected : 0, 0);
        rbNew.setCompoundDrawablesWithIntrinsicBounds(0, 0, !sortDefault ? R.drawable.vector_sort_selected : 0, 0);
        mPopupWindow = new PopupWindow(mPopView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(false);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAsDropDown(tvSort);
    }

    /**
     * 动态操作：星球内主理人
     */
    private void perform() {
        new HostDialog(mContext, false, false, false, false, true)
                .elite(dialog -> {
                    Toast.makeText(mContext, "发送消息：加入精华", Toast.LENGTH_SHORT).show();
                    // TODO: 2018/8/8  发送消息：加入精华
                    dialog.dismiss();
                })
                .mute(new MuteCallback() {
                    @Override
                    public void onMuteTemp(Dialog dialog) {
                        dialog.dismiss();
                        new JrDialog(mContext)
                                .title("禁言")
                                .message("确定要将 马云坤 禁言3天吗？")
                                .positive((dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    Toast.makeText(mContext, "禁言3天成功", Toast.LENGTH_SHORT).show();
                                })
                                .negative()
                                .show();
                        // TODO: 2018/8/8  发送消息：禁言3天
                    }

                    @Override
                    public void onMuteForever(Dialog dialog) {
                        dialog.dismiss();
                        new JrDialog(mContext)
                                .title("禁言")
                                .message("确定要将 马云坤 永久禁言吗？")
                                .positive((dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    Toast.makeText(mContext, "永久禁言成功", Toast.LENGTH_SHORT).show();
                                })
                                .negative()
                                .show();
                        // TODO: 2018/8/8  发送消息：永久禁言
                    }

                    @Override
                    public void onCancel(Dialog dialog) {
                        // TODO: 2018/8/8  发送消息：解除禁言
                        Toast.makeText(mContext, "解除禁言成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .concern(dialog -> {
                    Toast.makeText(mContext, "发送消息：关注作者", Toast.LENGTH_SHORT).show();
                    // TODO: 2018/8/8  发送消息：关注作者
                    dialog.dismiss();
                })
                .common(new CommonCallback() {
                    @Override
                    public void onDelete(Dialog dialog) {
                        Toast.makeText(mContext, "发送消息：删除动态", Toast.LENGTH_SHORT).show();
                        // TODO: 2018/8/8  发送消息：删除动态
                        dialog.dismiss();
                    }

                    @Override
                    public void onCollect(Dialog dialog) {
                        Toast.makeText(mContext, "取消收藏成功", Toast.LENGTH_SHORT).show();
                        // TODO: 2018/8/8  发送消息：取消收藏
                        dialog.dismiss();
                    }

                    @Override
                    public void onCancel(Dialog dialog) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * 获取数据星球内动态列表数据
     */
    private List<DynamicOut> getData() {
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
