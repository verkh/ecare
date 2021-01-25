package com.ecare.configuration;

import com.ecare.dao.*;
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
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource,
                                                  @Value("${hibernate.dialect}") String dialect,
                                                  @Value("${hibernate.showSql}") String showSql,
                                                  @Value("${hibernate.formatSql}") String formatSql,
    ) {
        // fill additional properties for Hibernate
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.format_sql", formatSql);

        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[] { "com.ecare" });
        sessionFactory.setHibernateProperties(properties);

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalSessionFactoryBean sessionFactory) {
        final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());
        return transactionManager;
    }

    /**
     * @return DAO class which allows to manipulate data user's data
     */
    @Bean
    public IUserDAO getUserDAO() {
        return new UserDAO();
    }
    @Bean
    public IContractDAO getContractDAO() {
        return new ContractDAO();
    }
    @Bean
    public IPlanDAO getPlanDAO() {
        return new PlanDAO();
    }
    @Bean
    public IOptionDAO getOptionDAO() { return new OptionDAO(); }
}
