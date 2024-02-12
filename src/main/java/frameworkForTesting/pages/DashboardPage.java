package frameworkForTesting.pages;

import frameworkForTesting.data.entities.BaseElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface DashboardPage extends WebPage {
    @FindBy("//div[text()='Sauce Labs Backpack']")
    BaseElement name_item();
}
