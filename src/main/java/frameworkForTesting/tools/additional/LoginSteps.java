package frameworkForTesting.tools.additional;

import frameworkForTesting.steps.Steps;
import frameworkForTesting.tools.utils.WebDriverSteps;
import frameworkForTesting.tools.webdriver.WebDriverManager;
import io.qameta.atlas.webdriver.WebPage;


public class LoginSteps extends WebDriverSteps {

    public LoginSteps(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public Steps login() {
        getWebDriverManager().resize();
        onWebPage().open();
        return new Steps(getWebDriverManager());
    }

    private WebPage onWebPage() {
        return onPage(WebPage.class);
    }
}
