package frameworkForTesting.tools.matchers;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.function.Predicate;

public class Waiter {

    public static <T> boolean waitFor(T element, Duration timeout, Predicate<T> condition) {
        Clock clock = Clock.systemDefaultZone();
        int pollingIntervalInMs = 100;

        Instant end = clock.instant().plus(timeout);
        boolean isReady = false;
        while (clock.instant().compareTo(end) <= 0 && !isReady) {
            try {
                isReady = condition.test(element);
            } catch (Exception ex) {
                try {
                    Thread.sleep(pollingIntervalInMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return isReady;

    }

}
