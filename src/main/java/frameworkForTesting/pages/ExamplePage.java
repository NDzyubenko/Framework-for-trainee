package frameworkForTesting.pages;

import frameworkForTesting.data.entities.BaseElement;
import frameworkForTesting.data.entities.Textfield;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Param;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface ExamplePage extends WebPage {
    @FindBy("//textarea[@id='APjFqb']")
    Textfield search_field();

    @FindBy("//input[@name='btnK']")
    BaseElement googleSearch_button();

    @FindBy("//h3[contains(text(), '{{ value }}')]")
    List<WebElement> search_results(@Param("value") String value);
}
