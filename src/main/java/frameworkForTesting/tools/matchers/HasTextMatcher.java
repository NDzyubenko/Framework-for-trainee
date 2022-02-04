package frameworkForTesting.tools.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class HasTextMatcher extends TypeSafeMatcher<WebElement> {
    private final Matcher<String> textMatcher;
    private final Duration timeout = Duration.ofSeconds(15);
    private String customMessage;

    // -------------- Constructors --------------
    private HasTextMatcher(Matcher<String> textMatcher) {
        super();
        this.textMatcher = textMatcher;
        this.customMessage = "";
    }

    private HasTextMatcher(Matcher<String> textMatcher, String message) {
        super();
        this.textMatcher = textMatcher;
        this.customMessage = message;
    }

    // ----------------- Matchers -----------------
    public static Matcher<WebElement> hasText(Matcher<String> textMatcher) {
        return new HasTextMatcher(textMatcher);
    }

    public static Matcher<WebElement> hasText(Matcher<String> textMatcher, String message) {
        return new HasTextMatcher(textMatcher, message);
    }

    public static Matcher<WebElement> hasText(String text) {
        return new HasTextMatcher(Is.is(text));
    }

    public static Matcher<WebElement> hasText(String text, String message) {
        return new HasTextMatcher(Is.is(text), message);
    }

    public static Matcher<WebElement> hasNotText(String text) {
        return new HasTextMatcher(IsNot.not(text));
    }

    public static Matcher<WebElement> hasNotText(String text, String message) {
        return new HasTextMatcher(IsNot.not(text), message);
    }

    // ----------------- Overridden methods -----------------
    @Override
    public boolean matchesSafely(WebElement item) {
        return Waiter.waitFor(item, timeout, e -> this.textMatcher.matches(item.getText().trim()));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element text ").appendDescriptionOf(this.textMatcher);
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription
                .appendText("text of element ")
                .appendValue(item).appendText(" was ")
                .appendValue(item.getText().trim());
        if (!customMessage.isEmpty())
            mismatchDescription.appendText("\n" + customMessage);
    }
}