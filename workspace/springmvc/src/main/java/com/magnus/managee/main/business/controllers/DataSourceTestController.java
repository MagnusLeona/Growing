package com.magnus.managee.main.business.controllers;

import com.magnus.managee.main.business.mappers.UserMapper;
import com.magnus.managee.main.business.services.UserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DataSourceTestController implements ApplicationContextAware {
    @Autowired
    public UserService userService;

    @Autowired
    public SqlSession sqlSession;

    public ApplicationContext applicationContext;

    @RequestMapping("/query")
    @ResponseBody
    public List<Map<String, Object>> get() {
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<Map> user1 = mapper.getUser();
            System.out.println(user1);
         } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/batch")
    @ResponseBody
    public String batch() throws Exception {
        ApplicationContext applicationContext = this.applicationContext;
        try {
            long start = System.currentTimeMillis();
            userService.insertUsersBatch();
            long l = System.currentTimeMillis();
            System.out.println(l - start + "     time cost");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return "success";
    }


    @RequestMapping("/cache")
    public String cache() throws InterruptedException {
        userService.getUsers();
        return "success";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
