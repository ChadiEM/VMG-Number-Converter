package com.chadimasri.tools;

import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@RunWith(Parameterized.class)
public class PhoneNumberConverterTest {

    private PhoneNumberConverter converter;

    private String input;
    private String expected;

    public PhoneNumberConverterTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"BLA BLA\nTEL:70987654\nBLABLA", "BLA BLA\nTEL:+96170987654\nBLABLA"},
                {"BLA BLA\nTEL:71987654\nBLABLA", "BLA BLA\nTEL:+96171987654\nBLABLA"},
                {"BLA BLA\nTEL:78987654\nBLABLA", "BLA BLA\nTEL:+96178987654\nBLABLA"},
                {"BLA BLA\nTEL:017671610174\nBLABLA", "BLA BLA\nTEL:+4917671610174\nBLABLA"},
                {"BLA BLA\nTEL:03944849\nBLABLA", "BLA BLA\nTEL:+9613944849\nBLABLA"}

        });
    }

    @Before
    public void doBefore() {
        Map<String, String> conversions = ImmutableMap.<String, String>builder()
                .put("TEL:(70\\d{6})", "TEL:+961$1")
                .put("TEL:(71\\d{6})", "TEL:+961$1")
                .put("TEL:(78\\d{6})", "TEL:+961$1")
                .put("TEL:0(3\\d{6})", "TEL:+961$1")
                .put("TEL:0(1\\d{10})", "TEL:+49$1")
                .build();

        converter = new PhoneNumberConverter(conversions);
    }

    @Test
    public void testConvertString() throws Exception {
        Assert.assertEquals(expected, converter.convert(input));
    }
}