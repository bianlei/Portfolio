package com.airnz.qa.config;

/**
 * 测试配置类：统一管理测试常量
 */
public class TestConfig {
    // 模拟Air NZ航班查询页面（用公共测试页面替代，避免官网限制）
    public static final String AIRNZ_TEST_URL = "https://www.demo.guru99.com/test/newtours/";
    // 模拟航班API接口（公共测试API）
    public static final String FLIGHT_API_URL = "https://jsonplaceholder.typicode.com/posts";
    // 测试等待时间
    public static final int WAIT_TIME = 10;
}