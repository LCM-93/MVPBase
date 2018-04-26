package com.lcm.app.data.entity;

/**
 * Created by hunji on 2016/9/1.
 * desc:
 */
public class HttpBaseResult<T> {

    /**
     * server_time : 1477284985
     * success : true
     * message :
     * data :
     */

    private int server_time;
    private boolean success;
    private String message;
    private T data;
    private String error_code;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
        this.server_time = server_time;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public void setData(T data) {
        this.data = data;
    }
}
