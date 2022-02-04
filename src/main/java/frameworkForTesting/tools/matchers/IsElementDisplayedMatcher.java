package frameworkForTesting.tools.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class IsElementDisplayedMatcher extends TypeSafeMatcher<WebElement> {

    private static final Duration defaultTimeout = Duration.ofSeconds(30);
    private static Duration timeout;
    private String customMessage;

    // -------------- Constructors --------------
    private IsElementDisplayedMatcher() {
        super();
        this.customMessage = "";
    }

    private IsElementDisplayedMatcher(String customMessage) {
        super();
        this.customMessage = customMessage;
    }

    // ----------------- Matchers -----------------
    public static Matcher<WebElement> displayed() {
        timeout = defaultTimeout;
        return new IsElementDisplayedMatcher();
    }

    public static Matcher<WebElement> displayed(String message) {
        timeout = defaultTimeout;
        return new IsElementDisplayedMatcher(message);
    }

    public static Matcher<WebElement> displayed(Duration duration) {
        timeout = duration;
        return new IsElementDisplayedMatcher();
    }

    public static Matcher<WebElement> displayed(Duration duration, String message) {
        timeout = duration;
        return new IsElementDisplayedMatcher(message);
    }

    public static Matcher<WebElement> displayed(int seconds) {
        timeout = Duration.ofSeconds(seconds);
        return new IsElementDisplayedMatcher();
    }

    public static Matcher<WebElement> displayed(int seconds, String message) {
        timeout = Duration.ofSeconds(seconds);
        return new IsElementDisplayedMatcher(message);
    }

    // ----------------- Overridden methods -----------------
    @Override
    protected boolean matchesSafely(WebElement element) {
        return Waiter.waitFor(element, timeout, e -> element.isDisplayed());
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("element is displayed while waiting ")
                .appendValue(String.valueOf(timeout.getSeconds()))
                .appendText(" seconds");
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("element ")
                .appendValue(item).appendText(" is not displayed while waiting ").
                appendValue(String.valueOf(timeout.getSeconds())).appendText(" seconds");
        if (!customMessage.isEmpty())
            mismatchDescription.appendText("\n" + customMessage);
    }
}