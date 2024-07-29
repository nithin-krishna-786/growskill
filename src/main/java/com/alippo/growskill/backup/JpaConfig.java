package com.alippo.growskill.backup;

//impsort java.util.Properties;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.activation.DataSource;
//
//@Configuration
//@EnableJpaRepositories
//public class JpaConfig {
//    
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource((javax.sql.DataSource) dataSource());
//        factoryBean.setPackagesToScan("com.alippo.growskill.entity");
//        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        factoryBean.setJpaProperties(hibernateProperties());
//        return factoryBean;
//    }
//    
//    @Bean
//    public DataSource dataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.url(getProperty("spring.datasource.url"));
//        dataSourceBuilder.username(getProperty("spring.datasource.username"));
//        dataSourceBuilder.password(getProperty("spring.datasource.password"));
//        dataSourceBuilder.driverClassName(getProperty("spring.datasource.driver-class-name"));
//        return (DataSource) dataSourceBuilder.build();
//    }
//    
//    private String getProperty(String key) {
//        return System.getProperty(key);
//    }
//    
//    @Bean
//    public Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        properties.setProperty("hibernate.show_sql", "true");
//        return properties;
//    }
//}