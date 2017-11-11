package com.mrpehlivan.SpringBootCRUD.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration implements EnvironmentAware {

    private static final String PROP_URL = "url";
    private static final String PROP_DB_NAME = "databaseName";
    private static final String PROP_SERVER_NAME = "serverName";
    private static final String PROP_DS_CLASS = "dataSourceClassName";
    private static final String PROP_USER = "username";
    private static final String PROP_PASSWORD = "password";

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    private Environment env;

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
    }

    public HikariConfig getDataSourceConfig(String prefix) {
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, prefix);

        log.debug("Configuring datasource with prefix {}", prefix);
        if (propertyResolver.getProperty(PROP_URL) == null && propertyResolver.getProperty(PROP_DB_NAME) == null) {
            log.error(
                    "Your database connection pool configuration is incorrect! The application"
                            + "cannot start. Please check your Spring profile, current profiles are: {}",
                    Arrays.toString(env.getActiveProfiles()));

            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(propertyResolver.getProperty(PROP_DS_CLASS));
        if (propertyResolver.getProperty(PROP_URL) == null || "".equals(propertyResolver.getProperty(PROP_URL))) {
            config.addDataSourceProperty(PROP_DB_NAME, propertyResolver.getProperty(PROP_DB_NAME));
            config.addDataSourceProperty(PROP_SERVER_NAME, propertyResolver.getProperty(PROP_SERVER_NAME));
        } else {
            config.addDataSourceProperty(PROP_URL, propertyResolver.getProperty(PROP_URL));
        }
        config.addDataSourceProperty("user", propertyResolver.getProperty(PROP_USER));
        config.addDataSourceProperty("password", propertyResolver.getProperty(PROP_PASSWORD));
        return config;
    }

    @Bean(destroyMethod = "close")
    @Primary
    public DataSource dataSource() {
        HikariConfig config = getDataSourceConfig("spring.datasource.");
        return new HikariDataSource(config);
    }
}
