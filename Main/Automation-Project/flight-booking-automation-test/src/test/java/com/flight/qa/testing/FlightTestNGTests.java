package com.airnz.qa.testng;

import com.airnz.qa.ui.PlaywrightFlightTests;
import com.airnz.qa.ui.SeleniumFlightTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class FlightTestNGTests {
    // 声明Selenium和Playwright测试类的实例
    private SeleniumFlightTests seleniumTests;
    private PlaywrightFlightTests playwrightTests;

    // 【前置操作】执行所有测试前初始化（Selenium+Playwright）
    @BeforeSuite
    public void setupSuite() {
        // 初始化Selenium测试
        seleniumTests = new SeleniumFlightTests();
        seleniumTests.setup(); // 调用Selenium的初始化方法
        
        // 初始化Playwright测试
        playwrightTests = new PlaywrightFlightTests();
        playwrightTests.setup(); // 调用Playwright的初始化方法
    }

    @Test(priority = 1, groups = "Selenium", description = "TestNG - Selenium Flight Search Test")
    public void runSeleniumFlightTest() {
        seleniumTests.testFlightSearch();
    }

    @Test(priority = 2, groups = "Playwright", description = "TestNG - Playwright Flight Booking Test")
    public void runPlaywrightFlightTest() {
        // playwrightTests.page.setDefaultTimeout(60000); // 60s超时设置，报错
        playwrightTests.testFlightBooking();
    }

    @AfterSuite
    public void teardownSuite() {
        seleniumTests.teardown();
        playwrightTests.teardown();
    }
}