package com.www.skeleton.web.common.vo;

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
     * 错误信息
     */
    private String errorMessage = null;

    private T data;

    public ApiResponse(){
        this(DEFAULT_CODE_SUCCESS, null);
    }

    public ApiResponse(Integer code, String errorMessage){
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T result) {
        this.data = result;
    }
}
