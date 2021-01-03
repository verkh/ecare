package com.ecare.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

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

    @Bean
    public EntityManagerFactory getEntityManagerFactory(
            DataSource dataSource,
            @Value("${spring.datasource.driver-class-name}") String driverClassName,
            @Value("${spring.datasource.url}") String dataSourceUrl,
            @Value("${spring.datasource.username}") String userName,
            @Value("${spring.datasource.password}") String password,
            @Value("${hibernate.dialect}") String dialect)
    {
        try {
            LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
            lef.setDataSource(dataSource);
            lef.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        String[] packages = environment.getProperty("com.ecare").split(",");
            lef.setPackagesToScan("*.models*");

            Properties props = new Properties();
            props.put("javax.persistence.jdbc.url", dataSourceUrl);
            props.put("javax.persistence.jdbc.user", userName);
            props.put("javax.persistence.jdbc.password", password);
            props.put("javax.persistence.jdbc.driver", driverClassName);
            props.put("hibernate.show_sql", true);
            props.put("hibernate.format_sql", true);
            props.put("hibernate.dialect", dialect);

            lef.setJpaProperties(props);

            lef.afterPropertiesSet();
            return lef.getObject();
        }
        catch (Exception e)
        {
            String s = e.toString();
            String a = e.getMessage();
            System.out.println(e.toString() + ": " + e.getMessage());
        }
        return null;
    }
}
