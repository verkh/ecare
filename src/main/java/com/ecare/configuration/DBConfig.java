package com.ecare.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DBConfig {

    @Bean
    public DataSource getDataSource(
            @Value("${spring.datasource.driver-class-name}") String driverClassName,
            @Value("${spring.datasource.url}") String dataSourceUrl,
            @Value("${spring.datasource.username}") String userName,
            @Value("${spring.datasource.password}") String password
    )
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, @Value("${hibernate.dialect}") String dialect) {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[] { "com.ecare" });
        sessionFactory.setHibernateProperties(additionalProperties(dialect));

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalSessionFactoryBean sessionFactory) {
        final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());
        return transactionManager;
    }

    Properties additionalProperties(String dialect) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);

        return properties;
    }
}
