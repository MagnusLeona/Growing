package com.magnus.managee.main;

import com.magnus.managee.main.configurations.appconfigs.MagnusWebConfigurations;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * MVC入口类，注册DispatcherServlet。SpringMVC启动的两种方式： 一个是继承WebApplicationInitializer接口，然后实现onStartUp方法（在Tomcat的servletContext中注册DispatcherSerlvet，并设置立即启动和映射路径）
 * 另外一个就是使用AbstractAnnotationConfigDispatcherServletInitializer，并重写里面的各种方法（传入配置类，定义映射路径，设置多媒体等等方法）
 */
//public class MagnusWebApplication extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[]{MagnusWebConfigurations.class};
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[]{MagnusWebConfigurations.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        registration.setMultipartConfig(new MultipartConfigElement("C://tmp"));
//    }
//}

public class MagnusWebApplication implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(MagnusWebConfigurations.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        ServletRegistration.Dynamic mvc = servletContext.addServlet("mvc", dispatcherServlet);
        mvc.setLoadOnStartup(1);
        mvc.addMapping("/");
        mvc.setMultipartConfig(new MultipartConfigElement("C://tmp"));

    }
}