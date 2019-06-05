package com.show.spring.cloud.config.client.controller;

import com.netflix.discovery.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试是否拿到配置文件
 * @author xuanweiyao
 * @date 11:17 2019/6/5
 */
@RestController
public class ConfigController {

    @Value("${configuration.environment}")
    String environment;

    @Value("${test.variable}")
    String variable;

    /**
     * 获取配置中心配置测试
     * @author xuanweiyao
     * @date 11:21 2019/6/5
     */
    @GetMapping("Config")
    public Map getEnvironment() {

        Map<String, String> configMap = new HashMap<>(16);
        configMap.put("environment", environment);
        configMap.put("variable", variable);
        return configMap;
    }
}
