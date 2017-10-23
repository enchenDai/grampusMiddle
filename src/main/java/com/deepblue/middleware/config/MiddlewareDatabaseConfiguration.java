
package com.deepblue.middleware.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("com.deepblue.middleware.repository.middleware")
@EnableTransactionManagement
public class MiddlewareDatabaseConfiguration {

    private final Logger log = LoggerFactory.getLogger(MiddlewareDatabaseConfiguration.class);

    private final Environment env;

    public MiddlewareDatabaseConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.middleware")
    public DataSource dataSource() {
        return new com.alibaba.druid.pool.DruidDataSource();
    }

}
