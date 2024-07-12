package com.rainbowsea.ssm.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * 数据源配置
 */
@Configuration
public class DataSourceConfig {

    @Value("${jdbc.driver}")
    private String driver;


    @Value("${jdbc.url}")
    private String url;


    @Value("${jdbc.username}")
    private String username;


    @Value("${jdbc.password}")
    private String password;

    public DataSourceConfig() {
    }

    public DataSourceConfig(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }


    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }
}
