package com.airnz.qa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestUtils {
    public static WebDriverWait getWebDriverWait(WebDriver driver, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public static String getRandomFlightNumber() {
        return "ANZ" + (int) (Math.random() * 1000);
    }
}