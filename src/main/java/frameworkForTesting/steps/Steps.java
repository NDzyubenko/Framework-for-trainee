package frameworkForTesting.steps;

import frameworkForTesting.pages.ExamplePage;
import frameworkForTesting.tools.utils.WebDriverSteps;
import frameworkForTesting.tools.webdriver.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.List;

import static frameworkForTesting.tools.matchers.IsElementDisplayedMatcher.displayed;

public class Steps extends WebDriverSteps {

    //region[Basic Steps]

    @Step("Enter data to the search field")
    public Steps enterSearchData(String value) {
        onExamplePage()
                .search_field()
                .should(displayed())
                .enterText(value);
        return this;
    }

    @Step("Click on the search button")
    public Steps clickSearchButton() {
        onExamplePage()
                .googleSearch_button()
                .should(displayed())
                .click();
        return this;
    }

    @Step("Verify results")
    public Steps verifyResults(String value) {
        List<WebElement> results = onExamplePage().search_results(value);
        for (WebElement result : results) {
            System.out.println(result.getText());
        }
        return this;
    }

    //endregion

    //region[Complex Steps]

    //endregion

    //region[Utility]

    public Steps(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    private ExamplePage onExamplePage() {
        return onPage(ExamplePage.class);
    }

    //endregion

}
