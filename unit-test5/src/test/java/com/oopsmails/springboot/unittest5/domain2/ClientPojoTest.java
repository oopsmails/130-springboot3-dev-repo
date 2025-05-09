package com.oopsmails.springboot.unittest5.domain2;

import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.jupiter.api.Test;

class ClientPojoTest {
    @Test
    void testGettersAndSetters() throws Exception {
        Validator validator = ValidatorBuilder.create()
                .with(new GetterTester())
                .with(new SetterTester())
                .build();
        validator.validate("com.oopsmails.springboot.unittest5.domain2");
    }
}