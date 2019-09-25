package com.kayak.fenbu;

import com.kayak.fenbu.config.MysqlConfig;
import com.kayak.fenbu.config.OracleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MysqlConfig.class, OracleConfig.class})
public class FenbuApplication {

    public static void main(String[] args) {
        SpringApplication.run(FenbuApplication.class, args);
    }

}
