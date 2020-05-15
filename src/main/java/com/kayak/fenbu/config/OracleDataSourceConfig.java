package com.kayak.fenbu.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration// // 扫描 Mapper 接口并容器管理
@MapperScan(value = "com.kayak.fenbu.mapper.oracleMapper", sqlSessionTemplateRef = "moviesSqlSessionTemplate")
public class OracleDataSourceConfig {
    @Value("${mybatis.mapper-locations}")
    private String mapper_locations;

    @Bean(name = "moviesDataSource")
    @Primary
    public DataSource masterDataSource(OracleConfig oracleConfig) throws SQLException {
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setUrl(oracleConfig.getUrl());
        druidXADataSource.setUsername(oracleConfig.getUsername());
        druidXADataSource.setPassword(oracleConfig.getPassword());

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(druidXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("moviesDataSource");
//        atomikosDataSourceBean.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        atomikosDataSourceBean.setMaxPoolSize(oracleConfig.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(oracleConfig.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(oracleConfig.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(oracleConfig.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(oracleConfig.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(oracleConfig.getMaxIdleTime());
        return atomikosDataSourceBean;
    }

    @Bean(name = "moviesSqlSessionTemplate")
    public SqlSessionTemplate regSqlSessionTemplate(@Qualifier("moviesSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "moviesSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("moviesDataSource") DataSource masterDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapper_locations));
        return sessionFactory.getObject();
    }
}
