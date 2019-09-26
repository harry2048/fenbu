package com.kayak.fenbu.config;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration// // 扫描 Mapper接口
@MapperScan(value = "com.kayak.fenbu.mapper.mysqlMapper", sqlSessionTemplateRef = "teacherSqlSessionTemplate")
public class MysqlDataSourceConfig {
    @Value("${mybatis.mapper-locations}")
    private String mapper_locations;

    @Bean(name = "teacherDataSource")
    public DataSource masterDataSource(MysqlConfig mysqlConfig) throws SQLException {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(mysqlConfig.getUrl());
        mysqlXADataSource.setUser(mysqlConfig.getUsername());
        mysqlXADataSource.setPassword(mysqlConfig.getPassword());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("teacherDataSource");
        atomikosDataSourceBean.setMinPoolSize(mysqlConfig.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(mysqlConfig.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(mysqlConfig.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(mysqlConfig.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(mysqlConfig.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(mysqlConfig.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(mysqlConfig.getMaxIdleTime());
        atomikosDataSourceBean.setTestQuery(mysqlConfig.getTestQuery());
        return atomikosDataSourceBean;
    }

    @Bean(name = "teacherSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("teacherDataSource") DataSource masterDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapper_locations));
        return sessionFactory.getObject();
    }

    @Bean(name = "teacherSqlSessionTemplate")
    public SqlSessionTemplate regSqlSessionTemplate(@Qualifier("teacherSqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
