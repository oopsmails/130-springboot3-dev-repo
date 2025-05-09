package com.oopsmails.springboot.unittest5.domain3;

import com.oopsmails.EnumPatternTester;
import org.junit.jupiter.api.Test;

class EnumPatternByPackageTest {

    @Test
    void testEnumValuesByClass() {
        EnumPatternTester.testEnumValues(YesNoEnum.class);
    }
}
