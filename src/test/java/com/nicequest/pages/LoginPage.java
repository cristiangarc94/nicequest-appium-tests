package com.nicequest.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.netquest.pokey:id/email"))
        );
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.netquest.pokey:id/password"))
        );
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void tapLogin() {
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("com.netquest.pokey:id/login_button"))
        );
        loginButton.click();
    }

    public WebElement getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.netquest.pokey:id/error_message"))
        );
    }

    public boolean isLoginVisible() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.netquest.pokey:id/dashboard"))
        ).isDisplayed();
    }
}
