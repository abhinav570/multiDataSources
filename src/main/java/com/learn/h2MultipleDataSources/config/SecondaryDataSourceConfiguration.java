package com.learn.h2MultipleDataSources.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager",
        basePackages = {"com.learn.h2MultipleDataSources.Repo.secondary"})
public class SecondaryDataSourceConfiguration {

    @Value("${spring.datasource.secondary.username}")
    String username;

    @Value("${spring.datasource.secondary.password}")
    String password;

    @Value("${spring.datasource.secondary.url}")
    String url;

    @Value("${hibernate.hbm2ddl.secondary.auto}")
    String hbm2ddl;

    @Value("${org.hibernate.dialect.secondary.H2Dialect}")
    String dialect;

    @Value("${secondary.entity.package}")
    String entityPackage;

    @Value("${secondary.entity.package}")
    String repoPackage;

    @Bean(name = "secondaryDataSourceProperties")
    @ConfigurationProperties("spring.datasource-secondary")
    public DataSourceProperties secondaryDataSourceProperties() {
        DataSourceProperties dataSourceProperties=new DataSourceProperties();
        dataSourceProperties.setUrl(url);
        dataSourceProperties.setUsername(username);
        dataSourceProperties.setPassword(password);
        return dataSourceProperties;
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties("spring.datasource-secondary.configuration")
    public DataSource secondaryDataSource(@Qualifier("secondaryDataSourceProperties") DataSourceProperties secondaryDataSourceProperties) {
        return secondaryDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
            EntityManagerFactoryBuilder secondaryEntityManagerFactoryBuilder, @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {

        Map<String, String> secondaryJpaProperties = new HashMap<>();
        secondaryJpaProperties.put("hibernate.dialect", dialect);
        secondaryJpaProperties.put("hibernate.hbm2ddl.auto", hbm2ddl);

        return secondaryEntityManagerFactoryBuilder
                .dataSource(secondaryDataSource)
                .packages(entityPackage)
                .persistenceUnit("secondaryDataSource")
                .properties(secondaryJpaProperties)
                .build();
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {

        return new JpaTransactionManager(secondaryEntityManagerFactory);
    }
}
