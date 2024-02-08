package smoke;

import frameworkForTesting.tools.additional.BaseTest;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test(description = "example")
    public void firstTest() {
        onStartPage()
                .login()
                .enterSearchData("My first test")
                .clickSearchButton()
                .verifyResults("first test");
    }
}
