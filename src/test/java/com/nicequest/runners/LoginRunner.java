package com.nicequest.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.nicequest.steps", "com.nicequest.utils"},
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true
)
public class LoginRunner {
    // No necesita código extra
}
