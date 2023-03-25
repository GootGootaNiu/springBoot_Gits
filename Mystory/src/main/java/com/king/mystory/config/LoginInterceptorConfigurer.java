package com.king.mystory.config;

import com.king.mystory.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置类
 * 配置类中需要设置过滤器 实现WebMvcConfigurer
 * 然后实现接口中的init方法
 */
@Configuration // 表示一个配置类 交给spring容器去管理
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 第一步：使用多态的形式传入自己编写的拦截器
        HandlerInterceptor interceptor = new LoginInterceptor();
        // 第三步：这里使用一个集合 添加白名单 和 黑名单
        // 这里要表示放行的数据
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/*");


        // 第二步：完成拦截器的注册  因为现在是一个配置类 所以这里要这样写
        registry.addInterceptor(interceptor)
                // addpathpatterns 拦截所有的数据 这个是黑名单
                .addPathPatterns("/**")
                // 这个excludePathPatterns这个是访问白名单中的数据
                .excludePathPatterns(patterns);// 表示要拦截的url 是什么
    }
}
