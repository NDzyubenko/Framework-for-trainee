package frameworkForTesting.data.entities;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.openqa.selenium.NoSuchElementException;

public interface BaseElement<A extends AtlasWebElement> extends AtlasWebElement<A> {


    default boolean isDisplayedNullable() {
        try {
            return this.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    default boolean isEnabledNullable() {
        try {
            return this.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    default String getTextNullable() {
        if (isDisplayedNullable())
            return this.getText();
        else
            return "";
    }

    default boolean isSelectedNullable() {
        try {
            return this.isSelected();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
