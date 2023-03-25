package com.king.mystory.controller;


import com.king.mystory.controller.ex.*;
import com.king.mystory.service.ex.*;
import com.king.mystory.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 用来表示控制层的基类
 */
public class BaseController {

    /**
     * 操作成功的状态码
     */
    public static final int OK = 200;

    // 请求处理方法 这个方法的返回值就是需要传递给前端的数据
    // 自动将异常对象传递给此方法的参数列表上
    @ExceptionHandler({ServiceException.class, FileUploadIOException.class}) // 用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        // 创建对象 注意这里把e 给传入进去
        JsonResult<Void> result = new JsonResult<>(e);
        // 用户名重复的异常 UsernameDuplicatedException
        if (e instanceof UsernameDuplicatedException) {
            result.setState(40000);
            result.setMessage("用户名重复!!!!!");

//            注册添加数据到数据库中的时候出现的未知的异常
        } else if (e instanceof InsertException) {
            result.setState(40001);
            result.setMessage("出现未知的异常!!!!!");

        } else if (e instanceof UserNotFoundException) {
            result.setState(40002);
            result.setMessage("用户数据不存在的异常");

            // 用户输入密码 密码错误产生的异常
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(40003);
            result.setMessage("用户密码错误异常");

            // 用户输入的密码 和 数据库中的密码不一致
        } else if (e instanceof UpdateException) {
            result.setState(40004);
            result.setMessage("更新数据时产生的异常");

//        controller 中产生的异常
            // FileEmptyException 上传文件出现异常
        } else if (e instanceof FileEmptyException) {
            result.setState(50001);
            result.setMessage("上传文件出现异常");

            // FileSizeException 文件大小异常
        } else if (e instanceof FileSizeException) {
            result.setState(50002);
            result.setMessage("上传文件太大了");

            //FileTypeException 上传文件类型出现问题
        } else if (e instanceof FileTypeException) {
            result.setState(50003);
            result.setMessage("文件类型出现问题");

            // FileStateException 上传文件状态出现异常
        } else if (e instanceof FileStateException) {
            result.setState(50004);
            result.setMessage("上传文件状态出现异常");

            // 上传文件读写异常
        } else if (e instanceof FileUploadIOException) {
            result.setState(50005);
            result.setMessage("文件读写出现异常");

        } else if (e instanceof AddressCountLimitException){
            result.setState(50006);
            result.setMessage("用户的收获地址超出上线");
        }

        return result;
    }

    /**
     * 获取session 对象中的uid
     *
     * @param session session 对象
     * @return 当前登录的用户uid 的值
     */
    protected final Integer getuidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }


    /**
     * 获取当前登录用户的username
     *
     * @param session session 对象
     * @return 当前登录用户的用户名
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}




























