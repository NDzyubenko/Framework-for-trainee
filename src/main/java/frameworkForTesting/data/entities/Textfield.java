package frameworkForTesting.data.entities;

import frameworkForTesting.tools.matchers.IsElementDisplayedMatcher;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

public interface Textfield extends BaseElement<Textfield> {

    default void enterText(String text) {
        //1. Clear any previous data
        this.should(IsElementDisplayedMatcher.displayed())
                .sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);

        //2. Enter text if not null
        if (text != null)
            this
                    .should(IsElementDisplayedMatcher.displayed())
                    .sendKeys(text);
    }

    default void enterText(Integer value) {
        enterText(String.valueOf((value == null) ? "" : value));
    }

    default void checkValue(String value) {
        this.should(IsElementDisplayedMatcher.displayed());
        Assert.assertEquals(this.getAttribute("value"), value);
    }

    default void checkValueInvisible(String value) {
        try {
            Assert.assertEquals(this.getAttribute("value"), value);
        } catch (NoSuchElementException e) {
            if (!value.isEmpty())
                Assert.fail(String.format("Expected %s but nothing found", value));
        }
    }


    default void checkTextValueInvisible(String value) {
        try {
            Assert.assertEquals(this.getText(), value);
        } catch (NoSuchElementException e) {
            if (!value.isEmpty())
                Assert.fail(String.format("Expected %s but nothing found", value));
        }
    }


    default String getValue() {
        this.should(IsElementDisplayedMatcher.displayed());
        return this.getAttribute("value");
    }

}
