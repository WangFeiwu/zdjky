package com.foxconn.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 开启Mvc，自动注入spring容器
 * 当一个类实现了ApplicationContextAware之后，这个类就可以方便获取ApplicationContext中的所有bean
 * 实现WebMvcConfigurer配置视图解析器和静态资源
 */

@Configuration
@EnableWebMvc//等价于<mvc:annotation-driven/>
public class MvcConfiguration implements ApplicationContextAware,WebMvcConfigurer {

    //Spring容器
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    /**
     * 静态资源配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
    }

    /**
     * 定义默认的请求处理器
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

    /**
     * 创建viewResolver
     * @return
     */
/*
    @Bean("viewResolver")
    public ViewResolver createResolver(){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        //设置Spring容器
        viewResolver.setApplicationContext(applicationContext);
        //取消缓存
        viewResolver.setCache(false);
        //视图解析前缀
        viewResolver.setPrefix("/WEB-INF/html/");
        //视图解析后缀
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
*/

}
