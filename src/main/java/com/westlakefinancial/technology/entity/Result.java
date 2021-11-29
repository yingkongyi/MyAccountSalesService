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
    private Boolean succ;

    /**
     * 服务器当前时间戳
     */
    private Long ts = System.currentTimeMillis();

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
    private String msg;

    public Boolean getSucc() {
        return succ;
    }

    public void setSucc(Boolean succ) {
        this.succ = succ;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result() {
    }

    public Result(Boolean succ, Long ts, T data, String code, String msg) {
        this.succ = succ;
        this.ts = ts;
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static Result ofSuccess() {
        Result result = new Result();
        result.succ = true;
        return result;
    }

    public static Result ofSuccess(Object data) {
        Result result = new Result();
        result.succ = true;
        result.setData(data);
        return result;
    }

    public static Result ofFail(String code, String msg) {
        Result result = new Result();
        result.succ = false;
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static Result ofFail(String code, String msg, Object data) {
        Result result = new Result();
        result.succ = false;
        result.code = code;
        result.msg = msg;
        result.setData(data);
        return result;
    }

    public static Result ofFail(CommonErrorCode resultEnum) {
        Result result = new Result();
        result.succ = false;
        result.code = resultEnum.getCode();
        result.msg = resultEnum.getMessage();
        return result;
    }

    /**
     * 获取 json
     */
    public String buildResultJson() {
        GsonBuilder gsonBuilder=new  GsonBuilder(); //设定格式的
        Result result=new Result(this.succ,this.ts,this.data,this.code,this.msg);
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
                "succ=" + succ +
                ", ts=" + ts +
                ", data=" + data +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}