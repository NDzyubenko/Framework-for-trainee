package frameworkForTesting.steps;

import frameworkForTesting.pages.DashboardPage;
import frameworkForTesting.pages.LoginSwagPage;
import frameworkForTesting.tools.utils.WebDriverSteps;
import frameworkForTesting.tools.webdriver.WebDriverManager;
import io.qameta.allure.Step;

import static frameworkForTesting.tools.matchers.IsElementDisplayedMatcher.displayed;

public class LoginSwagSteps extends WebDriverSteps {

    //region[Basic Steps]

    @Step("Enter data to login field")
    public LoginSwagSteps enterLogin(String login) {
        onLoginSwagPage()
                .login_field()
                .should(displayed())
                .enterText(login);
        return this;
    }

    @Step("Enter data to password field")
    public LoginSwagSteps enterPassword(String password) {
        onLoginSwagPage()
                .password_field()
                .should(displayed())
                .enterText(password);
        return this;
    }

    @Step("Click on the 'Login' button")
    public LoginSwagSteps clickOnLoginButton() {
        onLoginSwagPage()
                .login_button()
                .should(displayed())
                .click();
        return this;
    }

    @Step("Verify item")
    public LoginSwagSteps verifyItem() {
        onDashboardPage()
                .name_item()
                .should(displayed());
        return this;
    }



    //endregion

    //region[Complex Steps]


    //endregion

    //region[Utility]

    public LoginSwagSteps(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    private LoginSwagPage onLoginSwagPage() {
        return onPage(LoginSwagPage.class);
    }

    private DashboardPage onDashboardPage() {
        return onPage(DashboardPage.class);
    }

    //endregion

}
