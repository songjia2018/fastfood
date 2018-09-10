package com.jonas.fastfood.commonservice.utils;

public class JsonResult<T> {

    private int code;
    private String message;
    private T data;

    public JsonResult(){

    }
    public JsonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
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

    public static JsonResult success(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(ResultCode.SUCCESS);
        return jsonResult;
    }

    public static JsonResult failure(ResultCode resultCode){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(resultCode);
        return jsonResult;
    }

    public static JsonResult failure(ResultCode resultCode, Object data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(resultCode);
        jsonResult.setData(data);
        return jsonResult;
    }
}
