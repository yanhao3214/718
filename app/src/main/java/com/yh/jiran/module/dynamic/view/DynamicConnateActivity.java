package com.yh.jiran.module.dynamic.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.yh.core.utils.UrlUtil;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.base.WebViewActivity;
import com.yh.jiran.custom.dialog.comment.CommentDialog;
import com.yh.jiran.custom.dialog.comment.OnCommentClickListener;
import com.yh.jiran.custom.dialog.common.JrDialog;
import com.yh.jiran.custom.dialog.dynamic.HostDialog;
import com.yh.jiran.custom.dialog.dynamic.MemberDialog;
import com.yh.jiran.custom.dialog.dynamic.callback.CommonCallback;
import com.yh.jiran.custom.dialog.dynamic.callback.MuteCallback;
import com.yh.jiran.custom.dialog.share.ShareDialog;
import com.yh.jiran.custom.text.AllTextView;
import com.yh.jiran.custom.text.FilterClickMovementMethod;
import com.yh.jiran.custom.text.InnerURLSpan;
import com.yh.jiran.module.dynamic.model.entity.DynamicOut;
import com.yh.jiran.module.dynamic.view.adapter.NineImageAdapter;
import com.yh.jiran.module.star.view.StarActivity;
import com.yh.jiran.module.user.view.UserActivity;
import com.yh.jiran.utils.Consts;
import com.yh.jiran.utils.RouterMap;
import com.yh.jiran.utils.image.GlideLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 动态详情页-原创
 */
@Route(path = RouterMap.PATH_DYNAMIC_CONNATE_ACTIVITY)
public class DynamicConnateActivity extends ImmerseActivity {

    public static final String DYNAMIC_ID = "mDynamicId";

