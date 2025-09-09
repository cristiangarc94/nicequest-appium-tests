package com.nicequest.utils;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.appium.java_client.android.AndroidDriver;

public class Hooks {
    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                AndroidDriver driver = DriverManager.getDriver();
                ScreenshotUtil.takeScreenshot(driver, scenario.getName().replaceAll(" ", "_"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
