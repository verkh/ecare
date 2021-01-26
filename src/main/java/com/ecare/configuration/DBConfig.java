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

/**
 * Database configurator
 * Provides needed beens to use DAO
 */
@Configuration
@EnableTransactionManagement
public class DBConfig {

    /**
     * Configures from application.properties file
     * @param driverClassName the driver that will be used for connection to database
     * @param dataSourceUrl an url which used for connection
     * @param userName login of user which has access
     * @param password user's password obviously
     * @return the DataSource which allows to configure Hibernate
     */
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

    /**
     * Creates session factory for DAO classes
     * @param dataSource the source which allows to establish connection
     * @param dialect dialect
     * @return The Bean of session factory
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource,
                                                  @Value("${hibernate.dialect}") String dialect,
                                                  @Value("${hibernate.showSql}") String showSql,
                                                  @Value("${hibernate.formatSql}") String formatSql
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

    /**
     * Creates a transaction manager for automated transaction handling
     * @param sessionFactory the session factory
     * @return a transaction manager
     */
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

    /**
     * @return DAO class which allows to manipulate data contracts's data
     */
    @Bean
    public IContractDAO getContractDAO() {
        return new ContractDAO();
    }

    /**
     * @return DAO class which allows to manipulate data plans's data
     */
    @Bean
    public IPlanDAO getPlanDAO() {
        return new PlanDAO();
    }

    /**
     * @return DAO class which allows to manipulate data options's data
     */
    @Bean
    public IOptionDAO getOptionDAO() { return new OptionDAO(); }
}
