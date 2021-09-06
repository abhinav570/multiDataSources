package com.learn.h2Demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = {"com.learn.h2Demo.Repo.primary"})
public class PrimaryDataSourceConfiguration {

    @Primary
    @Bean(name = "primaryDataSourceProperties")
    @ConfigurationProperties("spring.datasource-primary")
    public DataSourceProperties primaryDataSourceProperties() {
        DataSourceProperties dataSourceProperties=new DataSourceProperties();
        dataSourceProperties.setUrl("jdbc:h2:mem:h1");
        dataSourceProperties.setUsername("sa");
        dataSourceProperties.setPassword("sa");
        return dataSourceProperties;
    }

    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties("spring.datasource-primary.configuration")
    public DataSource primaryDataSource(@Qualifier("primaryDataSourceProperties") DataSourceProperties primaryDataSourceProperties) {
        return primaryDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            EntityManagerFactoryBuilder primaryEntityManagerFactoryBuilder, @Qualifier("primaryDataSource") DataSource primaryDataSource) {

        Map<String, String> primaryJpaProperties = new HashMap<>();
        primaryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        primaryJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");

        return primaryEntityManagerFactoryBuilder
                .dataSource(primaryDataSource)
                .packages("com.learn.h2Demo.entity.primary")
                .persistenceUnit("primaryDataSource")
                .properties(primaryJpaProperties)
                .build();
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {

        return new JpaTransactionManager(primaryEntityManagerFactory);
    }
}