package com.kayak.fenbu.mapper.oracleMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OracleMapper {
//    @Insert("INSERT INTO person(name, age) VALUES(#{name}, #{age})")
    int insertOracle(@Param("name") String name, @Param("age") Integer age);

}
