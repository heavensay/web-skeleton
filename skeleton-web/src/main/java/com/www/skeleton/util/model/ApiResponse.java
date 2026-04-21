package com.www.skeleton.util.model;

/**
 * 统一数据格式
 * code 10000:请求成功，业务数据在data中;>10000:请求错误，请求错误之后会有对应的错误提示信息message，以及详细信息detail
 * @author lijianyu
 * @date 2019/3/19 16:53
 */
public class ApiResponse<T> {
    private static final Integer DEFAULT_CODE_SUCCESS = 10000;

    /**
     * 10000:成功;>10000:失败;
     */
    private Integer code = DEFAULT_CODE_SUCCESS;

    /**
     * 人性化错误消息。国际化信息
     */
    private String message;

    /**
     * http状态码
     */
    private Integer status;

    /**
     * 错误详细信息
     */
    private String detail;

    private T data;

    public ApiResponse(){
        this(DEFAULT_CODE_SUCCESS, null);
    }

    public ApiResponse(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T result) {
        this.data = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
