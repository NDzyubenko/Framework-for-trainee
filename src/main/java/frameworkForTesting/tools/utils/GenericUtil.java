package frameworkForTesting.tools.utils;

import java.util.*;

public class GenericUtil {

    //Methods for easy work with enums

    public static boolean isNullOrEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static <T> List<T> initList(T... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    public static <T> T getAnyItem(List<T> list) {
        if (isNullOrEmpty(list)) {
            throw new IndexOutOfBoundsException("List is empty.");
        }

        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    public static Map<String, String> createStringMap(String key, String value) {
        Map<String, String> result = new HashMap<>();
        result.put(key, value);
        return result;
    }

}
