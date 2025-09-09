package com.nicequest.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement getEmailField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.netquest.pokey:id/email")));
    }

    public WebElement getPasswordField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.netquest.pokey:id/password")));
    }

    public WebElement getLoginButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.netquest.pokey:id/login_button")));
    }
}
