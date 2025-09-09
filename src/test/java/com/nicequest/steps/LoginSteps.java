package com.nicequest.steps;

import com.nicequest.pages.LoginPage;
import com.nicequest.utils.DriverManager;
import io.cucumber.java.en.*;
import io.appium.java_client.AppiumDriver;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class LoginSteps {

    private AppiumDriver driver; // Cambiado a AppiumDriver para multiplataforma
    private LoginPage loginPage;

    @Given("Open Nicequest login page on {string}")
    public void openApp(String platform) throws Exception {
        driver = (AppiumDriver) DriverManager.getDriver(platform);
        loginPage = new LoginPage(driver);
    }

    @When("I enter email {string}")
    public void enterEmail(String email) {
        loginPage.enterEmail(email);
    }

    @And("I enter password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("I tap login button")
    public void tapLogin() {
        loginPage.tapLogin();
    }

    @Then("I should see {string}")
    public void validateResult(String expectedResult) {
        if(expectedResult.equalsIgnoreCase("Dashboard visible")) {
            assertTrue(loginPage.isLoginVisible(), "Dashboard no visible");
        } else {
            String message = loginPage.getErrorMessage().getText();
            if(message.toLowerCase().contains("email")) {
                System.out.println("Credenciales de email incorrectas");
            } else if(message.toLowerCase().contains("password")) {
                System.out.println("Credenciales de contraseña incorrectas");
            } else {
                System.out.println("Error desconocido");
            }
            assertEquals(expectedResult, message);
        }
    }
}
