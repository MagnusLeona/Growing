package com.magnus.managee.main.configurations.mapping;

import com.magnus.managee.main.business.controllers.ConfigueredMappingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 给RequestMappingHandlerMapping自定义注入映射规则。/my的请求全部分发到ConfigueredMappingController.index方法上。
 */
@Configuration
public class MyHandlerMappingConfiguration {

    @Autowired
    public void setHandlerMapping(RequestMappingHandlerMapping requestMappingHandlerMapping, ConfigueredMappingController configueredMappingController) throws NoSuchMethodException {
        RequestMappingInfo build = RequestMappingInfo.paths("/my").methods(RequestMethod.GET).build();
        Method index = ConfigueredMappingController.class.getMethod("index", HttpServletResponse.class);
        requestMappingHandlerMapping.registerMapping(build, configueredMappingController, index);
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }
}
