package frameworkForTesting.pages;

import frameworkForTesting.data.entities.BaseElement;
import frameworkForTesting.data.entities.Textfield;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface LoginSwagPage extends WebPage {

    @FindBy("//input[@id='user-name']")
    Textfield login_field();

    @FindBy("//input[@id='password']")
    Textfield password_field();

    @FindBy("//input[@id='login-button']")
    BaseElement login_button();

}
