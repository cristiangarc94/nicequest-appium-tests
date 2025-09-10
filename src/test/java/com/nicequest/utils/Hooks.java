package com.nicequest.utils;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private AppiumDriver driver;
    private String platform;

    @Before
    public void setup() throws Exception {
        // Si no se pasa -Dplatform, por defecto usa Android
        platform = System.getProperty("platform", "Android");
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
