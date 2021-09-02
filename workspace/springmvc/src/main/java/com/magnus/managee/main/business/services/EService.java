package com.magnus.managee.main.business.services;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

@Component
public class EService implements SmartInitializingSingleton {

    @Override
    public void afterSingletonsInstantiated() {
    }
}
