package com.king.mystory

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.MultipartConfigFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.unit.DataSize
import org.springframework.util.unit.DataUnit

import javax.servlet.MultipartConfigElement

@Configuration // 表示配置类
@SpringBootApplication
// @MapperScan  主键指定当前项目中Mapper接口路径的位置
// 在项目启动的时候会自动的去加载所有的文件
@MapperScan("com.king.mystory.mapper")
class MystoryApplication {

    static void main(String[] args) {
        SpringApplication.run(MystoryApplication, args)
    }

    /**
     * 这里是头像上传 调试头像的文件大小的
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        // 创建一个配置的工厂类对象
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置需要创建的对象的信息
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(15,DataUnit.MEGABYTES));

        //通过工厂类来创建MultipartConfigElement 对象
        return factory.createMultipartConfig();
    }
}
