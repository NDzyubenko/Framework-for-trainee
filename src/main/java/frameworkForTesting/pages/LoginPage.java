package frameworkForTesting.pages;

import frameworkForTesting.data.entities.BaseElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface LoginPage extends WebPage {
    @FindBy("//button[@class='close']")
    BaseElement close_advertising_button();
}
