package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"steps", "com.core.hooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class TestRunnerParallel extends AbstractTestNGCucumberTests {

    private static ThreadLocal<String> browser = new ThreadLocal<>();

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Parameters("browser")
    @BeforeClass
    public void setBrowser(String browserName) {
        browser.set(browserName);
    }

    public static String getBrowser() {
        return browser.get();
    }

}