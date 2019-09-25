package com.kayak.fenbu.service.impl;

import com.kayak.fenbu.mapper.mysqlMapper.MysqlMapper;
import com.kayak.fenbu.mapper.oracleMapper.OracleMapper;
import com.kayak.fenbu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private MysqlMapper mysqlMapper;

    @Autowired
    private OracleMapper oracleMapper;

    @Transactional
    @Override
    public Map<String, Object> insertMysql(String name, Integer age) {
        int count = mysqlMapper.insertMysql(name,age);
        Map<String, Object> result = new HashMap<>();
        if (count < 0) {
            result.put("msg","新增失败");
            return result;
        }
        result.put("msg", "mysql新增成功");
        return result;
    }

    @Transactional
    @Override
    public Map<String, Object> insertOracle(String name, Integer age) {
        int count = oracleMapper.insertOracle(name,age);
        Map<String, Object> result = new HashMap<>();
        if (count < 0) {
            result.put("msg","新增失败");
            return result;
        }
        result.put("msg", "新增成功");
        return result;
    }

    @Override
//    @Transactional(transactionManager="mysqlTransactionManager")
    @Transactional
    public Map<String, Object> insertMysqlAndOrcl(String name, Integer age) {
        int count2 = oracleMapper.insertOracle(name,age);
        int count1 = mysqlMapper.insertMysql(name,age);
        int x = 1/0;
        Map<String, Object> result = new HashMap<>();
        result.put("msg", count1+count2);
        return result;
    }
}
