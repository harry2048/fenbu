package com.kayak.fenbu.mapper.mysqlMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MysqlMapper {
//    @Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
    int insertMysql(@Param("name") String name, @Param("age") Integer age);
}
