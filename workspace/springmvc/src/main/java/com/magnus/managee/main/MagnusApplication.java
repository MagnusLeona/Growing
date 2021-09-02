package com.magnus.managee.main;

import com.magnus.managee.main.business.services.AService;
import com.magnus.managee.main.business.services.BService;
import com.magnus.managee.main.business.services.CService;
import com.magnus.managee.main.business.services.DService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.magnus.managee.main")
@EnableAspectJAutoProxy
public class MagnusApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MagnusApplication.class);
        AService bean = annotationConfigApplicationContext.getBean(AService.class);
        BService bean1 = annotationConfigApplicationContext.getBean(BService.class);
        CService bean2 = annotationConfigApplicationContext.getBean(CService.class);
        DService bean3 = annotationConfigApplicationContext.getBean(DService.class);

        bean.test();
    }
}
