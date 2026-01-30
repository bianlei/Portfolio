package com.airnz.qa.junit;

import com.airnz.qa.api.FlightApiTests;
import com.airnz.qa.ui.PlaywrightFlightTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * JUnit 5整合测试
 */
@Suite
@SelectClasses({FlightApiTests.class, PlaywrightFlightTests.class})
public class FlightJUnitTests {

    @Test
    @DisplayName("JUnit - API Test for Flight Data")
    public void runApiTest() {
        new FlightApiTests().testGetFlightData();
    }

    @Test
    @DisplayName("JUnit - Playwright UI Test for Flight Booking")
    public void runPlaywrightTest() {
        PlaywrightFlightTests playwrightTest = new PlaywrightFlightTests();
        playwrightTest.setup();
        playwrightTest.testFlightBooking();
        playwrightTest.teardown();
    }
}