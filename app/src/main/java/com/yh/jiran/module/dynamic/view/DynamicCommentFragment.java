package com.yh.jiran.module.dynamic.view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yh.core.app.BaseFragment;
import com.yh.core.utils.NumberUtil;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.custom.dialog.comment.InputDialog;
import com.yh.jiran.custom.dialog.common.JrDialog;
import com.yh.jiran.module.dynamic.model.entity.Comment;
import com.yh.jiran.module.dynamic.view.adapter.CommentAdapter;
import com.yh.jiran.utils.Consts;
import com.yh.jiran.utils.RouterMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/7/25
 * @function: 动态详情页面-评论Fragment
 */
public class DynamicCommentFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_forward_num)
    AppCompatTextView tvForwardNum;
    @BindView(R.id.tv_like_num)
    AppCompatTextView tvLikeNum;
    @BindView(R.id.recycler_comment)
    RecyclerView recyclerComment;

    private List<Comment> mDatas = new ArrayList<>();
    private ImmerseActivity mActivity;
    private CommentAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_dynamic_comment_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @Override
    protected Object setContent() {
        return R.layout.fragment_dynamic_comment_layout;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        tvTitle.setText("用户热评" + NumberUtil.parseToK(mDatas.size()));
        tvForwardNum.setText(NumberUtil.parseToK(123));
        tvLikeNum.setText(NumberUtil.parseToK(12345));

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerComment.setLayoutManager(manager);
        recyclerComment.setNestedScrollingEnabled(false);
        mAdapter = new CommentAdapter(mDatas);
//        recyclerComment.requestLayout();
        recyclerComment.setAdapter(mAdapter);
//        recyclerComment.setNestedScrollingEnabled(false);
        mAdapter.bindToRecyclerView(recyclerComment);

        mDatas.clear();
        mDatas.addAll(getComments());
        mAdapter.notifyDataSetChanged();

        setOnClick();
    }

    private void setOnClick() {
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            AppCompatImageView ivLike = (AppCompatImageView) adapter.getViewByPosition(position, R.id.iv_like);
            switch (view.getId()) {
                case R.id.iv_reviewer:
                    ARouter.getInstance()
                            .build(RouterMap.PATH_USER_HOME_ACTIVITY)
                            .navigation();
                    break;
                case R.id.iv_like:
                    ivLike.setImageResource(R.drawable.vector_comment_like_selected);
                    // TODO: 2018/8/16 发送消息  点赞评论
                    break;
                case R.id.tv_comment_text:
                    break;
                case R.id.tv_source_text:
                    break;
                case R.id.tv_reply:
                    InputDialog inputDialog = new InputDialog(getContext());
//                    inputDialog.setOnKeyListener((dialogInterface, keyCode, event) -> {
//                        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//                            dialogInterface.cancel();
//                        }
//                        return false;
//                    });
                    inputDialog.show();

//                    JrDialog jrDialog = new JrDialog(getContext(), R.style.CommentDialogStyle)
//                            .title("评论")
//                            .negative();
//                    jrDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//                        @Override
//                        public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent event) {
//                            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
//                                dialogInterface.cancel();
//                            return false;
//                        }
//                    });
//                    jrDialog.show();
                    break;
                case R.id.tv_delete:
                    new JrDialog(getContext())
                            .title("删除")
                            .message("确定要删除这一条评论吗？")
                            .negative()
                            .positive((dialog, which) -> {
                                mDatas.remove(position);
                                mAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            })
                            .show();

                    break;
                default:
                    break;
            }
        });
    }

    private List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Comment comment1 = new Comment();
            comment1.setUserImage(Consts.BING_PIC8);
            comment1.setUserName("哈哈哈");
            comment1.setLike(503);
            comment1.setTime("15分钟前");
            comment1.setMine(true);
            comment1.setsId("");
            comment1.setText("比特币金水泥地方女士对空ofo你玩儿就奋斗改变你搜的大江南北安师大是烦死我都人文给为保卫分不清女的实地去不去瓯网");
            comments.add(comment1);

            Comment comment2 = new Comment();
            comment2.setUserImage(Consts.BING_PIC7);
            comment2.setUserName("啦啦啦");
            comment2.setLike(6300);
            comment2.setTime("5分钟前");
            comment2.setMine(false);
            comment2.setsId("1000066");
            comment2.setText("就奋斗改变你搜的大江南北安师大是烦死我都人文给为保卫分不清女的实地去不去瓯网");
            comment2.setsText("比特币金水泥地方女士对空ofo你玩儿打分coin访问服务能力比么");
            comment2.setsHasDeleted(false);
            comment2.setsUserName("祝融");
            comments.add(comment2);

            Comment comment3 = new Comment();
            comment3.setUserImage(Consts.BING_PIC6);
            comment3.setUserName("嘿嘿嘿");
            comment3.setLike(2300);
            comment3.setTime("1分钟前");
            comment3.setMine(true);
            comment3.setsId("1000066");
            comment3.setText("就奋斗改变你搜的大江南北安师大是烦死我都人文给为保卫分不清女的实地去不去瓯网");
            comment3.setsText("比特币金水泥地方女士对空ofo你玩儿打分coin访问服务能力比么");
            comment3.setsHasDeleted(true);
            comment3.setsUserName("祝融");
            comments.add(comment3);
        }

        return comments;
    }

    @Override
    protected void initData() {
    }
}
