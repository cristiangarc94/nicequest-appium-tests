package com.nicequest.utils;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private AppiumDriver driver;
    private String platform = "Android"; // o "iOS", según configuración/test

    @Before
    public void setup() throws Exception {
        driver = (AppiumDriver) DriverManager.getDriver(platform);
    }

    @After
    public void teardown() {
        DriverManager.quitDriver(platform);
    }

    public AppiumDriver getDriver() {
        return driver;
    }
}
