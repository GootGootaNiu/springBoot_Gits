package com.king.mystory.utils;

import java.io.Serializable;

/**
 * Json格式的数据进行响应
 */
public class JsonResult<E> implements Serializable {
    /**
     * 状态码
     */
    private Integer state; // 向前端响应状态信息的
    /**
     * 描述信息
     */
    private String message; // 向前端描述信息
    /**
     * 数据
     */
    private E data;
//
//
//    public JsonResult() {
////        super();
//    }
//
//    public JsonResult(Integer state) {
////        super();
//        this.state = state;
//    }
//
//    /**
//     * 出现异常时调用
//     */
//    public JsonResult(Throwable e) {
////        super();
//        // 获取异常对象中的异常信息
//        this.message = e.getMessage();
//    }
//
//    public JsonResult(Integer state, E data) {
////        super();
//        this.state = state;
//        this.data = data;
//    }
//
//    public Integer getState() {
//        return state;
//    }
//
//    public void setState(Integer state) {
//        this.state = state;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public E getData() {
//        return data;
//    }
//
//    public void setData(E data) {
//        this.data = data;
//    }
//


    public JsonResult() {
    }

    /**
     * 捕获异常把异常给到这个类中去
     * @param e
     */
    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}































