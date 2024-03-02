package com.zjr.assistant.config;

import com.zjr.assistant.utils.DBConnectionPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBPoolConfig {

    @Bean
    public DBConnectionPoolExecutor dbConnectionPoolExecutor(){
        return new DBConnectionPoolExecutor(10,20);
    }
}
