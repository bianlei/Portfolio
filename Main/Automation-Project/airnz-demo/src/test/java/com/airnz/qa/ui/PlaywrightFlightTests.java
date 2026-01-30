package com.airnz.qa.ui;

import com.airnz.qa.config.TestConfig;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Playwright实现航班查询UI测试（对比Selenium，体现多工具能力）
 */
public class PlaywrightFlightTests {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeEach
    public void setup() {
        // 初始化Playwright
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(TestConfig.AIRNZ_TEST_URL);
    }

    @Test
    public void testFlightBooking() {
        // 等待页面加载并登录
        page.waitForSelector("input[name='userName']");
        page.fill("input[name='userName']", "test");
        page.fill("input[name='password']", "test");
        page.click("input[name='submit']");

        // 验证页面跳转
        page.waitForURL("**/reservation.php");
        // 选择往返航班
        page.click("input[value='roundtrip']");
        // 选择出发日期
        page.selectOption("select[name='fromMonth']", "12");
        page.selectOption("select[name='fromDay']", "25");
        // 提交查询
        page.click("input[name='findFlights']");

        // 断言航班结果页面加载
        assert page.title().contains("Flights");
        System.out.println("Playwright Flight Booking Test Passed!");
    }

    @AfterEach
    public void teardown() {
        // 关闭资源
        browser.close();
        playwright.close();
    }
}