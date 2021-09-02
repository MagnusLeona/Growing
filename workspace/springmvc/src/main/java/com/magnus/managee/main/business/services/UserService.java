package com.magnus.managee.main.business.services;

import com.magnus.managee.main.business.entity.User;
import com.magnus.managee.main.business.mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    public UserMapper mapper;

    @Autowired
    @Qualifier("batch")
    public SqlSession sqlSessionBatch;

    @Autowired
    public SqlSession sqlSession;

    @Autowired
    public SqlSessionFactory sqlSessionFactory;

    @Autowired
    @Qualifier("xml")
    public SqlSessionFactory sqlSessionFactoryXml;

    @Autowired
    public DataSourceTransactionManager transactionManager;

    @Transactional
    public void insertUsersBatch() throws Exception {
        logger.debug("--------------------------Logger.info");
        long start = System.currentTimeMillis();
        // 手动通过sqlSession去加载Mapper，和自动注入的Mapper，需要判断使用的是不是同一个SqlSession，否则可能效果不一样。
        UserMapper mapper = sqlSessionBatch.getMapper(UserMapper.class);
        try {
            for (int i = 0; i < 100000; i++) {
                Map map = new HashMap();
                map.put("id", i);
                map.put("name", i);
                map.put("description", i + 1);
                mapper.insertUser(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 手动执行批量的方法
//        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
//        transactionTemplate.execute(txStatus -> {
//
//            for (int i = 0; i < 100000; i++) {
//                Map map = new HashMap();
//                map.put("id", i);
//                map.put("name", i);
//                map.put("description", i + 1);
//                mapper.insertUser(map);
//            }
//            return null;
//        });
        long mid = System.currentTimeMillis();

        System.out.println(mid - start);
    }

    public void getUsers() {
        long start = System.currentTimeMillis();

        User user = mapper.getUserById(1);
        logger.debug(user.toString());
        long mid = System.currentTimeMillis();
        logger.info("========================= " + (mid - start));
        User user1 = mapper.getUserById(1);
        long end = System.currentTimeMillis();
        logger.debug(user == user1);
        logger.debug(user1.toString());
        logger.info("=========================" + (end - mid));

//        List<Map> user2 = mapper.getUser();
//        List<Map> user3 = mapper.getUser();
//        System.out.println(user2 == user3);
    }
}