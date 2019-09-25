package com.kayak.fenbu.controller;

import com.kayak.fenbu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/testMysql")
    @ResponseBody
    public Map<String, Object> testMysql(@RequestParam String name, @RequestParam Integer age){
        return testService.insertMysql(name, age);
    }

    @RequestMapping("/testOracle")
    @ResponseBody
    public Map<String, Object> testOracle(@RequestParam String name, @RequestParam Integer age){
        return testService.insertOracle(name, age);
    }

    @RequestMapping("/insertMysqlAndOrcl")
    @ResponseBody
    public Map<String, Object> insertMysqlAndOrcl(@RequestParam String name, @RequestParam Integer age){
        return testService.insertMysqlAndOrcl(name, age);
    }
}
