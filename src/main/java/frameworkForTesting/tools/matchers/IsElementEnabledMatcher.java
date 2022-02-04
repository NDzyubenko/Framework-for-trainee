package frameworkForTesting.tools.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class IsElementEnabledMatcher extends TypeSafeMatcher<WebElement> {

    private static final Duration defaultTimeout = Duration.ofSeconds(30);
    private static Duration timeout;
    private String customMessage;

    // -------------- Constructors --------------
    private IsElementEnabledMatcher() {
        super();
    }

    private IsElementEnabledMatcher(String customMessage) {
        super();
        this.customMessage = customMessage;
    }

    // ----------------- Matchers -----------------
    public static Matcher<WebElement> enabled() {
        timeout = defaultTimeout;
        return new IsElementEnabledMatcher();
    }

    public static Matcher<WebElement> enabled(String message) {
        timeout = defaultTimeout;
        return new IsElementEnabledMatcher(message);
    }

    public static Matcher<WebElement> enabled(Duration duration) {
        timeout = duration;
        return new IsElementEnabledMatcher();
    }

    public static Matcher<WebElement> enabled(Duration duration, String message) {
        timeout = duration;
        return new IsElementEnabledMatcher(message);
    }

    public static Matcher<WebElement> enabled(int seconds) {
        timeout = Duration.ofSeconds(seconds);
        return new IsElementEnabledMatcher();
    }

    public static Matcher<WebElement> enabled(int seconds, String message) {
        timeout = Duration.ofSeconds(seconds);
        return new IsElementEnabledMatcher(message);
    }

    // ----------------- Overridden methods -----------------
    @Override
    protected boolean matchesSafely(WebElement element) {
        return Waiter.waitFor(element, timeout, e -> element.isEnabled());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element is enabled while waiting ").appendValue(String.valueOf(timeout.getSeconds())).appendText(" seconds");
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("element ")
                .appendValue(item).appendText(" is not enabled while waiting ").
                appendValue(String.valueOf(timeout.getSeconds())).appendText(" seconds");

        if (!customMessage.isEmpty())
            mismatchDescription.appendText("\n" + customMessage);
    }
}