package com.airnz.qa.ui;

import com.airnz.qa.config.TestConfig;
import com.airnz.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Selenium实现航班查询UI测试（模拟Air NZ功能）
 */
public class SeleniumFlightTests {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        // 初始化Chrome驱动（无需手动下载，Selenium 4+自动管理）
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TestConfig.AIRNZ_TEST_URL);
    }

    @Test
    public void testFlightSearch() {
        // 等待页面加载并输入航班查询信息
        TestUtils.getWebDriverWait(driver, TestConfig.WAIT_TIME)
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("userName")));
        
        // 模拟登录（测试页面）
        driver.findElement(By.name("userName")).sendKeys("test");
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.name("submit")).click();

        // 验证航班查询页面加载
        TestUtils.getWebDriverWait(driver, TestConfig.WAIT_TIME)
                .until(ExpectedConditions.titleContains("Find a Flight"));
        
        // 选择单程航班
        driver.findElement(By.xpath("//input[@value='oneway']")).click();
        // 输入出发地和目的地（模拟Air NZ航班查询）
        driver.findElement(By.name("fromPort")).sendKeys("London");
        driver.findElement(By.name("toPort")).sendKeys("New York");
        driver.findElement(By.name("findFlights")).click();

        // 断言页面跳转成功
        assert driver.getTitle().contains("Flights");
        System.out.println("Selenium Flight Search Test Passed!");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}