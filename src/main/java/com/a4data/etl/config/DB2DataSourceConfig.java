/*package com.a4data.etl.config;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

*//**
 * @author Harish Gudivada
 * 
 *//*
@Configuration
@EnableJpaRepositories(
  basePackages = "com.a4data.etl.repository",
        entityManagerFactoryRef = "securityEntityManagerFactory",
        transactionManagerRef = "securityTransactionManager"
)
public class DB2DataSourceConfig
{
       @Autowired
       private Environment env;
  
       @Bean
       @ConfigurationProperties(prefix = "spring.ds_mysql")
       public DataSourceProperties dataSourceProperties() {
           return new DataSourceProperties();
       }
    
       @Bean
       public DataSource dataSource() {
            DataSourceProperties securityDataSourceProperties = dataSourceProperties();
            return DataSourceBuilder.create()
           .driverClassName(securityDataSourceProperties.getDriverClassName())
           .url(securityDataSourceProperties.getUrl())
           .username(securityDataSourceProperties.getUsername())
           .password(securityDataSourceProperties.getPassword())
           .build();
       }
    
       @Bean
       public PlatformTransactionManager transactionManager()
       {
           EntityManagerFactory factory = securityEntityManagerFactory().getObject();
           return new JpaTransactionManager(factory);
       }

       @Bean
       public LocalContainerEntityManagerFactoryBean securityEntityManagerFactory()
       {
           LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
           factory.setDataSource(dataSource());
           factory.setPackagesToScan(new String[]{"com.a4data.etl.models"});
           factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        
           Properties jpaProperties = new Properties();
           jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
           jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
           factory.setJpaProperties(jpaProperties);
        
           return factory;
       }
    
       @Bean
       public DataSourceInitializer securityDataSourceInitializer() 
       {
           DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
           dataSourceInitializer.setDataSource(dataSource());
           ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
           databasePopulator.addScript(new ClassPathResource("security-data.sql"));
           dataSourceInitializer.setDatabasePopulator(databasePopulator);
           dataSourceInitializer.setEnabled(env.getProperty("datasource.security.initialize", Boolean.class, false));
           return dataSourceInitializer;
       }   
}*/