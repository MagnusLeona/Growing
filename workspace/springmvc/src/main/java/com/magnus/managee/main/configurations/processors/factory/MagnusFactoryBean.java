package com.magnus.managee.main.configurations.processors.factory;

import com.magnus.managee.main.business.services.CService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MagnusFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new CService();
    }

    @Override
    public Class<?> getObjectType() {
        return CService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
