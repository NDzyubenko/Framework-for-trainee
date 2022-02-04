package frameworkForTesting.tools.matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.ElementsCollection;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static frameworkForTesting.tools.matchers.IsElementDisplayedMatcher.displayed;
import static org.hamcrest.Matchers.equalTo;

public class HasFollowingContents<E extends AtlasWebElement> extends TypeSafeMatcher<ElementsCollection<E>> {

    private final Matcher<List<String>> stringListMatcher;
    private String customMessage = "";

    // -------------- Constructors --------------
    private HasFollowingContents(Matcher<List<String>> stringListMatcher) {
        super();
        this.stringListMatcher = stringListMatcher;
    }

    private HasFollowingContents(Matcher<List<String>> stringListMatcher, String customMessage) {
        super();
        this.stringListMatcher = stringListMatcher;
        this.customMessage = customMessage;
    }

    // ----------------- Matchers -----------------

    public static <E extends AtlasWebElement> Matcher<ElementsCollection<E>> hasContents(String... expected) {
        List<String> sortedExpected = Arrays.asList(expected)
                .stream()
                .sorted()
                .collect(Collectors.toList());
        return new HasFollowingContents<E>(equalTo(sortedExpected));
    }

    public static <E extends AtlasWebElement> Matcher<ElementsCollection<E>> hasContentsMessage(String customMessage, String... expected) {
        List<String> sortedExpected = Arrays.asList(expected)
                .stream()
                .sorted()
                .collect(Collectors.toList());
        return new HasFollowingContents<E>(equalTo(sortedExpected), customMessage);
    }

    // ----------------- Overridden methods -----------------
    @Override
    protected boolean matchesSafely(ElementsCollection<E> elementsCollection) {
        return stringListMatcher.matches(elementsCollection
                .stream()
                .map(e -> e.should(displayed()).getText().trim())
                .sorted()
                .collect(Collectors.toList()));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Collection contents match ").appendDescriptionOf(stringListMatcher);
    }

    @Override
    public void describeMismatchSafely(ElementsCollection<E> elementsCollection, Description mismatchDescription) {
        mismatchDescription.appendText("Contents are")
                .appendValue(elementsCollection.toString())
                .appendText(" while expected");

        if (!customMessage.isEmpty())
            mismatchDescription.appendText("\n" + customMessage);
    }


}
