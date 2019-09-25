package com.kayak.fenbu.service;

import java.util.Map;

public interface TestService {
    Map<String, Object> insertMysql(String name, Integer age);

    Map<String, Object> insertOracle(String name, Integer age);

    Map<String, Object> insertMysqlAndOrcl(String name, Integer age);
}
