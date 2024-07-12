package com.rainbowsea.ssm.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

// 标注该类式一个配置文件类
@Configuration
// 组件扫描
@ComponentScan("com.rainbowsea.ssm.service")
// 属性配置文件位置
@PropertySource("classpath:jdbc.properties")
// 导入其他配置到Spring 配置
@Import({MyBatisConfig.class,DataSourceConfig.class})
// 开启事务管理器
@EnableTransactionManagement
public class SpringConfig {


    // 在 DataSourceConfig中添加事务管理器对象
    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
