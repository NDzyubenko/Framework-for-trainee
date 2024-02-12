package smoke;

import frameworkForTesting.data.variables.login.LoginData;
import frameworkForTesting.tools.additional.BaseTest;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test(description = "example")
    public void firstTest() {
        LoginData loginData = new LoginData();
        onStartPage()
                .login()
                .enterLogin(loginData.getLogin())
                .enterPassword(loginData.getPassword())
                .clickOnLoginButton()
                .verifyItem();
    }
}
