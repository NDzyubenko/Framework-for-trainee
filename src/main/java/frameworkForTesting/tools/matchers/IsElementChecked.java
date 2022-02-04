package frameworkForTesting.tools.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class IsElementChecked extends TypeSafeMatcher<WebElement> {

    private static final Duration defaultTimeout = Duration.ofSeconds(30);
    private static Duration timeout;
    private String customMessage;

    // -------------- Constructors --------------
    private IsElementChecked() {
        super();
    }

    private IsElementChecked(String customMessage) {
        super();
        this.customMessage = customMessage;
    }

    // ----------------- Matchers -----------------
    public static Matcher<WebElement> checked() {
        timeout = defaultTimeout;
        return new IsElementChecked();
    }

    public static Matcher<WebElement> checked(String message) {
        return new IsElementChecked(message);
    }

    public static Matcher<WebElement> checked(Duration duration) {
        timeout = duration;
        return new IsElementChecked();
    }

    public static Matcher<WebElement> checked(Duration duration, String message) {
        timeout = duration;
        return new IsElementChecked(message);
    }

    public static Matcher<WebElement> checked(int seconds) {
        timeout = Duration.ofSeconds(seconds);
        return new IsElementChecked();
    }

    public static Matcher<WebElement> checked(int seconds, String message) {
        timeout = Duration.ofSeconds(seconds);
        return new IsElementChecked(message);
    }

    // ----------------- Overridden methods -----------------
    @Override
    protected boolean matchesSafely(WebElement element) {
        return Waiter.waitFor(element, timeout, e -> element.getAttribute("checked").equals("true"));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element is checked while waiting ").appendValue(String.valueOf(timeout.getSeconds())).appendText(" seconds");
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("element ").appendValue(item).appendText(" is not checked while waiting ").
                appendValue(String.valueOf(timeout.getSeconds())).appendText(" seconds");

        if (!customMessage.isEmpty())
            mismatchDescription.appendText("\n" + customMessage);
    }
}
