package org.example.utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils {

    public static String joinWithNewLine(String... items) {
        return Stream.of(items)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.joining("\n"));
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !value.isBlank();
    }

}
