package com.www.skeleton.util.model;

/**
 * @author lijianyu
 * @date 2019/3/19 16:53
 */
public class ApiResponse<T> {
    private static final Integer DEFAULT_CODE_SUCCESS = 0;

    /**
     * 0:成功;>0:失败;
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
