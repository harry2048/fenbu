package com.kayak.fenbu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Data
@ConfigurationProperties(prefix = "oracle.datasource")
public class OracleConfig {
    private String url;
    private String username;
    private String password;
}
