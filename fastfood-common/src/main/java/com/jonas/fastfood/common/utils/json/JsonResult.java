package com.jonas.fastfood.common.utils.json;

import com.jonas.fastfood.common.constants.ResultCode;

public class JsonResult<T> {

    private int code;
    private String message;
    private T data;

    public JsonResult(){

    }

    public JsonResult(ResultCode code, String message) {
        this.code = code.code();
        this.message = message;
    }

    public JsonResult(ResultCode code, String message,T data) {
        this.code = code.code();
        this.message = message;
        this.data = data;
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

    public static JsonResult success(ResultCode resultCode,String msg){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(ResultCode.SUCCESS);
        jsonResult.setMessage(msg);
        return jsonResult;
    }

    public static JsonResult success(Object data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(ResultCode.SUCCESS);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult success(ResultCode resultCode){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(resultCode);
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

    // ---------- 在 service 中请只使用下面的静态方法就好了. 不要 new JsonResult()... 这样操作 ----------

    /**
     * 请求成功且不需要返回数据, 当返回 "地址添加成功" 这一类说明时
     */
    public static <T> JsonResult<T> success(String msg) {
        return new JsonResult<T>(ResultCode.SUCCESS, msg);
    }

    /**
     * 请求成功且有返回数据时
     */
    public static <T> JsonResult<T> success(String msg, T data) {
        return new JsonResult<T>(ResultCode.SUCCESS, msg, data);
    }

    /**
     * 参数错误
     */
    public static <T> JsonResult<T> badRequest(String msg) {
        // return new JsonResult<T>(JsonCode.BAD_REQUEST, msg);
        return new JsonResult<T>(ResultCode.DATA_IS_WRONG, msg);
    }

    /**
     * 未登录
     */
    public static <T> JsonResult<T> notLogin(String msg) {
        return new JsonResult<T>(ResultCode.USER_NOT_LOGGED_IN, msg);
    }

    /**
     * 无权限
     */
    public static <T> JsonResult<T> notPermission(String msg) {
        // return new JsonResult<T>(JsonCode.NOT_PERMISSION, msg);
        return new JsonResult<T>(ResultCode.PERMISSION_NO_ACCESS, msg);
    }

    /**
     * 未找到
     */
    public static <T> JsonResult<T> notFound(String msg) {
        return new JsonResult<T>(ResultCode.RESULE_DATA_NONE, msg);
    }

    /**
     * 请求失败
     */
    public static <T> JsonResult<T> fail(String msg) {
        return new JsonResult<T>(ResultCode.SYSTEM_INNER_ERROR, msg);
    }

    /**
     * 请求失败
     */
    public static <T> JsonResult<T> fail(ResultCode jsonCode, String msg) {
        return new JsonResult<T>(jsonCode, msg);
    }
}
