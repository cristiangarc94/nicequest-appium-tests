package com.nicequest.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final String dashboardId = "com.netquest.pokey:id/dashboard";

    public DashboardPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement getDashboardElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(dashboardId)));
    }

    public boolean isDashboardVisible() {
        return getDashboardElement().isDisplayed();
    }
}
