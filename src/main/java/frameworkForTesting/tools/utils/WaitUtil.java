package frameworkForTesting.tools.utils;

import frameworkForTesting.data.enums.TimeUnit;


public class WaitUtil {

    public static void wait(int amount, TimeUnit timeUnit) {
        waitFor(amount, timeUnit);
    }

    public static void waitFor(int amount, TimeUnit timeUnit) {
        try {
            switch (timeUnit) {
                case SECONDS:
                    Thread.sleep(amount * 1000);
                    break;
                case MINUTES:
                    Thread.sleep(amount * 60000);
                    break;
                case HOURS:
                    Thread.sleep(amount * 360000);
                    break;
            }
        } catch (InterruptedException e) {
        }
    }
}
