package com.vishrut.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JPAConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean emf(){
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(getDataSource());
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPackagesToScan("com.vishrutspring.entity");


        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        properties.put("hibernate.hbm2ddl.auto","create");
        //properties.put("hibernate.show_sql","true");
        properties.put("hibernate.jdbc.batch_size", "5");
        properties.put("hibernate.order_inserts","true");
        emf.setJpaProperties(properties);

        return emf;
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/employee_directory");
        ds.setUsername("root");
        ds.setPassword("2wsx@WSX");

        return ds;
    }

    @Bean
    public PlatformTransactionManager txManager(EntityManagerFactory emf){
        JpaTransactionManager txm = new JpaTransactionManager(emf);
        return txm;
    }
}
