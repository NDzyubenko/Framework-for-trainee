package frameworkForTesting.tools.additional;

import frameworkForTesting.tools.webdriver.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    protected WebDriverManager driverManager;
    protected LoginSteps steps;

    protected LoginSteps onStartPage() {
        return steps;
    }

    @BeforeMethod(alwaysRun = true)
    public void before(Method method) {
        driverManager = new WebDriverManager();
        driverManager.startWebDriver();
        steps = new LoginSteps(driverManager);
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        driverManager.stopWebDriver();
    }

}
