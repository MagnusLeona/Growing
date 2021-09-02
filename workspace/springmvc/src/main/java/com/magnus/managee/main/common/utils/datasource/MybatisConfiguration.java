package com.magnus.managee.main.common.utils.datasource;

import com.magnus.managee.main.business.mappers.UserMapper;
import net.sf.ehcache.management.Cache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.logging.log4j.core.Logger;
import org.ehcache.CacheManager;
import org.ehcache.core.Ehcache;
import org.mybatis.caches.ehcache.EhcacheCache;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * MapperScan原理： 引入了MapperScannerRegistrar，扫描指定包下的所有mapper，并注册成bean
 */
@Configuration
@MapperScan("com.magnus.managee.main.business.mappers")
public class MybatisConfiguration {

    @Autowired
    public DataSource dataSource;

    /**
     * Spring中配置mybatis的方法，通过SqlSessionFactoryBean去生成。
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PerpetualCache perpetualCache = new PerpetualCache("cache");
//        EhcacheCache ehcacheCache = new EhcacheCache("ehcache");
        sqlSessionFactoryBean.setCache(perpetualCache);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        SpringManagedTransactionFactory springManagedTransactionFactory = new SpringManagedTransactionFactory();
        Environment environment = new Environment("1", springManagedTransactionFactory, dataSource);
        configuration.setEnvironment(environment);
        sqlSessionFactoryBean.setConfiguration(configuration);
        // Mybatis配置日志系统，输出日志采用Log4j的形式。
        configuration.setLogImpl(Log4j2Impl.class);
        configuration.setCacheEnabled(true);
        SqlSessionFactory object = sqlSessionFactoryBean.getObject();
        return object;
    }

    /**
     * 最原始的操作Mybatis的方法，通过SqlSessionFactoryBuilder + 配置文件进行配置。
     * @return
     * @throws IOException
     */
    @Bean
    @Qualifier("xml")
    public SqlSessionFactory sqlSessionFactoryByXml() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");

        TransactionFactory springManagedTransactionFactory = new SpringManagedTransactionFactory();
        Environment environment = new Environment("sqlsession", springManagedTransactionFactory, dataSource);

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setEnvironment(environment);
        configuration.addMapper(UserMapper.class);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(configuration);
        return build;
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(), ExecutorType.SIMPLE);
    }

    @Bean
    @Qualifier("batch")
    public SqlSession sqlSessionBatch() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(), ExecutorType.BATCH);
    }
}

