package hw20.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-report/cucumber.html",
                "json:target/cucumber-report/cucumber.json"},
        features = {"src/test/resources/feature"},
        glue = {"hw20/selenium_steps"}
)

public class LoginTestRunner extends AbstractTestNGCucumberTests {
}
