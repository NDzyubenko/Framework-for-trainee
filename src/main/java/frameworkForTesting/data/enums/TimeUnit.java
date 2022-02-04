package frameworkForTesting.data.enums;

import frameworkForTesting.tools.utils.GenericUtil;

import java.util.List;

import static frameworkForTesting.tools.utils.GenericUtil.getAnyItem;

public enum TimeUnit {

    SECONDS("Seconds"),
    MINUTES("Minutes"),
    HOURS("Hours");


    private String value;

    TimeUnit(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static TimeUnit getAny() {
        List<TimeUnit> timeUnit = list();
        return getAnyItem(timeUnit);
    }

    private static List<TimeUnit> list() {
        return GenericUtil.initList(values());
    }

    public static TimeUnit anySecMin() {
        List<TimeUnit> time = list();
        time.remove(HOURS);
        return GenericUtil.getAnyItem(time);
    }

}
