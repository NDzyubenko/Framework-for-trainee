package frameworkForTesting.tools.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.Is;
import org.openqa.selenium.WebElement;

public class HasPlaceholderMatcher extends TypeSafeMatcher<WebElement> {
    private final Matcher<String> textMatcher;
    private String customMessage;

    // -------------- Constructors --------------
    private HasPlaceholderMatcher(Matcher<String> textMatcher) {
        super();
        this.textMatcher = textMatcher;
    }

    private HasPlaceholderMatcher(Matcher<String> textMatcher, String customMessage) {
        super();
        this.textMatcher = textMatcher;
        this.customMessage = customMessage;
    }

    // ----------------- Matchers -----------------
    public static Matcher<WebElement> hasPlaceholder(Matcher<String> textMatcher) {
        return new HasPlaceholderMatcher(textMatcher);
    }

    public static Matcher<WebElement> hasPlaceholder(String text) {
        return new HasPlaceholderMatcher(Is.is(text));
    }

    public static Matcher<WebElement> hasPlaceholder(String text, String customMessage) {
        return new HasPlaceholderMatcher(Is.is(text), customMessage);
    }

    // ----------------- Overridden methods -----------------
    @Override
    public boolean matchesSafely(WebElement item) {
        return this.textMatcher.matches(item.getAttribute("placeholder"));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element text ").appendDescriptionOf(this.textMatcher);
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("text of element ")
                .appendValue(item).appendText(" was ")
                .appendValue(item.getText());

        if (!customMessage.isEmpty())
            mismatchDescription.appendText("\n" + customMessage);
    }
}