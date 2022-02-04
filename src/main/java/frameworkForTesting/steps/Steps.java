package frameworkForTesting.steps;

import frameworkForTesting.pages.LoginPage;
import frameworkForTesting.tools.utils.WebDriverSteps;
import frameworkForTesting.tools.webdriver.WebDriverManager;

public class Steps extends WebDriverSteps {

    //region[Basic Steps]

    //endregion

    //region[Complex Steps]

    //endregion

    //region[Utility]

    public Steps(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }


    private LoginPage onLoginPage() {
        return onPage(LoginPage.class);
    }

    //endregion

}
