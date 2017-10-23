//package com.deepblue.middleware.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableJpaRepositories("com.deepblue.middleware.repository.palmset")
//@EnableTransactionManagement
//public class PalmsetDatabaseConfiguration {
//
//    private final Logger log = LoggerFactory.getLogger(PalmsetDatabaseConfiguration.class);
//
//    private final Environment env;
//
//    public PalmsetDatabaseConfiguration(Environment env) {
//        this.env = env;
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource.palmset")
//    public DataSource dataSource() {
//        return new com.alibaba.druid.pool.DruidDataSource();
//    }
//
//}
