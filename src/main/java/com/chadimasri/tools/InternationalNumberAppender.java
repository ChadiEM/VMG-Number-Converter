package com.chadimasri.tools;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class InternationalNumberAppender {

    private static final Charset VMG_ENCODING = Charset.forName("UTF-16LE");

    public static void main(String[] args) throws IOException {
        // Parse input
        String inputDirectory = args[0];
        String outputDirectory = args[1];

        Map<String, String> conversionPatterns = new HashMap<>();
        for (int i = 2; i < args.length; i += 2) {
            conversionPatterns.put(args[i], args[i + 1]);
        }

        System.out.println(conversionPatterns);

        // Processing
        Path inputDirectoryPath = Paths.get(inputDirectory);
        Path outputDirectoryPath = Paths.get(outputDirectory);

        PhoneNumberConverter converter = new PhoneNumberConverter(conversionPatterns);

        for (File file : inputDirectoryPath.toFile().listFiles()) {
            String input = IOUtils.toString(new FileInputStream(file), VMG_ENCODING);
            if (converter.needsConversion(input)) {
                String output = converter.convert(input);
                Files.write(outputDirectoryPath.resolve(file.getName()), output.getBytes(VMG_ENCODING));
            }
        }
    }
}
