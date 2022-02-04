package frameworkForTesting.tools.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class IsElementNotDisplayedMatcher extends TypeSafeMatcher<WebElement> {

    private static final Duration defaultTimeout = Duration.ofSeconds(15);
    private static Duration timeout;
    private String customMessage;

    // -------------- Constructors --------------
    private IsElementNotDisplayedMatcher() {
        super();
    }

    private IsElementNotDisplayedMatcher(String customMessage) {
        super();
        this.customMessage = customMessage;
    }

    // ----------------- Matchers -----------------
    public static Matcher<WebElement> notDisplayed() {
        timeout = defaultTimeout;
        return new IsElementNotDisplayedMatcher();
    }

    public static Matcher<WebElement> notDisplayed(String message) {
        timeout = defaultTimeout;
        return new IsElementNotDisplayedMatcher(message);
    }

    public static Matcher<WebElement> notDisplayed(Duration duration) {
        timeout = duration;
        return new IsElementNotDisplayedMatcher();
    }

    public static Matcher<WebElement> notDisplayed(Duration duration, String message) {
        timeout = duration;
        return new IsElementNotDisplayedMatcher(message);
    }

    public static Matcher<WebElement> notDisplayed(int seconds) {
        timeout = Duration.ofSeconds(seconds);
        return new IsElementNotDisplayedMatcher();
    }

    public static Matcher<WebElement> notDisplayed(int seconds, String message) {
        timeout = Duration.ofSeconds(seconds);
        return new IsElementNotDisplayedMatcher(message);
    }

    // ----------------- Overridden methods -----------------
    @Override
    protected boolean matchesSafely(WebElement element) {
        long waitUntil = System.currentTimeMillis() + timeout.toMillis();
        boolean isDisplayed = true;
        while (System.currentTimeMillis() <= waitUntil && isDisplayed) {
            try {
                isDisplayed = element.isDisplayed();
            } catch (Exception e) {
                return true;
            }
        }
        return !isDisplayed;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element is not displayed on page");
    }

    @Override
    public void describeMismatchSafely(WebElement element, Description mismatchDescription) {
        mismatchDescription
                .appendText("element ")
                .appendValue(element)
                .appendText(" is displayed on page");
        if (!customMessage.isEmpty())
            mismatchDescription.appendText("\n" + customMessage);
    }
}