    @BindView(R.id.iv_cancel)
    AppCompatImageView ivCancel;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.iv_collect)
    AppCompatImageView ivCollect;
    @BindView(R.id.tv_operate)
    AppCompatTextView tvOperate;
    @BindView(R.id.iv_author)
    CircleImageView ivAuthor;
    @BindView(R.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R.id.tv_time)
    AppCompatTextView tvTime;
    @BindView(R.id.btn_concern)
    AppCompatButton btnConcern;
    @BindView(R.id.btn_concern_done)
    AppCompatButton btnConcernDone;
    @BindView(R.id.tv_text)
    AppCompatTextView tvText;
    @BindView(R.id.iv_nine)
    NineGridImageView ivNine;
    @BindView(R.id.iv_link)
    AppCompatImageView ivLink;
    @BindView(R.id.tv_link)
    AppCompatTextView tvLink;
    @BindView(R.id.layout_link)
    LinearLayout layoutLink;
    @BindView(R.id.layout_up)
    LinearLayout layoutUp;
    @BindView(R.id.tv_from)
    AppCompatTextView tvFrom;
    @BindView(R.id.iv_star_from)
    AppCompatImageView ivStarFrom;
    @BindView(R.id.tv_star_name)
    AppCompatTextView tvStarName;
    @BindView(R.id.tv_elite)
    AppCompatTextView tvElite;
    @BindView(R.id.tv_member)
    AppCompatTextView tvMember;
    @BindView(R.id.layout_star_from)
    RelativeLayout layoutStarFrom;
    @BindView(R.id.tv_comment)
    AppCompatTextView tvComment;
    @BindView(R.id.iv_comment)
    AppCompatImageView ivComment;
    @BindView(R.id.tv_comment_num)
    AppCompatTextView tvCommentNum;
    @BindView(R.id.iv_like)
    AppCompatImageView ivLike;
    @BindView(R.id.iv_share)
    AppCompatImageView ivShare;
    @BindView(R.id.scroll_view)
    NestedScrollView scrollView;
    @BindView(R.id.layout_comment)
    FrameLayout layoutComment;

    private DynamicOut mDynamic = new DynamicOut();

    @Autowired
    String mDynamicId;

    @Override
    protected int setContent() {
        return R.layout.activity_dynamic_detail_connate_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);
        initDynamicWithPics();

        tvName.setText(mDynamic.getAuthorName());
        tvTime.setText(mDynamic.getPublishTime());
        tvText.setText(mDynamic.getText());
        ivCollect.setImageResource(mDynamic.isMyCollection() ? R.drawable.ic_collect_selected : R.drawable.ic_collect_normal);
        btnConcern.setVisibility(mDynamic.isConcernAuthor() ? View.GONE : View.VISIBLE);
        btnConcernDone.setVisibility(!mDynamic.isConcernAuthor() ? View.GONE : View.VISIBLE);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.layout_comment, new DynamicCommentFragment());
        ft.commit();

        //加载头像
        GlideLoader.load(this, mDynamic.getImgAuthorUrl(), ivAuthor);

        /**
         * 设置正文：超链接 跳转
         */
        handleText(tvText, mDynamic.getText(), mDynamic);

        /**
         * 设置九宫格图片
         */
        if (null != mDynamic.getImgUrl()) {
            ivNine.setAdapter(new NineImageAdapter());
            ivNine.setImagesData(mDynamic.getImgUrl());
        } else {
            ivNine.setVisibility(View.GONE);
        }

        /**
         * 设置链接布局的内容
         */
        boolean hasLink = !TextUtils.isEmpty(mDynamic.getLinkUrl());
        if (hasLink) {
            GlideLoader.load(this, mDynamic.getLinkImage(), ivLink);
            tvLink.setText(mDynamic.getLinkContent());
        } else {
            layoutLink.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.iv_cancel, R.id.iv_collect, R.id.tv_operate, R.id.iv_author, R.id.tv_name,
            R.id.btn_concern, R.id.layout_link, R.id.tv_from, R.id.layout_star_from, R.id.tv_comment,
            R.id.iv_comment, R.id.iv_like, R.id.iv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel:
                finish();
                break;
            case R.id.iv_collect:
                onCollect(ivCollect);
                break;
            case R.id.tv_operate:
                if (mDynamic.getMyStatus().equals("主理人")) {
                    perform();
                } else {
                    operate();
                }
                break;
            case R.id.iv_author:
            case R.id.tv_name:
                toUserHome(mDynamic.getAuthorId());
                break;
            case R.id.btn_concern:
                btnConcern.setVisibility(View.INVISIBLE);
                btnConcernDone.setVisibility(View.VISIBLE);
                // TODO: 2018/8/16 发送消息 关注用户
                Toast.makeText(this, "关注成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_link:
            case R.id.tv_from:
                toLink(mDynamic.getsLinkUrl(), mDynamic.getsLinkTitle());
                break;
            case R.id.layout_star_from:
                toStarHome(mDynamic.getStarId());
                break;
            case R.id.tv_comment:
                new CommentDialog(this)
                        .publish((commentDialog, comment) -> {
                            Toast.makeText(DynamicConnateActivity.this, "发布评论",
                                    Toast.LENGTH_SHORT).show();
                            // TODO: 2018/8/17 发送消息：发布评论

                            commentDialog.dismiss();
                        })
                        .show();
                break;
            case R.id.iv_comment:
                Toast.makeText(this, "显示评论", Toast.LENGTH_SHORT).show();
//                DynamicCommentFragment commentFragment = (DynamicCommentFragment) getSupportFragmentManager().findFragmentById(R.id.layout_comment);
//                commentFragment.current();

                scrollView.post(() -> scrollView.smoothScrollTo(0, layoutComment.getTop()));
                break;
            case R.id.iv_like:
                ivLike.setImageResource(R.drawable.vector_dynamic_like_selected);
                // TODO: 2018/8/17 发送消息：点赞动态
                break;
            case R.id.iv_share:
                new ShareDialog(this, false)
                        .show();
                break;
            default:
                break;
        }
    }

    private void onCollect(ImageView ivCollect) {
        if (ivCollect.getDrawable().getCurrent().getConstantState() == ContextCompat.getDrawable(this, R.drawable.ic_collect_selected).getConstantState()) {
            ivCollect.setImageResource(R.drawable.ic_collect_normal);
            Toast.makeText(this, "发送取消收藏的消息", Toast.LENGTH_SHORT).show();
            // TODO: 2018/8/12 发送取消收藏消息
        } else {
            ivCollect.setImageResource(R.drawable.ic_collect_selected);
            Toast.makeText(this, "发送添加收藏的消息", Toast.LENGTH_SHORT).show();
            // TODO: 2018/8/12 发送收藏消息
        }
    }

    /**
     * 设置正文：1.“全文”跳转；2.超链接 跳转
     */
    @SuppressLint("ClickableViewAccessibility")
    private void handleText(TextView textView, String raw, DynamicOut dynamic) {
        List<String> urls = new ArrayList<>();
        String text = UrlUtil.handleURLinString(raw, urls);
        SpannableString ss = new SpannableString(text);
        int loc = text.indexOf("#查看链接");
        int i = 0;
        while (loc != -1) {
            InnerURLSpan innerURLSpan = new InnerURLSpan(this, "", urls.get(i++));
            ss.setSpan(innerURLSpan, loc, loc + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            loc = text.indexOf("#查看链接", loc + 1);
        }

        textView.setText(ss);
        textView.setOnTouchListener(FilterClickMovementMethod.getInstance());
        if (textView instanceof AllTextView) {
            ((AllTextView) textView).setMaxShowLines(6);
            ((AllTextView) textView).setMyText(textView.getText());
            ((AllTextView) textView).setOnAllSpanClickListener(widget -> ARouter.getInstance()
                    .build(dynamic.getType().equals("1") ? RouterMap.PATH_DYNAMIC_CONNATE_ACTIVITY
                            : RouterMap.PATH_DYNAMIC_FORWARD_ACTIVITY)
                    .withString(DynamicConnateActivity.DYNAMIC_ID, dynamic.getDynamicId())
                    .navigation());
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
    private void toDynamicHome(String dynamicId, String type) {
        ARouter.getInstance()
                .build(type.equals("1") ? RouterMap.PATH_DYNAMIC_CONNATE_ACTIVITY
                        : RouterMap.PATH_DYNAMIC_FORWARD_ACTIVITY)
                .withString(DynamicConnateActivity.DYNAMIC_ID, dynamicId)
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
     * 动态操作：星球外所有人
     */
    private void operate() {
        new MemberDialog(DynamicConnateActivity.this, true, false)
                .perform(new CommonCallback() {
                    @Override
                    public void onDelete(Dialog dialog) {
                        dialog.dismiss();
                        new JrDialog(DynamicConnateActivity.this)
                                .title("删除")
                                .message("确定要删除这一条动态吗？")
                                .positive((dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    Toast.makeText(DynamicConnateActivity.this, "删除动态成功", Toast.LENGTH_SHORT).show();
                                })
                                .negative()
                                .show();
                        // TODO: 2018/8/8 发送消息：删除动态
                    }

                    @Override
                    public void onCollect(Dialog dialog) {
                        dialog.dismiss();
                        Toast.makeText(DynamicConnateActivity.this, "发送消息：收藏动态", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(Dialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }

    /**
     * 动态操作：星球内主理人（测试）
     */
    private void perform() {
        new HostDialog(DynamicConnateActivity.this, false, false, false, false, true)
                .elite(dialog -> {
                    Toast.makeText(DynamicConnateActivity.this, "发送消息：加入精华", Toast.LENGTH_SHORT).show();
                    // TODO: 2018/8/8  发送消息：加入精华
                    dialog.dismiss();
                })
                .mute(new MuteCallback() {
                    @Override
                    public void onMuteTemp(Dialog dialog) {
                        dialog.dismiss();
                        new JrDialog(DynamicConnateActivity.this)
                                .title("禁言")
                                .message("确定要将 马云坤 禁言3天吗？")
                                .positive((dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    Toast.makeText(DynamicConnateActivity.this, "禁言3天成功", Toast.LENGTH_SHORT).show();
                                })
                                .negative()
                                .show();
                        // TODO: 2018/8/8  发送消息：禁言3天
                    }

                    @Override
                    public void onMuteForever(Dialog dialog) {
                        dialog.dismiss();
                        new JrDialog(DynamicConnateActivity.this)
                                .title("禁言")
                                .message("确定要将 马云坤 永久禁言吗？")
                                .positive((dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    Toast.makeText(DynamicConnateActivity.this, "永久禁言成功", Toast.LENGTH_SHORT).show();
                                })
                                .negative()
                                .show();
                        // TODO: 2018/8/8  发送消息：永久禁言
                    }

                    @Override
                    public void onCancel(Dialog dialog) {
                        // TODO: 2018/8/8  发送消息：解除禁言
                        Toast.makeText(DynamicConnateActivity.this, "解除禁言成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .concern(dialog -> {
                    Toast.makeText(DynamicConnateActivity.this, "发送消息：关注作者", Toast.LENGTH_SHORT).show();
                    // TODO: 2018/8/8  发送消息：关注作者
                    dialog.dismiss();
                })
                .common(new CommonCallback() {
                    @Override
                    public void onDelete(Dialog dialog) {
                        Toast.makeText(DynamicConnateActivity.this, "发送消息：删除动态", Toast.LENGTH_SHORT).show();
                        // TODO: 2018/8/8  发送消息：删除动态
                        dialog.dismiss();
                    }

                    @Override
                    public void onCollect(Dialog dialog) {
                        Toast.makeText(DynamicConnateActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
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
     * 多图、无链接
     */
    private void initDynamicWithPics() {
        List<String> imgUrls = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            imgUrls.add(Consts.BING_PIC7);
        }

        mDynamic.setMyCollection(false);
        mDynamic.setMyStatus("成员");
        mDynamic.setType("1");
        mDynamic.setImgUrl(imgUrls);
        mDynamic.setType("1");
        mDynamic.setTriggerName("可可说币");
        mDynamic.setTriggerAction(5);
        mDynamic.setImgStarUrl(Consts.BING_PIC);
        mDynamic.setStarName("啦啦啦啦啦啦");
        mDynamic.setPublishTime("3天前");
        mDynamic.setIn(true);
        mDynamic.setText("跟你说等你离https://www.baidu.com/开农村是个我和法国人https://www.baidu.com/陪你欧式地回复我IEnew陪【全文】你未分配哪位破fine发日后");
        mDynamic.setAuthorName("币圈邦德");
        mDynamic.setImgAuthorUrl(Consts.BING_PIC);
        mDynamic.setLike(0);
        mDynamic.setComment(3);
        mDynamic.setStarId("ISO89757");
    }

    /**
     * 带连接内容、无图
     */
    private void initDynamicWithLink() {
        mDynamic.setMyCollection(true);
        mDynamic.setMyStatus("主理人");
        mDynamic.setType("1");
        mDynamic.setType("1");
        mDynamic.setLinkContent("这是链接的文字内容，计然哈哈哈哈哈，啦啦啦啦，已和无法拨info文凭不过好的撒看到啥时候");
        mDynamic.setLinkImage(Consts.BING_PIC);
        mDynamic.setLinkUrl("https://mail.163.com/");
        mDynamic.setTriggerName("可可说币");
        mDynamic.setTriggerAction(5);
        mDynamic.setImgStarUrl(Consts.BING_PIC);
        mDynamic.setStarName("啦啦啦啦啦啦");
        mDynamic.setPublishTime("3天前");
        mDynamic.setIn(true);
        mDynamic.setText("跟你说等https://www.baidu.com/你离开农村https://www.baidu.com/是个我和法国人陪你https://www.baidu.com/欧式地回复我IEnew陪【全文】你未分配哪位破fine发日后打算打算多少个人合同合同任务任务二分部防丢偶偶发布公告的风格");
        mDynamic.setAuthorName("币圈邦德");
        mDynamic.setImgAuthorUrl(Consts.BING_PIC);
        mDynamic.setLike(7);
        mDynamic.setComment(0);
        mDynamic.setStarId("ISO89757");
    }
}
