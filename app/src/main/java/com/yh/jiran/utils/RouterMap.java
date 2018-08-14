package com.yh.jiran.utils;

/**
 * @author: 闫昊
 * @date: 2018/7/21
 * @function: 路由表
 */
public class RouterMap {

    public static final String STAR_ID = "star_id";
    public static final String USER_ID = "user_id";
    public static final String DYNAMIC_ID = "dynamic_id";


    /**
     * 路由跳转界面（测试用）
     */
    public static final String PATH_ROUTER_ACTIVITY = "/test/router.activity";

    /**
     * 快递查询页面（MVP/RxJava模板）
     */
    public static final String PATH_COURIER_ACTIVITY = "/test/CourierActivity";

//    /**
//     * 星球详情界面
//     */
//    public static final String PATH_STAR_ACTIVITY = "/home/StarActivity";


    /**
     * 返回码：编辑用户信息
     */
    public static final int RESULT_CODE_USER_EDIT = 1001;


    /**
     * APP主页
     */
    public static final String PATH_HOME_ACTIVITY = "/main/HomeActivity";

    /**
     * 搜索界面
     */
    public static final String PATH_SEARCH_ACTIVITY = "/home/SearchActivity";

    /**
     * 选择星球界面
     */
    public static final String PATH_STAR_PICK_ACTIVITY = "/home/StarPickActivity";

    /**
     * 星球主页
     */
    public static final String PATH_STAR_HOME_ACTIVITY = "/star/StarHomeActivity";

    /**
     * 登录界面
     */
    public static final String PATH_LOGIN_ACTIVITY = "/login/LoginActivity";

    /**
     * 确定头像和用户名界面
     */
    public static final String PATH_INFO_ACTIVITY = "/login/InfoActivity";

    /**
     * 推荐星球界面
     */
    public static final String PATH_REC_STAR_ACTIVITY = "/login/RecStarActivity";

    /**
     * APP内置浏览器
     */
    public static final String PATH_WEBVIEW_ACTIVITY = "/common/WebViewActivity";

    /**
     * 大图浏览界面
     */
    public static final String PATH_PHOTO_VIEW_ACTIVITY = "/common/PhotoViewActivity";

    /**
     * 动态详情页
     */
    public static final String PATH_DYNAMIC_DETAIL_ACTIVITY = "/dynamic/DynamicDetailActivity";

    /**
     * 个人主页
     */
    public static final String PATH_USER_HOME_ACTIVITY = "/user/UserActivity";

    /**
     * 个人信息编辑页
     */
    public static final String PATH_USER_EDIT_ACTIVITY = "/user/UserEditActivity";

    /**
     * 设置页面
     */
    public static final String PATH_SETTING_ACTIVITY = "/common/SettingActivity";

    /**
     * 粉丝或者关注列表
     */
    public static final String PATH_USER_MUTUAL_ACTIVITY = "/user/UserMutualActivity";

    /**
     * 星球资料页
     */
    public static final String PATH_STAR_INFO_ACTIVITY = "/star/StarInfoActivity";

    /**
     * 发布动态页
     */
    public static final String PATH_DYNAMIC_TWEET_ACTIVITY = "/dynamic/DynamicTweetActivity";
}
