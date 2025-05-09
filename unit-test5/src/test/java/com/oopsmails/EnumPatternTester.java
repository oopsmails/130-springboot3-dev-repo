package com.oopsmails;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoField;
import com.openpojo.reflection.PojoMethod;
import com.openpojo.validation.test.Tester;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class EnumPatternTester implements Tester {
    @Override
    public void run(PojoClass pojoClass) {
        if (!pojoClass.isEnum()) {
            return;
        }

        // Verify the standard pattern
        verifyValueField(pojoClass);
        verifyGetterMethod(pojoClass);
        verifyFromValueMethod(pojoClass);
    }

    private void verifyValueField(PojoClass pojoClass) {
        boolean hasValueField = false;
        for (PojoField field : pojoClass.getPojoFields()) {
            if ("value".equals(field.getName()) && String.class.equals(field.getType())) {
                hasValueField = true;
                break;
            }
        }
        if (!hasValueField) {
            throw new AssertionError("Enum " + pojoClass.getName() + " is missing private String value field");
        }
    }

    private void verifyGetterMethod(PojoClass pojoClass) {
        boolean hasGetter = false;
        for (PojoMethod method : pojoClass.getPojoMethods()) {
            if ("getValue".equals(method.getName())
                    && String.class.equals(method.getReturnType())
                    && method.getPojoParameters().isEmpty()) {
                hasGetter = true;
                break;
            }
        }
        if (!hasGetter) {
            throw new AssertionError("Enum " + pojoClass.getName() + " is missing getValue() method");
        }
    }

    private void verifyFromValueMethod(PojoClass pojoClass) {
        boolean hasFromValue = false;
        for (PojoMethod method : pojoClass.getPojoMethods()) {
            if ("fromValue".equals(method.getName())
                    && method.isStatic()
                    && pojoClass.getClazz().equals(method.getReturnType())
                    && method.getPojoParameters().size() == 1
                    && String.class.equals(method.getPojoParameters().get(0).getType())) {
                hasFromValue = true;
                break;
            }
        }
        if (!hasFromValue) {
            throw new AssertionError("Enum " + pojoClass.getName() + " is missing static fromValue(String) method");
        }
    }

    public static  <T extends Enum<T>> void testEnumValues(Class<T> enumClass) {
        try {
            // Test that fromValue works for each enum constant
            for (T enumConstant : enumClass.getEnumConstants()) {
                String value = (String) enumClass.getMethod("getValue").invoke(enumConstant);
                T result = (T) enumClass.getMethod("fromValue", String.class).invoke(null, value);
                assertEquals(enumConstant, result);

                // Test case insensitivity
                T resultUpper = (T) enumClass.getMethod("fromValue", String.class).invoke(null, value.toUpperCase());
                assertEquals(enumConstant, resultUpper);
            }

            // Test invalid value
            try {
                enumClass.getMethod("fromValue", String.class).invoke(null, "invalid_value");
                fail("Expected IllegalArgumentException for invalid value");
            } catch (InvocationTargetException e) {
                assertTrue(e.getCause() instanceof IllegalArgumentException);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error testing enum: " + enumClass.getName(), e);
        }
    }
}
