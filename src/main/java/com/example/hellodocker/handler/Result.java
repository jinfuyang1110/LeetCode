package com.example.hellodocker.handler;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yjf
 * @date 2022/2/21
 * @description
 */
public class Result<T> {
    private ResponseCommonParam header = new ResponseCommonParam();

    /**
     *	响应数据
     */
    private T result;

    public Result() {
        super();
    }

    public Result(Integer status, T result) {
        super();
        this.header.status = status;
        this.result = result;
    }

    public Result(Integer status, String errors) {
        super();
        this.header.status = status;
        this.header.errors = errors;
    }

    Result(StatusCode statusCode) {
        super();
        this.header.status = statusCode.getCode();
        this.header.errors = statusCode.getDesc();
    }

    Result(StatusCode statusCode, T result) {
        super();
        this.header.status = statusCode.getCode();
        this.result = result;
    }

    Result(StatusCode statusCode, String errors) {
        super();
        this.header.status = statusCode.getCode();
        if (StringUtils.isNotEmpty(errors)) {
            this.header.errors = statusCode.getDesc() + "：" + errors;
        } else {
            this.header.errors = statusCode.getDesc();
        }
    }
    /**
     * <pre>
     *	返回成功消息，无响应结果
     * </pre>
     *
     * @return
     */
    public static Result success() {
        return new Result(StatusCode.SUCCESS);
    }

    /**
     * <pre>
     *	返回成功消息，有响应结果
     * </pre>
     *
     * @param result
     * @return
     */
    public static <T> Result<T> success(T result) {
        return new Result<T>(StatusCode.SUCCESS, result);
    }

    /**
     * <pre>
     * 	返回自定义错误信息
     * </pre>
     * @param errors
     * @return
     */
    public static Result definedError(String errors) {
        return new Result(StatusCode.DEFINED_ERROR, errors);
    }


    public static Result czError(String errors) {
        return new Result(StatusCode.CZ_ERROR, errors);
    }

    /**
     * <pre>
     *	系统未定义异常
     * </pre>
     *
     * @param errors
     * @return
     */
    public static Result undefinedError(String errors) {
        return new Result(StatusCode.UNDEFINED_ERROR, errors);
    }

    /**
     * <pre>
     *	请求参数错误
     * </pre>
     *
     * @param errors 详细描述
     * @return
     */
    public static Result invalidRequestParams(String errors) {
        return new Result(StatusCode.INVAILD_REQUEST_PARAMS, errors);
    }

    /**
     * <pre>
     *	不合法的appId
     * </pre>
     *
     * @return
     */
    public static Result invalidAppId() {
        return new Result(StatusCode.INVALID_APPID);
    }

    /**
     * <pre>
     *	不合法的认证会话
     * </pre>
     *
     * @return
     */
    public static Result invalidAccessToken() {
        return new Result(StatusCode.INVALID_ACCESS_TOKEN);
    }

    public ResponseCommonParam getHeader() {
        return header;
    }

    public void setHeader(ResponseCommonParam header) {
        this.header = header;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }




    /**
     * <pre>
     *	通用返回参数
     * </pre>
     *
     */
    public class ResponseCommonParam {

        private Integer status;

        private String errors;

        private Integer coordType;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getErrors() {
            return errors;
        }

        public void setErrors(String errors) {
            this.errors = errors;
        }

        public Integer getCoordType() {
            return coordType;
        }

        public void setCoordType(Integer coordType) {
            this.coordType = coordType;
        }

    }
}
