package frameworkForTesting.tools.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class IsCollectionHasSize<E> extends TypeSafeMatcher<Collection<? extends E>> {

    private final Matcher<Integer> intMatcher;
    private String customMessage;
    private int timeout = 30;

    // -------------- Constructors --------------
    private IsCollectionHasSize(Matcher<Integer> intMatcher) {
        this.intMatcher = intMatcher;
        this.customMessage = "";
    }

    private IsCollectionHasSize(Matcher<Integer> intMatcher, String customMessage) {
        super();
        this.intMatcher = intMatcher;
        this.customMessage = customMessage;
    }

    private IsCollectionHasSize(Matcher<Integer> intMatcher, int timeout) {
        super();
        this.intMatcher = intMatcher;
        this.timeout = timeout;
        this.customMessage = "";
    }

    private IsCollectionHasSize(Matcher<Integer> intMatcher, int timeout, String customMessage) {
        super();
        this.intMatcher = intMatcher;
        this.timeout = timeout;
        this.customMessage = customMessage;
    }

    // ----------------- Matchers -----------------
    public static <E> Matcher<Collection<? extends E>> hasSize(int size) {
        return new IsCollectionHasSize<>(equalTo(size));
    }

    public static <E> Matcher<Collection<? extends E>> hasSize(int size, String message) {
        return new IsCollectionHasSize<>(equalTo(size), message);
    }

    public static <E> Matcher<Collection<? extends E>> hasSize(int size, int timeout) {
        return new IsCollectionHasSize<>(equalTo(size), timeout);
    }

    public static <E> Matcher<Collection<? extends E>> hasSize(int size, int timeout, String customMessage) {
        return new IsCollectionHasSize<>(equalTo(size), timeout, customMessage);
    }

    public static <E> Matcher<Collection<? extends E>> hasSize(Matcher<Integer> intMatcher) {
        return new IsCollectionHasSize<>(intMatcher);
    }

    public static <E> Matcher<Collection<? extends E>> hasSizeGreaterThanOrEqualTo(int size) {
        return new IsCollectionHasSize<>(greaterThanOrEqualTo(size));
    }

    public static <E> Matcher<Collection<? extends E>> notEmpty() {
        return new IsCollectionHasSize<>(greaterThanOrEqualTo(1));
    }

    public static <E> Matcher<Collection<? extends E>> notEmpty(String customMessage) {
        return new IsCollectionHasSize<>(greaterThanOrEqualTo(1), customMessage);
    }

    // ----------------- Overridden methods -----------------
    @Override
    protected boolean matchesSafely(Collection<? extends E> element) {
        long waitUntil = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeout);
        while (System.currentTimeMillis() <= waitUntil && !intMatcher.matches(element.size())) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return intMatcher.matches(element.size());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("collection size is ").appendDescriptionOf(intMatcher);
    }

    @Override
    public void describeMismatchSafely(Collection<? extends E> elements, Description mismatchDescription) {
        mismatchDescription.appendText("collection size is ").appendValue(elements.size()).appendText(" while waiting ")
                .appendValue(timeout).appendText(" seconds");

        if (!customMessage.isEmpty())
            mismatchDescription.appendText("\n" + customMessage);
    }
}