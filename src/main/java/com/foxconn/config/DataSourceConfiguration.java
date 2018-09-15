package com.foxconn.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
@MapperScan("com.foxconn.mapper")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;
    @Value("${jdbc.filters}")
    private String jdbcFilters;
    @Value("${jdbc.maxActive}")
    private int jdbcMaxActive;
    @Value("${jdbc.initialSize}")
    private int jdbcInitialSize;
    @Value("${jdbc.maxWait}")
    private int jdbcMaxWait;
    @Value("${jdbc.minIdle}")
    private int jdbcMinIdle;
    @Value("${jdbc.timeBetweenEvictionRunsMillis}")
    private int jdbcTimeBetweenEvictionRunsMillis;
    @Value("${jdbc.minEvictableIdleTimeMillis}")
    private int jdbcMinEvictableIdleTimeMillis;
    @Value("${jdbc.validationQuery}")
    private String jdbcValidationQuery;
    @Value("${jdbc.testWhileIdle}")
    private boolean jdbcTestWhileIdle;
    @Value("${jdbc.testOnBorrow}")
    private boolean jdbcTestOnBorrow;
    @Value("${jdbc.testOnReturn}")
    private boolean jdbcTestOnReturn;
    @Value("${jdbc.poolPreparedStatements}")
    private boolean jdbcPoolPreparedStatements;
    @Value("${jdbc.maxOpenPreparedStatements}")
    private int jdbcMaxOpenPreparedStatements;

    @Bean(name = "dataSource")
    public DruidDataSource createDataSource() throws SQLException {
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        dataSource.setFilters(jdbcFilters);
        dataSource.setMaxActive(jdbcMaxActive);
        dataSource.setInitialSize(jdbcInitialSize);
        dataSource.setMaxWait(jdbcMaxWait);
        dataSource.setMinIdle(jdbcMinIdle);
        dataSource.setTimeBetweenEvictionRunsMillis(jdbcTimeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(jdbcMinEvictableIdleTimeMillis);
        dataSource.setValidationQuery(jdbcValidationQuery);
        dataSource.setTestWhileIdle(jdbcTestWhileIdle);
        dataSource.setTestOnBorrow(jdbcTestOnBorrow);
        dataSource.setTestOnReturn(jdbcTestOnReturn);
        dataSource.setPoolPreparedStatements(jdbcPoolPreparedStatements);
        dataSource.setMaxOpenPreparedStatements(jdbcMaxOpenPreparedStatements);
        return dataSource;
    }
}
