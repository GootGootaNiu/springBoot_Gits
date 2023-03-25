package com.king.mystory.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器 登录的时候添加的拦截器 他是用来处理数据的
 * 注意： 定义一个类 去实现这个接口  HandlerIntercewptor 这个类
 */
public class LoginInterceptor implements HandlerInterceptor {
    // 调用所有处理请求的方法之气那被自动调用执行的方法

    /**
     * 检车全局session 对象中是否有uid  如果有则放行 如果没有重定向到登录页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器（url + controller 映射）
     * @return 返回值为true  表示放行当前请求 如果返回值为false 则表示拦截当前请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//        httpServletRequest 对象来获取session 对象
        Object uid = request.getSession().getAttribute("uid");
        // 这里判断session域中有没有 uid  如果有就放行
        if (uid == null) {
            // 说明用户没有登录过系统 重定向到login.html页面
            // 注意 response.senRedirect 重定向的意思
            response.sendRedirect("/web/login.html");
            // 结束后续的调用
            return false;
        }
        // 如果uid 不为空 继续放行
        return true;
    }
    // 在modelAndview 对象
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
