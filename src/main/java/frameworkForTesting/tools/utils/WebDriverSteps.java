package frameworkForTesting.tools.utils;


import frameworkForTesting.data.enums.TimeUnit;
import frameworkForTesting.tools.webdriver.WebDriverManager;
import io.qameta.atlas.webdriver.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebDriverSteps {

    final protected WebDriverManager webDriverManager;

    public WebDriverSteps(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    protected <T extends WebPage> T onPage(Class<T> page) {
        return webDriverManager.getAtlas().create(webDriverManager.getDriver(), page);
    }

    public WebDriverManager getWebDriverManager() {
        WaitUtil.wait(3, TimeUnit.SECONDS);
        return webDriverManager;
    }

    public WebElement findByXpath(String xpath) {
        return getWebDriverManager()
                .getDriver()
                .findElement(By.xpath(xpath));
    }
}
