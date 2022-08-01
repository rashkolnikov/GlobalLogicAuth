package com.example.myauthenticationserver.config;

import com.google.common.base.Preconditions;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

    @Value("${globalLogic-databases.relational.url}")
    private String url;

    @Value("${globalLogic-databases.relational.driverClassName}")
    private String driverClassName;

    @Value("${globalLogic-databases.relational.username}")
    private String username;

    @Value("${globalLogic-databases.relational.password}")
    private String password;


    @Bean
    public @NonNull DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(Preconditions.checkNotNull(this.url));
         dataSource.setDriverClassName(Preconditions.checkNotNull(this.driverClassName));
            dataSource.setUsername(Preconditions.checkNotNull(this.username));
            dataSource.setPassword(Preconditions.checkNotNull(this.password));

        return dataSource;
    }

}
