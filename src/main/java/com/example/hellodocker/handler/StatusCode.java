package com.example.hellodocker.handler;

/**
 * @author yjf
 * @date 2022/2/21
 * @description
 */
public enum StatusCode {
    /**
     *	成功返回
     */
    SUCCESS(10000, ""),

    /**
     *	不合法的appId
     */
    INVALID_APPID(10001, "不合法的应用ID"),

    /**
     *	不合法的认证会话
     */
    INVALID_ACCESS_TOKEN(10002, "不合法的认证会话"),

    /**
     *	不合法的url长度
     */
    INVALID_URL_SIZE(10003, "不合法的url长度"),

    /**
     *	不合法的url域名
     */
    INVALID_URL_DOMAIN(10004, "不合法的url域名"),

    /**
     *	缺失认证会话参数
     */
    ACCESS_TOKEN_MISSING(10005, "缺失认证会话参数"),

    /**
     *	缺失appId参数
     */
    APPID_MISSING(10006, "缺失应用ID参数"),

    /**
     *	缺失刷新会话参数
     */
    REFRESH_TOKEN_MISSING(10007, "缺失刷新会话参数"),

    /**
     *	需要使用GET方法请求
     */
    REQUIRE_GET_METHOD(10008, "需要使用GET方法请求"),

    /**
     *	需要使用 POST方法请求
     */
    REQUIRE_POST_METHOD(10009, "需要使用 POST方法请求"),

    /**
     *	需要使用 HTTPS
     */
    REQUIRE_HTTPS(10010, "需要使用 HTTPS"),

    /**
     *	空白的POST数据
     */
    EMPTY_POST_DATA(10011, "空白的POST数据 "),

    /**
     *	二进制文件超过限制
     */
    MEDIA_SIZE_OUT_OF_LIMIT(10012, "二进制文件超过限制"),

    /**
     *	content参数超过限制
     */
    CONTENT_SIZE_OUT_OF_LIMIT(10013, "content参数超过限制 "),

    /**
     *	title参数超过限制
     */
    TITLE_SIZE_OUT_OF_LIMIT(10014, "title参数超过限制"),

    /**
     *	description参数超过限制
     */
    DESCRIPTION_SIZE_OUT_OF_LIMIT(10015, "description参数超过限制 "),

    /**
     *	url参数长度超过限制
     */
    URL_SIZE_OUT_OF_LIMIT(10016, "url参数长度超过限制"),

    /**
     *	picurl参数超过限制
     */
    PICURL_SIZE_OUT_OF_LIMIT(10017, "picurl参数超过限制"),

    /**
     *	接口未授权
     */
    API_UNAUTHORIZED(10018, "接口未授权"),

    /**
     *	接口请求参数不正确
     */
    INVAILD_REQUEST_PARAMS(10019, "请求参数不正确"),

    /**
     *	未定义异常
     */
    UNDEFINED_ERROR(10404, "系统未定义异常"),

    /**
     *	未定义异常
     */
    DEFINED_ERROR(10200, ""),

    /**
     *	操作失败异常
     */
    CZ_ERROR(10300, "操作失败"),

    /**
     *	操作成功
     */
    CC_SUCCESS(0,"成功"),

    /**
     *	操作失败
     */
    CC_ERROR(1,"失败");

    private final int code;

    private final String desc;

    private StatusCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
