package com.ecare.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class DBConfig {

    @Bean
    public HikariConfig hikariConfig(
            @Value("${spring.datasource.driver-class-name}") String driverClassName,
            @Value("${spring.datasource.url}") String dataSourceUrl,
            @Value("${spring.datasource.username}") String userName,
            @Value("${spring.datasource.password}") String password
    )
    {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(dataSourceUrl);
        config.setUsername(userName);
        config.setPassword(password);

        config.setMaximumPoolSize(2);
        config.setConnectionTestQuery("select 1");

        return config;
    }


    @Bean
    public DataSource getDataSource(HikariConfig hikariConfig) { return new HikariDataSource(hikariConfig); }

//    @Bean
//    public SpringLiquibase liquibase(DataSource dataSource) {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setChangeLog("classpath:liquibase-changelog.yaml");
//        liquibase.setDataSource(dataSource);
//        return liquibase;
//    }
}
