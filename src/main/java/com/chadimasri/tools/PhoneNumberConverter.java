package com.chadimasri.tools;

import java.util.Map;
import java.util.regex.Pattern;

public class PhoneNumberConverter {
    private final Map<String, String> conversionMap;

    public PhoneNumberConverter(Map<String, String> conversionMap) {
        this.conversionMap = conversionMap;
    }

    public boolean needsConversion(String input) {
        for (Map.Entry<String, String> conversionEntry : conversionMap.entrySet()) {
            String source = conversionEntry.getKey();

            Pattern pattern = Pattern.compile(source);

            if (pattern.matcher(input).find()) {
                return true;
            }
        }

        return false;
    }

    public String convert(String input) {
        for (Map.Entry<String, String> conversionEntry : conversionMap.entrySet()) {
            String source = conversionEntry.getKey();

            Pattern pattern = Pattern.compile(source);

            if (pattern.matcher(input).find()) {
                return input.replaceAll(source, conversionEntry.getValue());
            }
        }

        return input;
    }
}