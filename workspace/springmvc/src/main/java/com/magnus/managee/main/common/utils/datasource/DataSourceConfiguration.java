package com.magnus.managee.main.common.utils.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.magnus.managee.support.dicts.DataSourceEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Properties;

@Configuration
@PropertySource("classpath:datasource/data.properties")
public class DataSourceConfiguration {
    @Value("${datasource.user}")
    public String user;
    @Value("${datasource.url}")
    public String url;
    @Value("${datasource.password}")
    public String password;
    @Value("${datasource.driver}")
    public String driver;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        Properties properties = new Properties();
        properties.put(DataSourceEnum.DRUID_URL.value(), url);
        properties.put(DataSourceEnum.DRUID_USERNAME.value(), user);
        properties.put(DataSourceEnum.DRUID_PASSWORD.value(), password);
        properties.put(DataSourceEnum.DRUID_MAXACTIVE, 10);
        properties.put(DataSourceEnum.DRUID_MAXIDLE, 2);
        properties.put(DataSourceEnum.DRUID_INITIALSIZE, 1);
        druidDataSource.configFromPropety(properties);
        return druidDataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
