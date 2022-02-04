package frameworkForTesting.tools.webdriver;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.function.Function;

import static frameworkForTesting.tools.webdriver.Properties.getUrl;

public class WebDriverManager {

    public static final Dimension RESOLUTION = new Dimension(1920, 1080);

    private WebDriver driver;

    private Atlas atlas;


    public void stopWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void startWebDriver() {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        atlas = new Atlas(new WebDriverConfiguration(driver, getUrl()));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Atlas getAtlas() {
        return atlas;
    }

    public Actions actions() {
        return new Actions(getDriver());
    }

    public void openNewTab(String link, Integer numberTab) {
        String a = "window.open('" + link + "','_blank');";
        ((JavascriptExecutor) getDriver()).executeScript(a);
        switchTab(numberTab);
    }

    public void switchTab(Integer numberTab) {
        ArrayList tabs = new ArrayList(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(numberTab).toString());
    }


    public void clickBack() {
        getDriver().navigate().back();
    }

    public void clickRefresh() {
        getDriver().navigate().refresh();
    }

    public void waitForLoad() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }

    public void resize() {
        driver.manage().window().setSize(RESOLUTION);
        driver.manage().window().setPosition(new Point(0, 0));
    }

}

