package com.westlakefinancial.technology.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.westlakefinancial.technology.enums.CommonErrorCode;

import java.io.Serializable;
/**
 * @ClassName Result
 * @Description TODO
 * @Author james
 * @Date 2021/11/29 9:18
 * @Version 1.0
 **/

public class Result<T> implements Serializable {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 服务器当前时间戳
     */
    private Long timeMillis = System.currentTimeMillis();

    /**
     * 成功数据
     */
    private T data;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(Long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result() {
    }

    public Result(Boolean success, Long timeMillis, T data, String code, String message) {
        this.success = success;
        this.timeMillis = timeMillis;
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static Result ofSuccess() {
        Result result = new Result();
        result.success = true;
        return result;
    }

    public static Result ofSuccess(Object data) {
        Result result = new Result();
        result.success = true;
        result.setData(data);
        return result;
    }

    public static Result ofFail(String code, String msg) {
        Result result = new Result();
        result.success = false;
        result.code = code;
        result.message = msg;
        return result;
    }

    public static Result ofFail(String code, String msg, Object data) {
        Result result = new Result();
        result.success = false;
        result.code = code;
        result.message = msg;
        result.setData(data);
        return result;
    }

    public static Result ofFail(CommonErrorCode resultEnum) {
        Result result = new Result();
        result.success = false;
        result.code = resultEnum.getCode();
        result.message = resultEnum.getMessage();
        return result;
    }

    /**
     * 获取 json
     */
    public String buildResultJson() {
        GsonBuilder gsonBuilder=new  GsonBuilder(); //设定格式的
        Result result=new Result(this.success,this.timeMillis,this.data,this.code,this.message);
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss"); //设定date属性 有误json本身没有date类型
        Gson gson=gsonBuilder.create();
        String Gjson=gson.toJson(result);
        return Gjson;
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("succ", this.succ);
        jsonObject.put("code", this.code);
        jsonObject.put("ts", this.ts);
        jsonObject.put("msg", this.msg);
        jsonObject.put("data", this.data);
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);*/
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", timeMillis=" + timeMillis +
                ", data=" + data +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}