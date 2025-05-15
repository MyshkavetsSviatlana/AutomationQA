package hw20.runner_allure;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        plugin = {"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        },
        features = {"src/test/resources/feature_allure"},
        glue = {"hw20/selenium_allure_steps"},
        monochrome = true
)

public class LoginTestAllureRunner extends AbstractTestNGCucumberTests {
}
