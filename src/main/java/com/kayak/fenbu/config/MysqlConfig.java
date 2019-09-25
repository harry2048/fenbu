package com.kayak.fenbu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Data
@ConfigurationProperties(prefix = "mysql.datasource")
public class MysqlConfig {
    private String url;
    private String username;
    private String password;
    private String testQuery;
}
