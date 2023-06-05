package html.util;

import java.util.Map;
import java.util.stream.Collectors;

public class Utils {

    public static String generateStringFromMap(Map attrs, boolean multiValue) {
        String str = "";
        if (multiValue) {
            str =  (String) attrs.keySet().stream()
                    .map(key -> key + "=" + attrs.get(key))
                    .collect(Collectors.joining(", "));
        } else {
            str = " " + attrs.keySet().stream()
                    .map(key -> key + "=\"" + attrs.get(key) + "\"")
                    .collect(Collectors.joining(" "));
        }

        return str;
    }

    public static String generateStringFromMap(Map attrs) {
        return generateStringFromMap(attrs, false);
    }
}
