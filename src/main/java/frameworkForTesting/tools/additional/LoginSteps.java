package frameworkForTesting.tools.additional;

import frameworkForTesting.steps.LoginSwagSteps;
import frameworkForTesting.tools.utils.WebDriverSteps;
import frameworkForTesting.tools.webdriver.WebDriverManager;
import io.qameta.atlas.webdriver.WebPage;


public class LoginSteps extends WebDriverSteps {

    public LoginSteps(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public LoginSwagSteps login() {
        getWebDriverManager().resize();
        onWebPage().open();
        return new LoginSwagSteps(getWebDriverManager());
    }

    private WebPage onWebPage() {
        return onPage(WebPage.class);
    }
}
