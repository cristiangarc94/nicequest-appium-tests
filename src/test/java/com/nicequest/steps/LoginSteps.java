package com.nicequest.steps;

import com.nicequest.pages.LoginPage;
import com.nicequest.pages.DashboardPage;
import com.nicequest.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;

public class LoginSteps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Given("Open Nicequest login page")
    public void openApp() throws Exception {
        AndroidDriver driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @When("I enter email {string}")
    public void enterEmail(String email) {
        loginPage.getEmailField().sendKeys(email);
    }

    @And("I enter password {string}")
    public void enterPassword(String password) {
        loginPage.getPasswordField().sendKeys(password);
    }

    @And("I tap login button")
    public void tapLogin() {
        loginPage.getLoginButton().click();
    }

    @Then("I should see the dashboard")
    public void verifyDashboard() {
        Assert.assertTrue("Dashboard is not visible", dashboardPage.isDashboardVisible());
    }
}
