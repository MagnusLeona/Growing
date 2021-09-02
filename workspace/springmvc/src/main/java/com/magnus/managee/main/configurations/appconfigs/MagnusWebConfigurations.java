package com.magnus.managee.main.configurations.appconfigs;

import com.magnus.managee.main.common.interceptors.MagnusHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 入口配置类，个性化配置各种DispatcherServlet属性
 *
 */
@Configuration
@ComponentScan("com.magnus.managee.main")
@EnableTransactionManagement
public class MagnusWebConfigurations extends WebMvcConfigurationSupport{

    @Bean
    public MagnusHandlerInterceptor magnusHandlerInterceptor() {
        return new MagnusHandlerInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(magnusHandlerInterceptor()).addPathPatterns("/hello");
    }

//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("WEB-INF/jsp/", "classpath:/static/").resourceChain(true).addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
//    }


    @Override
    protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean("multipartResolver")
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
